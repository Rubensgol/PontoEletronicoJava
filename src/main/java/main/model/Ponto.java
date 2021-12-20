package main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PONTO")
public class Ponto {
	public Ponto() {

	}
	public Ponto(String registro, String funcionario_cpf) {
		this.registro = registro;
		this.funcionariocpf = funcionario_cpf;
	}
	public Ponto(String registro, String funcionario_cpf, Date momento) {
		this.registro = registro;
		this.funcionariocpf = funcionario_cpf;
		this.momento=momento;
	}
	public Ponto(int id, Date momento, String registro, String funcionariocpf) {
		super();
		this.id = id;
		this.momento = momento;
		this.registro = registro;
		this.funcionariocpf = funcionariocpf;
	}
	@Id
	private int id;
	private Date momento;
	public Date getMomento() {
		return momento;
	}
	public void setMomento(Date momento) {
		this.momento = momento;
	}
	public String getFuncionariocpf() {
		return funcionariocpf;
	}
	public void setFuncionariocpf(String funcionariocpf) {
		this.funcionariocpf = funcionariocpf;
	}
	private String registro;

	@Column(name = "funcionario_cpf")
	private String funcionariocpf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getFuncionario_cpf() {
		return funcionariocpf;
	}

	public void setFuncionario_cpf(String funcionario_cpf) {
		this.funcionariocpf = funcionario_cpf;
	}

}
