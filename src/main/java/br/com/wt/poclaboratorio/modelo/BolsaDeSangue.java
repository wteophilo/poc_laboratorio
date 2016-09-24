package br.com.wt.poclaboratorio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BolsaDeSangue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade=CascadeType.ALL)
	private Doador doador;
	@Enumerated(EnumType.STRING)
	private TipoSanguineo tipoSanguine;
	@ManyToOne
	@JoinColumn(name = "laboratorio_id")
	private Laboratorio laboratorio;
	
	public BolsaDeSangue() {}

	public BolsaDeSangue(Doador doador, TipoSanguineo tipoSanguine, Laboratorio laboratorio) {
		super();
		this.doador = doador;
		this.tipoSanguine = tipoSanguine;
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

	public TipoSanguineo getTipoSanguine() {
		return tipoSanguine;
	}

	public void setTipoSanguine(TipoSanguineo tipoSanguine) {
		this.tipoSanguine = tipoSanguine;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	@Override
	public String toString() {
		return "BolsaDeSangue [id=" + id + ", doador=" + doador + ", tipoSanguine=" + tipoSanguine + ", laboratorio="
				+ laboratorio + "]";
	}

}
