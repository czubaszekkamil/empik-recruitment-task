package com.empik.githubadapter.empik.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class RequestRepositoryImpl implements RequestRepository {

    private static final String CREATE_SQL = "INSERT INTO request VALUES (:login, 1)";
    private static final String INCREMENT_COUNTER_SQL = "UPDATE request SET request_count = request_count + 1 WHERE login = :login";

    private final EntityManager em;

    @Override
    public void saveOrUpdateCounter(String login) {
        int numberOfUpdates = updateCounter(login);

        if (isNotUpdated(numberOfUpdates)) {
            saveNewCounter(login);
        }
    }

    private static boolean isNotUpdated(int numberOfUpdates) {
        return numberOfUpdates == 0;
    }

    private void saveNewCounter(String login) {
        Query query = em.createNativeQuery(CREATE_SQL);

        query.setParameter("login", login);

        query.executeUpdate();
    }

    private int updateCounter(String login) {
        Query query = em.createNativeQuery(INCREMENT_COUNTER_SQL);

        query.setParameter("login", login);

        return query.executeUpdate();
    }
}
