package com.digitalinnovationone.springjpa.repository;

import com.digitalinnovationone.springjpa.model.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @EntityGraph(attributePaths={"carros", "carros.multas"})
    Cliente findClienteById(Integer id);
}
