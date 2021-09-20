package com.hari.test;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import com.hari.SpringBootServeyIntegrationTestExample;
import com.hari.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootServeyIntegrationTestExample.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class ServeyControllerIntegrationTestWithSecurity {

	final TestRestTemplate restTemplate = new TestRestTemplate();
	final HttpHeaders headers = createHttpHeaders("user1", "user1");// new HttpHeaders();

	@LocalServerPort
	private int localServerPost;

	@Before
	public void before() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	private HttpHeaders createHttpHeaders(String user, String password) {
		final HttpHeaders headers = new HttpHeaders();
		final String auth = user + ":" + password;
		final byte[] encodedAuth = Base64Utils.encode(auth.getBytes(Charset.forName("US-ASCII")));
		final String headerValue = "Basic " + new String(encodedAuth);
		headers.add("Authorization", headerValue);
		return headers;
	}

	@Test
	void test() {
		final String url = createURL("/serveys/Servey1/questions/Q1");
		final HttpEntity<String> entity = new HttpEntity<>(null, headers);
		final ResponseEntity<String> responseOutput = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println("Response:: " + responseOutput.getBody());
	}

	@Test
	void testJsonAssert() throws JSONException {
		/**
		 * @Signature JSONAssert.assertEquals(expected, actual, strict);
		 */
		JSONAssert.assertEquals("{id:1}", "{id:1;name:hari}", false);
	}

	@Test
	void testRetriveServeyQuestionJsonAssert() throws JSONException {
		final String url = createURL("/serveys/Servey1/questions/Q1");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		final HttpEntity<String> entity = new HttpEntity<>(null, headers);
		final ResponseEntity<String> responseOutput = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		final String expected = "{id:Q1; correctAnswer:Russia; options:[India, USA, Chaina, Russia]}";
		JSONAssert.assertEquals(expected, responseOutput.getBody(), false);
	}

	@Test
	void testRetriveServeyQuestionJsonAssertSimplyfy() throws JSONException {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		final String expected = "{id:Q1; correctAnswer:Russia; options:[India, USA, Chaina, Russia]}";
		JSONAssert.assertEquals(expected, restTemplate.exchange(createURL("/serveys/Servey1/questions/Q1"),
				HttpMethod.GET, new HttpEntity<>(null, headers), String.class).getBody(), false);
	}

	@Test
	void testRetriveServeyQuestionsJsonAssert() throws JSONException {
		final ResponseEntity<List<Question>> response = restTemplate.exchange(createURL("/serveys/Servey1/questions"),
				HttpMethod.GET, new HttpEntity<String>(null, headers),
				new ParameterizedTypeReference<List<Question>>() {
				});
		final Question sampleQuestion = new Question("Q2", "Most population Country in World", "Chaina",
				Arrays.asList("India", "Russia", "USA", "Chaina"));
		assertTrue(response.getBody().contains(sampleQuestion));
	}

	@Test
	void addQuestion() throws JSONException {
		final Question newQuestion = new Question("EMPTY", "QuestionFromTest1", "India",
				Arrays.asList("India", "Russia", "USA", "Chaina"));
		final HttpEntity<Question> entity = new HttpEntity<>(newQuestion, headers);
		final ResponseEntity<String> responseOutput = restTemplate.exchange(createURL("/serveys/Servey1/questions"),
				HttpMethod.POST, entity, String.class);
		final String actual = responseOutput.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println(actual);
		assertTrue(actual.contains("/serveys/Servey1/questions"));
	}

	private String createURL(String uri) {
		return "http://localhost:" + localServerPost + uri;
	}

}
