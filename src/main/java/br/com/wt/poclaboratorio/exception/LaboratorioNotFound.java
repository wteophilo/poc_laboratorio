package br.com.wt.poclaboratorio.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LaboratorioNotFound extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LaboratorioNotFound(String laboratorioId) {
		super("Não foi possível localizar o Laboratório:  "+laboratorioId);
	}	
}
