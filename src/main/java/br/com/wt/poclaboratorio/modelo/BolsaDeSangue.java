package br.com.wt.poclaboratorio.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BolsaDeSangue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "id_doador")
	private Doador doador;
	@Enumerated(EnumType.STRING)
	private TipoSanguineo tipoSanguine;
	@OneToOne(mappedBy = "id_laboratorio")
	private Laboratorio laboratorio;

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
