package com.digitalinnovationone.springjpa.controller;

import com.digitalinnovationone.springjpa.model.Carro;
import com.digitalinnovationone.springjpa.model.Cliente;
import com.digitalinnovationone.springjpa.model.Multa;
import com.digitalinnovationone.springjpa.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> clientes(@PathVariable("id") Integer id) {
        Cliente cliente = clienteRepository.findClienteById(id);
        Carro carro = cliente.getCarros().stream().findFirst().get();
//        carro.setCliente(null);
        Multa multa = carro.getMultas().stream().findFirst().get();
//        multa.setCarro(null);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/salvar")
    public ResponseEntity<Void> clientes() {
        Carro carro = new Carro();
        carro.setMarca("Ford");
        carro.setModelo("Fusion");

        Multa multa = new Multa();
        multa.setValor(100.00);
        multa.setData(LocalDateTime.now());
        multa.setCarro(carro);
        Cliente cliente = new Cliente();
        cliente.setNome("Joao");
        cliente.setCarros(Collections.singleton(carro));
        carro.setCliente(cliente);
        carro.setMultas(Collections.singleton(multa));
        cliente.setCarros(Collections.singleton(carro));
        clienteRepository.save(cliente);
        return ResponseEntity.ok().body(null);
    }
}
