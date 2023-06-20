package rest.api.orders.rest.api.orders.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rest.api.orders.rest.api.orders.domain.Client;
import rest.api.orders.rest.api.orders.dto.ClientCreateDto;
import rest.api.orders.rest.api.orders.repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid ClientCreateDto clientCreateDto, UriComponentsBuilder uriComponentsBuilder){
        var client = new Client(clientCreateDto);
        var uri  = uriComponentsBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

        clientRepository.save(client);

        return ResponseEntity.created(uri).body(client);

    }

    @GetMapping
    public ResponseEntity<Object> index(){
        var products = clientRepository.findAll();

        return ResponseEntity.ok(products.stream().toList());
    }
}
