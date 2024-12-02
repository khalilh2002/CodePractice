package com.lsi.lab2.bean;

import com.lsi.lab2.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.PersistenceContext;
import lombok.Data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.Getter;

import java.io.Serializable;

@Data
@Named
@ApplicationScoped
public class LoginBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  private String username;
  private String password;
  @Getter
  private User loggedInUser;


  @PostConstruct
  public void init() {
    if (entityManager == null) {
      System.err.println("EntityManager is null in LoginBean.init()");
      return; // Exit if EntityManager is not available
    }

    try {
      // Check if a user with the username "test" exists
      User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
        .setParameter("username", "test")
        .getSingleResult();
      // User already exists, no need to do anything
    } catch (NoResultException e) {
      User user = new User();
      user.setUsername("test");
      user.setPassword("test");
      user.setEmail("test@test.com");
      entityManager.getTransaction().begin();
      entityManager.persist(user);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      // Log or handle unexpected exceptions
    }
  }


  public String login() {
    try {
      // Parameterized query to avoid SQL injection
      User user = entityManager.createQuery(
          "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
        .setParameter("username", username)
        .setParameter("password", password)
        .getSingleResult();

        loggedInUser = user;
      return "/user/user-all.xhtml?faces-redirect=true";

    } catch (NoResultException e) {
      // No user found with the given credentials
      loggedInUser = null;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "login.xhtml?faces-redirect=true"; // Redirect back to login on failure
  }

  public boolean isLoggedIn() {
    return loggedInUser != null;
  }

  public String logout() {
    loggedInUser = null; // Clear session
    return "login.xhtml?faces-redirect=true"; // Redirect to login page
  }
}
