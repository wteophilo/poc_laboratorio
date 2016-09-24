package poclaboratorio;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.wt.poclaboratorio.Boot;
import br.com.wt.poclaboratorio.modelo.Laboratorio;
import br.com.wt.poclaboratorio.repository.LaboratorioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8081"})
public class LaboratorioControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc mockMvc;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private List<Laboratorio> laboratorios = new ArrayList<Laboratorio>();
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		this.laboratorios = laboratorioRepository.findAll();
		if (this.laboratorios == null){
			this.laboratorios.add(
					laboratorioRepository.save(new Laboratorio("Laboratorio_1", "Rua A", "00001-11", "lab1@lab1.com.br",null)));
			this.laboratorios.add(
					laboratorioRepository.save(new Laboratorio("Laboratorio_2", "Rua B", "00002-22", "lab2@lab2.com.br",null)));
		}
	}
	
	
	@Test
	public void listaLaboratorios() throws Exception {
		mockMvc.perform(get("/laboratorio/"+this.laboratorios.get(0).getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(this.laboratorios.get(0).getId().intValue())));
	}

	@Test
	public void adicionarLaboratorio() throws Exception {
		String laboratorioJson = json(new Laboratorio("Laboratorio_3", "Rua C", "00003-3", "lab2@lab3.com.br",null));
		this.mockMvc.perform(post("/laboratorio/").contentType(contentType).content(laboratorioJson)).andExpect(status().isCreated());
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
