package com.petstore;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.model.Category;
import com.petstore.model.Pet;
import com.petstore.model.Tag;
import com.petstore.service.PetRepository;
import com.petstore.utils.StatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetStoreApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private PetRepository petRepository ;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private Pet pet = new Pet(new Category("CAT"), "Jackie", new ArrayList<String>(
			Arrays.asList("A", "B")), new ArrayList<Tag>() {
		{
			add(new Tag("Tag1"));
			add(new Tag("Tag2"));
		}
	}, StatusEnum.available);


	@Test
	public void findPetById() throws Exception 
	{
		Pet fPet = petRepository.save(pet);

		this.mockMvc.perform(get("/pet/" + fPet.getId()).with(httpBasic("bill", "abc123")).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name").value("Jackie"));

		petRepository.delete(fPet.getId());
	}

	@Test
	public void removePetByid() throws Exception 
	{
		Pet fPet = petRepository.save(pet);

		this.mockMvc.perform(delete("/pet/"+ fPet.getId()).with(httpBasic("bill", "abc123")).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addPet() throws Exception 
	{
		MvcResult  result = this.mockMvc.perform(post("/pet/").with(httpBasic("bill", "abc123")).accept(MediaType.APPLICATION_JSON).content(asJsonString(pet))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();
		JsonNode rootNode = new ObjectMapper().readTree(content);

		petRepository.delete(rootNode.get("id").asLong());
	}


	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} 

}
