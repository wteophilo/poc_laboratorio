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

import br.com.wt.poclaboratorio.modelo.BolsaDeSangue;
import br.com.wt.poclaboratorio.modelo.BolsaDeSangueRepository;


@RestController
@RequestMapping("/bolsa")
public class BolsaDeSangueController {
	@Autowired
	private BolsaDeSangueRepository bolsaDeSangueRepository;
	
	@RequestMapping(value = "/",method = RequestMethod.POST ,produces = "application/json")
	public ResponseEntity<Void> add(@RequestBody BolsaDeSangue bolsaDeSangue ,UriComponentsBuilder ucBuilder){
		System.out.println("Criado um nova data ");
		HttpHeaders headers = new HttpHeaders();
		try{
			bolsaDeSangueRepository.save(bolsaDeSangue);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsaDeSangue.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}catch(RuntimeErrorException e){
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.PRECONDITION_FAILED);
		}
  
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET,  produces = "application/json")
	public List<BolsaDeSangue> lista() {
		List<BolsaDeSangue> bolsas = (List<BolsaDeSangue>) bolsaDeSangueRepository.findAll();
		return bolsas;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public BolsaDeSangue getLaboratorio(@PathVariable Long id) {
		BolsaDeSangue bolsaDeSangue = bolsaDeSangueRepository.findOne(id);
		return bolsaDeSangue;
	}
	
	@RequestMapping(value = "/doador/update",method = RequestMethod.PUT ,produces = "application/json")
	public ResponseEntity<Void> update(@PathVariable long id,@RequestBody BolsaDeSangue bolsaDeSangue,UriComponentsBuilder ucBuilder){		
		BolsaDeSangue bolsaDeSangueBD = bolsaDeSangueRepository.findOne(id);
		bolsaDeSangueRepository.save(bolsaDeSangueBD);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsaDeSangue.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
