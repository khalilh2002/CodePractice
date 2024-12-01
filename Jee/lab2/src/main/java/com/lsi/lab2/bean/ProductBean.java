package com.lsi.lab2.bean;

import com.lsi.lab2.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class ProductBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  private String name;
  private Double price;

  public String saveProduct() {
    Product product = Product.builder()
      .name(name)
      .price(price)
      .build();

    entityManager.getTransaction().begin();
    entityManager.persist(product);
    entityManager.getTransaction().commit();
    return "product-list.xhtml?faces-redirect=true"; // Redirect to product list after saving
  }

  public List<Product> getProducts() {
    return entityManager.createQuery("SELECT p FROM Product p", Product.class)
      .getResultList();
  }

  public String navigateToAddProduct() {
    return "product-add?faces-redirect=true"; // Redirect to product-add.xhtml
  }
}
