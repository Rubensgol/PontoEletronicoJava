package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador {
	public Administrador() {
	
	}
	@Id
	@Column(name="funcionario_cpf")
	private String funcionario;
	
	public String getFuncionario() {
		return funcionario;
	}
	public Administrador(String funcionario, String senha, String login) {
		super();
		this.funcionario = funcionario;
		this.senha = senha;
		this.login = login;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	private String senha;
	private String login;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
