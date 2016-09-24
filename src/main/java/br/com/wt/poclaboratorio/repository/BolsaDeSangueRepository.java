package br.com.wt.poclaboratorio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.wt.poclaboratorio.modelo.BolsaDeSangue;

@RepositoryRestResource
public interface BolsaDeSangueRepository extends CrudRepository<BolsaDeSangue,Long>{

}
