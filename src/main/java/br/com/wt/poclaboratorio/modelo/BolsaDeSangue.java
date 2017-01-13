package br.com.wt.poclaboratorio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BolsaDeSangue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Doador doador;
	private String tipoSanguineo;
	private String observacao;
	private String numRastreamento;
	private String dataColeta;
	@ManyToOne(cascade ={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "laboratorio_id")
	@JsonBackReference
	private Laboratorio laboratorio;

	
	public BolsaDeSangue() {}

	

	public BolsaDeSangue(Long id, Doador doador, String tipoSanguineo, String observacao, String numRastreamento,
			Laboratorio laboratorio) {
		super();
		this.id = id;
		this.doador = doador;
		this.tipoSanguineo = tipoSanguineo;
		this.observacao = observacao;
		this.numRastreamento = numRastreamento;
		this.laboratorio = laboratorio;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	

	public String getNumRastreamento() {
		return numRastreamento;
	}



	public void setNumRastreamento(String numRastreamento) {
		this.numRastreamento = numRastreamento;
	}
	

	public String getDataColeta() {
		return dataColeta;
	}



	public void setDataColeta(String dataColeta) {
		this.dataColeta = dataColeta;
	}



	@Override
	public String toString() {
		return "BolsaDeSangue [id=" + id + ", doador=" + doador + ", tipoSanguineo=" + tipoSanguineo + ", observacao="
				+ observacao + ", numRastreamento=" + numRastreamento + "]";
	}

}
