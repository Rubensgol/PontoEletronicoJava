package main.controler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import main.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, String>{
	public Administrador findByLogin(String login);
}
