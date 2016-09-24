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
import br.com.wt.poclaboratorio.repository.BolsaDeSangueRepository;


@RestController
@RequestMapping("/bolsa")
public class BolsaDeSangueController {
	
	@Autowired
	private BolsaDeSangueRepository bolsaDeSangueRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> add(@RequestBody BolsaDeSangue bolsaDeSangue, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		try {
			bolsaDeSangueRepository.save(bolsaDeSangue);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsaDeSangue.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (RuntimeErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<BolsaDeSangue>> lista() {
		List<BolsaDeSangue> bolsaDeSangues = (List<BolsaDeSangue>) bolsaDeSangueRepository.findAll();
		if (bolsaDeSangues == null) {
			return new ResponseEntity<>(bolsaDeSangues, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolsaDeSangues, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BolsaDeSangue> getBolsaDeSangue(@PathVariable Long id) {
		BolsaDeSangue bolsaDeSangue = bolsaDeSangueRepository.findOne(id);
		if (bolsaDeSangue == null) {
			return new ResponseEntity<>(bolsaDeSangue, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolsaDeSangue, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody BolsaDeSangue bolsaDeSangue,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		BolsaDeSangue bolsaDeSangueBD = bolsaDeSangueRepository.findOne(id);

		if (bolsaDeSangueBD == null) {
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
		}

		bolsaDeSangueBD.setDoador(bolsaDeSangue.getDoador());
		bolsaDeSangueBD.setLaboratorio(bolsaDeSangue.getLaboratorio());
		bolsaDeSangueBD.setTipoSanguine(bolsaDeSangue.getTipoSanguine());

		bolsaDeSangueRepository.save(bolsaDeSangueBD);
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsaDeSangue.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		BolsaDeSangue bolsaDeSangue = bolsaDeSangueRepository.findOne(id);
		if (bolsaDeSangue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		bolsaDeSangueRepository.delete(bolsaDeSangue);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
