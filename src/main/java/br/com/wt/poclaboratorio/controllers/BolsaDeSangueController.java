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
import br.com.wt.poclaboratorio.modelo.Doador;
import br.com.wt.poclaboratorio.modelo.Laboratorio;
import br.com.wt.poclaboratorio.repository.BolsaDeSangueRepository;


@RestController
@RequestMapping("/bolsa")
public class BolsaDeSangueController {
	
	@Autowired
	private BolsaDeSangueRepository bolsaDeSangueRepository;
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> add(@RequestBody BolsaDeSangue bolsa, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		try {
			bolsaDeSangueRepository.save(bolsa);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsa.getId()).toUri());
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
		return new ResponseEntity<>(bolsaDeSangues, HttpStatus.OK);
	}

	
	@RequestMapping(value="/listaTiposSanguineo",method=RequestMethod.GET,produces = "application/json")
	public ResponseEntity<List<BolsaDeSangue>> listaPorTipoSanguineo(@PathVariable String tipoSanguineo) {
		List<BolsaDeSangue>bolsaDeSangues =(List<BolsaDeSangue>) bolsaDeSangueRepository.findBytipoSanguineo(tipoSanguineo);
		if (bolsaDeSangues == null) {
			return new ResponseEntity<>(bolsaDeSangues, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolsaDeSangues, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/listaBolsasPorLaboratorio",method=RequestMethod.GET,produces = "application/json")
	public ResponseEntity<List<BolsaDeSangue>> listaPorTipoSanguineo(@RequestBody Laboratorio laboratorio) {
		List<BolsaDeSangue>bolsaDeSangues =(List<BolsaDeSangue>) bolsaDeSangueRepository.findBylaboratorio(laboratorio);
		if (bolsaDeSangues == null) {
			return new ResponseEntity<>(bolsaDeSangues, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolsaDeSangues, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/buscaBolsaPorDoador",method=RequestMethod.GET,produces = "application/json")
	public ResponseEntity<BolsaDeSangue> buscaBolsaPorDoador(@RequestBody Doador doador) {
		BolsaDeSangue bolsa = bolsaDeSangueRepository.findBydoador(doador);
		if (bolsa == null) {
			return new ResponseEntity<BolsaDeSangue>(bolsa, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BolsaDeSangue>(bolsa, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BolsaDeSangue> getBolsaDeSangue(@PathVariable Long id) {
		BolsaDeSangue bolsaDeSangue = bolsaDeSangueRepository.findOne(id);
		if (bolsaDeSangue == null) {
			return new ResponseEntity<>(bolsaDeSangue, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolsaDeSangue, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/atualizaPorId/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody BolsaDeSangue bolsaDeSangue,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		BolsaDeSangue bolsaDeSangueBD = bolsaDeSangueRepository.findOne(id);

		if (bolsaDeSangueBD == null) {
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
		}

		bolsaDeSangueBD.setDoador(bolsaDeSangue.getDoador());
		bolsaDeSangueBD.setLaboratorio(bolsaDeSangue.getLaboratorio());
		bolsaDeSangueBD.setTipoSanguineo(bolsaDeSangue.getTipoSanguineo());

		bolsaDeSangueRepository.save(bolsaDeSangueBD);
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(bolsaDeSangue.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/deletaPorId/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		BolsaDeSangue bolsaDeSangue = bolsaDeSangueRepository.findOne(id);
		if (bolsaDeSangue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		bolsaDeSangueRepository.delete(bolsaDeSangue);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
