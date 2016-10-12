package poclaboratorio;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
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
import br.com.wt.poclaboratorio.config.Cripto;
import br.com.wt.poclaboratorio.modelo.Laboratorio;
import br.com.wt.poclaboratorio.modelo.User;
import br.com.wt.poclaboratorio.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=8081" })
public class UserControllerTest {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private User user;
	private List<User> users;
	private Cripto cripto;
	private Laboratorio lab;

	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		this.users = userRepository.findAll();
		this.lab = new Laboratorio();
		this.lab.setId(new Long(34));
		this.cripto = new Cripto();
		if (this.users == null) {

			this.users.add(userRepository.save(new User("Lucas", cripto.encrypt("123"), lab)));
			this.users.add(userRepository.save(new User("Ana Julia", cripto.encrypt("321"), lab)));
		}
	}

	@Test
	public void listaUsuarios() throws Exception {
		mockMvc.perform(get("/user/" + this.users.get(0).getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(this.users.get(0).getId().intValue())));
	}

	@Test
	public void findUser() throws Exception {
		mockMvc.perform(get("/user/" + this.users.get(0).getUsername(), this.users.get(0).getPassword()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.username", is(this.users.get(0).getUsername())));
	}

	@Test
	public void adicionarUser() throws Exception {
		String UserJson = json(new User("João", cripto.encrypt("123"), lab));
		this.mockMvc.perform(post("/" + this.lab.getId() + "/user/").contentType(contentType).content(UserJson))
				.andExpect(status().isCreated());
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
