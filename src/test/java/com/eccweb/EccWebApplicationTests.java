package com.eccweb;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.eccpi.EccPiApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EccPiApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8181")

public class EccWebApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCurrLogin() {
		final String result = doAuthenticatedExchange("kamal", HttpMethod.GET, "/rest/currLogin",null,"test");
		assertTrue(result.contains("\"username\":\"kamal\""));
		//assertTrue(result.contains("\"roles\":[\"USER\"]"));
	}
	
	

	private String doAnonymousExchange(final HttpMethod method, final String path, String request) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> testRequest = new HttpEntity<>(request, httpHeaders);
		HttpEntity<String> testResponse = restTemplate.exchange("http://localhost:8181/" + path, method, testRequest,
				String.class);
		return testResponse.getBody();
	}

	private String doAuthenticatedExchange(final String user, final HttpMethod method, final String path,
			String request, String password) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> login = new HttpEntity<>(
				"{\"username\":\"" + user + "\",\"password\":\"" + password + "\"}", httpHeaders);
		ResponseEntity<Void> results = restTemplate.postForEntity("http://localhost:8181/rest/login", login, Void.class);

		httpHeaders.add("X-AUTH-TOKEN", results.getHeaders().getFirst("X-AUTH-TOKEN"));
		HttpEntity<String> testRequest = new HttpEntity<>(request, httpHeaders);
		HttpEntity<String> testResponse = restTemplate.exchange("http://localhost:8181/" + path, method, testRequest,
				String.class);
		return testResponse.getBody();
	}
}
