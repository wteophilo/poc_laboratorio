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
import br.com.wt.poclaboratorio.repository.LaboratorioRepository;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", headers="Accept=application/json")
	public ResponseEntity<Void> add(@RequestBody Laboratorio laboratorio, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		try {
			laboratorioRepository.save(laboratorio);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(laboratorio.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (RuntimeErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Laboratorio>> lista() {
		
		List<Laboratorio> laboratorios = (List<Laboratorio>) laboratorioRepository.findAll();
		if (laboratorios == null) {
			return new ResponseEntity<>(laboratorios, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(laboratorios, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Laboratorio> getLaboratorio(@PathVariable Long id) {
		Laboratorio laboratorio = laboratorioRepository.findOne(id);
		if (laboratorio == null) {
			return new ResponseEntity<>(laboratorio, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(laboratorio, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Laboratorio laboratorio,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		Laboratorio laboratorioBD = laboratorioRepository.findOne(id);

		if (laboratorioBD == null) {
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
		}

		laboratorioBD.setCep(laboratorio.getCep());
		laboratorioBD.setEmail(laboratorio.getEmail());
		laboratorioBD.setEndereco(laboratorio.getEndereco());
		laboratorioBD.setNome(laboratorio.getNome());

		laboratorioRepository.save(laboratorioBD);
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(laboratorio.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Laboratorio laboratorio = laboratorioRepository.findOne(id);
		if (laboratorio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		laboratorioRepository.delete(laboratorio);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
