package main.controler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.model.Ponto;

public interface PontoRepository extends JpaRepository<Ponto, Integer>{
	public List<Ponto> findByfuncionariocpf(String funcionariocpf);
	public long countByfuncionariocpf(String funcionariocpf);
	@Query(value="select max(id) from Ponto")
	public long findMaxPonto();
}
