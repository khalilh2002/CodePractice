package com.lsi.lab2.bean;

import com.lsi.lab2.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Named
@SessionScoped
public class UserBean implements Serializable {

  @Inject
  private EntityManager entityManager;

  private String username;
  private String email;
  private String password;
  private User user;


  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public String save(){
    User user = User.builder()
      .username(username)
      .email(email)
      .password(password)
      .build();
    if (user.getId()!=null && !entityManager.contains(user)) {
      entityManager.merge(user);
    }
    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();
    return "user-all.xhtml?faces-redirect=true"; // Redirect to user list after saving


  }

  public String loadUser(Long userId) {
    this.user = entityManager.find(User.class, userId);
    return "user-view.xhtml?faces-redirect=true";
  }

  public List<User> getUsers() {
    return entityManager.createQuery("SELECT u FROM User u", User.class)
      .getResultList();
  }

  public String navigateToAddUser() {
    return "user-add?faces-redirect=true"; // Correct redirection to user-add.xhtml
  }



}
