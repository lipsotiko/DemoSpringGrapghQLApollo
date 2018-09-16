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

		HelloWorld helloWorld1 = new HelloWorld();
		helloWorld1.setName("vango 1");
		helloWorldRepository.saveAndFlush(helloWorld1);

		HelloWorld helloWorld2 = new HelloWorld();
		helloWorld2.setName("vango 2");
		helloWorldRepository.saveAndFlush(helloWorld2);

		HelloWorld helloWorld3 = new HelloWorld();
		helloWorld3.setName("vango 3");
		helloWorldRepository.saveAndFlush(helloWorld3);
	}

	@After
	public void tearDown() {
		helloWorldRepository.deleteAll();
	}

	@Test
	public void getHelloWorldsWithGraphQL() throws IOException {
		String body = "{ \"query\": \"query { getHelloWorlds { id name } }\", \"variables\": null }";
		httpEntity = new HttpEntity<>(body, headers);
		exchange = restTemplate.exchange("/graphql", HttpMethod.POST, httpEntity, String.class);

		QueryResponse queryResponse = mapper.readValue(exchange.getBody(), QueryResponse.class);
		assertThat(queryResponse.data.getHelloWorlds.size()).isEqualTo(3);
	}

	@Test
	public void getHelloWorldsByNameWithGraphQL() throws IOException {
		String body = "{ \"query\": \"query getHelloWorldsByName($name: String!) { " +
				"getHelloWorldsByName(name: $name) { id name } } \", \"variables\": {\"name\": \"vango 1\"} }";
		httpEntity = new HttpEntity<>(body, headers);
		exchange = restTemplate.exchange("/graphql", HttpMethod.POST, httpEntity, String.class);

		QueryResponse queryResponse = mapper.readValue(exchange.getBody(), QueryResponse.class);
		assertThat(queryResponse.data.getHelloWorldsByName.size()).isEqualTo(1);
		assertThat(queryResponse.data.getHelloWorldsByName.get(0).getName()).isEqualTo("vango 1");
	}

}
