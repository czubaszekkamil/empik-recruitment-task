package com.empik.githubadapter.empik.service;

import com.empik.githubadapter.empik.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    @Transactional
    public void refreshCounterFor(String login) {
        requestRepository.saveOrUpdateCounter(login);
    }
}
