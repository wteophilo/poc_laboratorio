package br.com.wt.poclaboratorio.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BolsaDeSangueRepository extends CrudRepository<BolsaDeSangue,Long>{

}
