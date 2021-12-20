package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SETORES")
public class Setores {
	public Setores() {
		
	}
	@Id
	@Column(name="registros")
	private int registros;
	
	private String setor;

	public Setores(int registros, String setor) {
		super();
		this.registros = registros;
		this.setor = setor;
	}

	public int getRegistros() {
		return registros;
	}

	public void setRegistros(int registros) {
		this.registros = registros;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	

}
