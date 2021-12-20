package main.controler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

}
