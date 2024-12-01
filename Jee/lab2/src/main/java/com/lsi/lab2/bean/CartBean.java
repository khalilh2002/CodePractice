package com.lsi.lab2.bean;

import com.lsi.lab2.model.Cart;
import com.lsi.lab2.model.Product;
import com.lsi.lab2.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class CartBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  @Inject
  private LoginBean loginBean; // Inject LoginBean to get the logged-in user

  private Cart cart; // Holds the current cart

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
          .orElseGet(() -> createNewCartForUser(currentUser));
      } else {
        throw new IllegalStateException("No user is logged in.");
      }
    }
    return cart;
  }
  private Cart createNewCartForUser(User user) {
    if (user.getCart() != null) {
      return user.getCart(); // Return the existing cart if already associated
    }

    Cart newCart = new Cart();
    newCart.setUser(user);
    newCart.setProducts(new ArrayList<>());

    entityManager.getTransaction().begin();
    entityManager.persist(newCart);
    user.setCart(newCart); // Associate cart with the user
    entityManager.merge(user); // Update the user
    entityManager.getTransaction().commit();

    return newCart;
  }

  public void addToCart(Product product) {
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
