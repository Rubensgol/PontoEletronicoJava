package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CARGO")
public class Cargo {
	public Cargo() {
		
	}
	public Cargo(int id, String descricao, String atribuicao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.atribuicao = atribuicao;
	}
	@Id
	@Column(name="id_cargo")
	private int id;
	private String descricao;
	private String atribuicao;
	
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
	public String getAtribuicao() {
		return atribuicao;
	}
	public void setAtribuicao(String atribuicao) {
		this.atribuicao = atribuicao;
	}

}
