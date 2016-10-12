package br.com.wt.poclaboratorio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Laboratorio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String endereco;
	private String cep;
	private String email;
	@OneToMany(mappedBy = "laboratorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BolsaDeSangue> bolsasDeSangue;
	@OneToMany(mappedBy = "laboratorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> user;

	public Laboratorio() {
	}
	
	

	public Laboratorio(String nome, String endereco, String cep, String email, List<BolsaDeSangue> bolsasDeSangue) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.email = email;
		this.bolsasDeSangue = bolsasDeSangue;
	}



	public Laboratorio(Long id, String nome, String endereco, String cep, String email,
			List<BolsaDeSangue> bolsasDeSangue, List<User> user) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.email = email;
		this.bolsasDeSangue = bolsasDeSangue;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BolsaDeSangue> getBolsasDeSangue() {
		return bolsasDeSangue;
	}

	public void setBolsasDeSangue(List<BolsaDeSangue> bolsasDeSangue) {
		this.bolsasDeSangue = bolsasDeSangue;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Laboratorio [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", email="
				+ email + ", bolsasDeSangue=" + bolsasDeSangue + ", user=" + user + "]";
	}

}
