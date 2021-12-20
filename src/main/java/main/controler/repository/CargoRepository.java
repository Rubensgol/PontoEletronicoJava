package main.controler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{
	public Cargo findByAtribuicao(String atribuicao);
}
