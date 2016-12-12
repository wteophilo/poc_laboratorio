package br.com.wt.poclaboratorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.wt.poclaboratorio.modelo.BolsaDeSangue;
import br.com.wt.poclaboratorio.modelo.Doador;
import br.com.wt.poclaboratorio.modelo.Laboratorio;

@RepositoryRestResource
public interface BolsaDeSangueRepository extends JpaRepository<BolsaDeSangue,Long>{
	List<BolsaDeSangue> findBytipoSanguineo(String tipoSanguineo);
	BolsaDeSangue findBydoador(Doador doador);
	List<BolsaDeSangue> findBylaboratorio(Laboratorio laboratorio);
}
