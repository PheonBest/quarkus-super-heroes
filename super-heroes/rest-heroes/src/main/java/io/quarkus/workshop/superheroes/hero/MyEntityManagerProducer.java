package io.quarkus.workshop.superheroes.hero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Produces;
import jakarta.ejb.Stateless;

@Stateless
public class MyEntityManagerProducer{

    @PersistenceContext(unitName="name")
    private EntityManager entityManager;

    @Produces
    public EntityManager entityManager(){
        return entityManager;
    }

}
