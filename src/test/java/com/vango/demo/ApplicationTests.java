package com.vango.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vango.demo.graphql.response.QueryResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired private TestRestTemplate restTemplate;
	@Autowired private HelloWorldRepository helloWorldRepository;
	private HttpHeaders headers = new HttpHeaders();
	private ObjectMapper mapper = new ObjectMapper();
	private HttpEntity<String> httpEntity;
	private ResponseEntity<String> exchange;

	@Before
	public void setUp() {
		headers.setContentType(MediaType.APPLICATION_JSON);

		for(int i = 1; i <= 10; i++) {
			HelloWorld helloWorld = new HelloWorld();
			helloWorld.setName(String.format("vango %s", i));
			helloWorldRepository.saveAndFlush(helloWorld);
		}
	}

	@After
	public void tearDown() {
		helloWorldRepository.deleteAll();
	}

	@Test
	public void getHelloWorldsWithGraphQL() throws IOException {
		String query = "{ \"query\": \"query { getHelloWorlds { id name } }\", \"variables\": null }";
		QueryResponse queryResponse = executeGraphQL(query);
		assertThat(queryResponse.data.getHelloWorlds.size()).isEqualTo(10);
	}

	@Test
	public void getHelloWorldsByNameWithGraphQL() throws IOException {
		String query = "{ \"query\": \"query getHelloWorldsByName($name: String!) { " +
				"getHelloWorldsByName(name: $name) { id name } } \", \"variables\": {\"name\": \"vango 1\"} }";
		QueryResponse queryResponse = executeGraphQL(query);
		assertThat(queryResponse.data.getHelloWorldsByName.size()).isEqualTo(1);
		assertThat(queryResponse.data.getHelloWorldsByName.get(0).getName()).isEqualTo("vango 1");
	}

	private QueryResponse executeGraphQL(String query) throws IOException {
		httpEntity = new HttpEntity<>(query, headers);
		exchange = restTemplate.exchange("/graphql", HttpMethod.POST, httpEntity, String.class);
		return mapper.readValue(exchange.getBody(), QueryResponse.class);
	}

}
