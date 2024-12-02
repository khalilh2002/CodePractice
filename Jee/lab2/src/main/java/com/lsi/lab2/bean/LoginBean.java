package com.lsi.lab2.bean;

import com.lsi.lab2.model.User;
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
