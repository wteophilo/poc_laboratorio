package br.com.wt.poclaboratorio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Laboratorio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String endereco;
	private String cep;
	private String email;
	private String senha;
	private String cnpj;
	private String telefone;
	@OneToMany(mappedBy = "laboratorio", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<BolsaDeSangue> bolsasDeSangue;
	
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
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<BolsaDeSangue> getBolsasDeSangue() {
		return bolsasDeSangue;
	}

	public void setBolsasDeSangue(List<BolsaDeSangue> bolsasDeSangue) {
		this.bolsasDeSangue = bolsasDeSangue;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Laboratorio [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", email="
				+ email + ", senha=" + senha + ", cnpj=" + cnpj + ", bolsasDeSangue=" + bolsasDeSangue + "]";
	}

}
