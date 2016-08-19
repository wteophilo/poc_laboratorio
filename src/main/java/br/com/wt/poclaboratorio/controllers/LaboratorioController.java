package br.com.wt.poclaboratorio.controllers;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.wt.poclaboratorio.modelo.Laboratorio;
import br.com.wt.poclaboratorio.modelo.LaboratorioRepository;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@RequestMapping(value = "/",method = RequestMethod.POST ,produces = "application/json")
	public ResponseEntity<Void> add(@RequestBody Laboratorio laboratorio ,UriComponentsBuilder ucBuilder){
		System.out.println("Criado um nova data ");
		HttpHeaders headers = new HttpHeaders();
		try{
			laboratorioRepository.save(laboratorio);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(laboratorio.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}catch(RuntimeErrorException e){
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.PRECONDITION_FAILED);
		}
  
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET,  produces = "application/json")
	public List<Laboratorio> lista() {
		List<Laboratorio> laboratorios = (List<Laboratorio>) laboratorioRepository.findAll();
		return laboratorios;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Laboratorio getLaboratorio(@PathVariable Long id) {
		Laboratorio laboratorio = laboratorioRepository.findOne(id);
		return laboratorio;
	}
	
	@RequestMapping(value = "/doador/update",method = RequestMethod.PUT ,produces = "application/json")
	public ResponseEntity<Void> update(@PathVariable long id,@RequestBody Laboratorio laboratorio,UriComponentsBuilder ucBuilder){
		
		Laboratorio laboratorioBD = laboratorioRepository.findOne(id);
		laboratorioBD.setCep(laboratorio.getCep());
		laboratorioBD.setEmail(laboratorio.getEmail());
		laboratorioBD.setEndereco(laboratorio.getEndereco());
		laboratorioBD.setNome(laboratorio.getNome());
		
		laboratorioRepository.save(laboratorioBD);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(laboratorio.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	

}
