package com.empik.githubadapter.it;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class RequestTestHelper {

    @Autowired
    private EntityManager entityManager;


    int requestCountFor(String login) {
        Query query = entityManager.createNativeQuery("select request_count from request where login = ?1");

        query.setParameter(1, login);

        return (Integer) query.getSingleResult();
    }
}
