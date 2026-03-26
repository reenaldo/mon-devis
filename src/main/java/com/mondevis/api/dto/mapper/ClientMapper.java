package com.mondevis.api.dto.mapper;

import com.mondevis.api.dto.ClientRequest;
import com.mondevis.api.dto.ClientResponse;
import com.mondevis.api.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest request) {
        return Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }

    public ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}
