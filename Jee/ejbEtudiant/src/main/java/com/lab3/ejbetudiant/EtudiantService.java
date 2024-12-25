package com.lab3.ejbetudiant;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class EtudiantService implements EtudiantServiceRemote {
    @PersistenceContext(unitName = "Etudiant")
    private EntityManager entityManager;

    @Override
    public void addEtudiant(Etudiant etudiant) {
        entityManager.persist(etudiant);
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

    @Override
    public Etudiant findEtudiantById(int id) {
        return entityManager.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant> findAllEtudiants() {
        return entityManager.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
    }
}
