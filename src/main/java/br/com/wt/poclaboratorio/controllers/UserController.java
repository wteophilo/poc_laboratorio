package br.com.wt.poclaboratorio.controllers;

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

import br.com.wt.poclaboratorio.config.Cripto;
import br.com.wt.poclaboratorio.exception.LaboratorioNotFound;
import br.com.wt.poclaboratorio.modelo.User;
import br.com.wt.poclaboratorio.repository.LaboratorioRepository;
import br.com.wt.poclaboratorio.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	private Cripto cripto;
	
	@RequestMapping(value = "{labId}/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> add(@PathVariable String labId,@RequestBody User user, UriComponentsBuilder ucBuilder) {
		validateLab(Long.parseLong("labId"));
		HttpHeaders headers = new HttpHeaders();
		try {
			cripto = new Cripto();
			user.setPassword(cripto.encrypt(user.getPassword()));
			userRepository.save(user);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (RuntimeErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<User> findUser(@RequestBody String username,@RequestBody String senha , UriComponentsBuilder ucBuilder) {
		cripto = new Cripto();
		senha =cripto.encrypt(senha);
		User user = userRepository.findByUser(username,senha);
		if (user == null) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	private void validateLab(Long labId) {
		this.laboratorioRepository.findByID(labId).orElseThrow(
				() -> new LaboratorioNotFound(labId));
	}
}