package main.controler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import main.model.TipoPonto;

public interface TipoPontoRepository extends JpaRepository<TipoPonto,Integer>{
}
