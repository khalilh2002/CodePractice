package com.lab3.session.service;
import com.lab3.domain.persistance.Etudiant;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EtudiantSessionBean implements EtudiantRemote {

  @PersistenceContext(unitName = "Etudiant")
  private EntityManager entityManager;

  @Override
  public void createEtudiant(Etudiant etudiant) {
    entityManager.persist(etudiant);
  }


  @Override
  public Etudiant findEtudiantById(int id) {
    return entityManager.find(Etudiant.class, id);
  }

  @Override
  public List<Etudiant> getAllEtudiants() {
    return entityManager.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
  }

  @Override
  public void updateEtudiant(Etudiant etudiant) {
    entityManager.merge(etudiant);
  }

  @Override
  public void deleteEtudiant(int id) {
    Etudiant etudiant = entityManager.find(Etudiant.class, id);
    if (etudiant != null) {
      entityManager.remove(etudiant);
    }
  }
}
