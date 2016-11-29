package br.com.wt.poclaboratorio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.wt.poclaboratorio.modelo.Laboratorio;


@RepositoryRestResource
public interface LaboratorioRepository extends JpaRepository<Laboratorio,Long>{
	 Laboratorio findByid(Long id);
	 Laboratorio findBycnpj(String cnpj);
}
