package com.hari.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hari.controller.ServeyController;
import com.hari.model.Question;
import com.hari.service.ServeyService;

@RunWith(SpringRunner.class)
@WebMvcTest(ServeyController.class)
class ServeyControllerTestWithSecurity {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ServeyService serveyService;

	@WithMockUser(username = "user1", password = "user1")
	@Test
	void test() throws Exception {
		final Question mockitoQuestion = new Question("Q1", "Largest Country in World", "Russia",
				Arrays.asList("India", "Russia", "USA", "Chaina"));
		// Condition
		Mockito.when(serveyService.retriveQuestion(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mockitoQuestion);
		// Call service
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/serveys/Servey1/questions/Q1")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		final String expected = "{id:Q1; correctAnswer:Russia}";
		// Assert
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@WithMockUser(username = "user1", password = "user1")
	@Test
	void createQuestion() throws Exception {
		final Question mockQuestion = new Question("1", "SmallNumber", "1", Arrays.asList("1", "2", "3", "4"));
		final String question = "{\"description\":\"SmallNumber\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		Mockito.when(serveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
				.thenReturn(mockQuestion);
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/serveys/Servey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(question).contentType(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		final MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * @WebMvcTest(value = SurveyController.class, secure = false)
	 *
	 * secure=false is deprecated.
	 *
	 * Instead, you would need to use @WithMockUser
	 *
	 * Add this to pom.xml
	 *
	 * <dependency> <groupId>org.springframework.security</groupId>
	 * <artifactId>spring-security-test</artifactId> <scope>test</scope>
	 * </dependency>
	 *
	 * Use @WithMockUser to use a mock user as follow:
	 * ==============================================
	 *
	 * @WithMockUser(username = "user1", password = "secret1")
	 *
	 * @Test public void yourTest() throws Exception {...}
	 */
}
