package poclaboratorio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.wt.poclaboratorio.config.Cripto;

public class CriptoTest {

	private Cripto cripto;
	private String senha;
	
	@Before
	public void setUp(){
		this.cripto = new Cripto();
		this.senha = "123";
	}
	
	@Test
	public void verificaCriptografia() {
		String senhaCriptografada = this.cripto.encrypt(this.senha);
		assertEquals("202cb962ac59075b964b07152d234b70", senhaCriptografada);
	}

}
