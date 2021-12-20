package main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_PONTO")
public class TipoPonto {
	@Id
	private int id;
	private String descricao;
	public TipoPonto() {
		
	}
	
	public TipoPonto(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
