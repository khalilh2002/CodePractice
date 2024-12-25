package com.lab3.ejbproject;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EtudiantSessionBean implements EtudiantRemote {
  @PersistenceContext(unitName = "EtudiantPU")
  private EntityManager em;

  @Override
  public void createEtudiant(Etudiant e) { em.persist(e); }

  @Override
  public Etudiant findEtudiant(int id) { return em.find(Etudiant.class, id); }

  @Override
  public List<Etudiant> findAll() {
    return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
  }

  @Override
  public void updateEtudiant(Etudiant e) { em.merge(e); }

  @Override
  public void deleteEtudiant(int id) {
    Etudiant e = findEtudiant(id);
    if (e != null) em.remove(e);
  }
}
