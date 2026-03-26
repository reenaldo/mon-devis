package com.mondevis.api.service;

import com.mondevis.api.dto.ClientRequest;
import com.mondevis.api.dto.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse createClient(ClientRequest request);

    List<ClientResponse> getAllClients();

    ClientResponse getClientById(Long id);
}
