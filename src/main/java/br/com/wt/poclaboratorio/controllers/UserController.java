package br.com.wt.poclaboratorio.controllers;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.wt.poclaboratorio.modelo.User;
import br.com.wt.poclaboratorio.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", headers="Accept=application/json")
	public ResponseEntity<Void> add(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		user = naoExiste(user); 
		try {
			userRepository.save(user);
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (RuntimeErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	
	
	private User naoExiste(User user){
		User userDB = userRepository.findByusername(user.getUsername());
		if (userDB == null){
			return user;
		}else{
			return userDB;
		}	
	}

}
