package com.mondevis.api.service.impl;

import com.mondevis.api.dto.ClientRequest;
import com.mondevis.api.dto.ClientResponse;
import com.mondevis.api.dto.mapper.ClientMapper;
import com.mondevis.api.entity.Client;
import com.mondevis.api.exception.DuplicateResourceException;
import com.mondevis.api.exception.ResourceNotFoundException;
import com.mondevis.api.repository.ClientRepository;
import com.mondevis.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        log.debug("Creating client with email: {}", request.getEmail());

        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "A client with email '" + request.getEmail() + "' already exists");
        }

        Client client = clientMapper.toEntity(request);
        Client saved = clientRepository.save(client);

        log.info("Client created with id: {}", saved.getId());
        return clientMapper.toResponse(saved);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        log.debug("Fetching all clients");
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse getClientById(Long id) {
        log.debug("Fetching client with id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        return clientMapper.toResponse(client);
    }
}
