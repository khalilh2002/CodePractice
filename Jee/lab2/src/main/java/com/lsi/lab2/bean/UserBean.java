package com.lsi.lab2.bean;

import com.lsi.lab2.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Named
@RequestScoped
public class UserBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  @Inject
  private LoginBean loginBean;

  private String username;
  private String email;
  private String password;
  private User user;

  private String error;


  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public String save() {
    try {
      User user = User.builder()
        .username(username)
        .email(email)
        .password(password)
        .build();

      entityManager.getTransaction().begin();
      if (user.getId() != null && !entityManager.contains(user)) {
        entityManager.merge(user);
      } else {
        entityManager.persist(user);
      }
      entityManager.getTransaction().commit();
      return "user-all.xhtml?faces-redirect=true"; // Redirect to user list after saving
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      error = "Error saving user: " + e.getMessage();
      e.printStackTrace();
      return null; // Stay on the same page
    }
  }

  public String deleteUser(long id) {
    try {
      if (loginBean.isLoggedIn() && loginBean.getLoggedInUser().getId() == id) {
        error = "User is logged in and cannot be deleted.";
        return "user-all.xhtml?faces-redirect=true";
      }

      User user = entityManager.find(User.class, id);
      if (user == null) {
        error = "User not found.";
        return "user-all.xhtml?faces-redirect=true";
      }

      entityManager.getTransaction().begin();
      entityManager.remove(user);
      entityManager.getTransaction().commit();
      error = ""; // Clear error if successful
      return "user-all.xhtml?faces-redirect=true";
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      error = "Error deleting user: " + e.getMessage();
      e.printStackTrace();
      return null; // Stay on the same page
    }
  }

  public String loadUser(Long userId) {
    try {
      this.user = entityManager.find(User.class, userId);
      if (user == null) {
        error = "User not found.";
        return null; // Stay on the same page
      }
      error = ""; // Clear error if successful
      return "user-view.xhtml?faces-redirect=true";
    } catch (Exception e) {
      error = "Error loading user: " + e.getMessage();
      e.printStackTrace();
      return null; // Stay on the same page
    }
  }

  public List<User> getUsers() {
    return entityManager.createQuery("SELECT u FROM User u", User.class)
      .getResultList();
  }

  public String navigateToAddUser() {
    return "user-add?faces-redirect=true"; // Correct redirection to user-add.xhtml
  }
  public String navigateToAllUsers() {
    return "user-all.xhtml?faces-redirect=true"; // Redirect to user list after saving

  }




}
