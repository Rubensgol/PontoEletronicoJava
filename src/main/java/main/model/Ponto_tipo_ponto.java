package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PONTO_HAS_TIPO_PONTO")
public class Ponto_tipo_ponto {
	public Ponto_tipo_ponto() {
		
	}
	@Id
	@Column(name="ponto_id")
	private int pontoid;
	
	
	@Column(name="tipo_ponto_id")
	private int tipopontoid;


	public Ponto_tipo_ponto(int pontoid, int tipopontoid) {
		super();
		this.pontoid = pontoid;
		this.tipopontoid = tipopontoid;
	}


	public int getPontoid() {
		return pontoid;
	}


	public void setPontoid(int pontoid) {
		this.pontoid = pontoid;
	}


	public int getTipopontoid() {
		return tipopontoid;
	}


	public void setTipopontoid(int tipopontoid) {
		this.tipopontoid = tipopontoid;
	}

}
