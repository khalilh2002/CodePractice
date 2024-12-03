package com.lsi.lab2.bean;

import com.lsi.lab2.model.Cart;
import com.lsi.lab2.model.Product;
import com.lsi.lab2.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@RequestScoped
public class CartBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  @Inject
  private LoginBean loginBean; // Inject LoginBean to get the logged-in user

  private Cart cart; // Holds the current cart

  @PostConstruct
  public void init() {
    ensureLoggedIn();
  }


  public void ensureLoggedIn() {
    if (!loginBean.isLoggedIn()) {
      try {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/login.xhtml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }



  public List<Product> getProducts() {
    return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
  }
  public Cart getCart() {

    if (cart == null) {
      User currentUser = loginBean.getLoggedInUser(); // Fetch logged-in user
      if (currentUser != null) {
        cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
          .setParameter("userId", currentUser.getId())
          .getResultStream()
          .findFirst()
          .orElseGet(() -> createNewCartForUser(currentUser.getId()));
      } else {
        throw new IllegalStateException("No user is logged in.");
      }
    }
    entityManager.refresh(cart); // Ensure cart reflects the current database state

    return cart;
  }
  private Cart createNewCartForUser(Long userId) {
    // Find the user by ID
    User user = entityManager.find(User.class, userId);
    if (user == null) {
      throw new IllegalArgumentException("User does not exist with ID: " + userId);
    }

    // Check if the user already has a cart
    if (user.getCart() != null) {
      return user.getCart(); // Return the existing cart
    }

    // Create and associate a new cart
    Cart newCart = new Cart();
    newCart.setUser(user);
    newCart.setProducts(new ArrayList<>());

    // Persist the cart and update the user
    entityManager.getTransaction().begin();
    entityManager.persist(newCart);
    user.setCart(newCart); // Associate the cart with the user
    entityManager.merge(user); // Ensure the user entity is updated
    entityManager.getTransaction().commit();

    return newCart;
  }


  public void addToCart(Product product) {
    System.out.println(product);
    Cart userCart = getCart(); // Ensure cart is initialized
    if (userCart == null || loginBean.getLoggedInUser() == null) {
      throw new IllegalStateException("No cart or user found.");
    }
    if (!userCart.getProducts().contains(product)) {
      userCart.getProducts().add(product);
      entityManager.getTransaction().begin();
      entityManager.merge(userCart); // Update cart
      entityManager.getTransaction().commit();
    }
  }

  public void removeFromCart(Product product) {
    Cart userCart = getCart(); // Ensure cart is initialized
    if (userCart.getProducts().contains(product)) {
      userCart.getProducts().remove(product); // Remove product from the cart
      entityManager.getTransaction().begin();
      entityManager.merge(userCart); // Update the cart in the database
      entityManager.getTransaction().commit();

    }
  }
}
