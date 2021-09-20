package com.hari.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hari.model.Question;
import com.hari.service.ServeyService;

@RestController
public class ServeyController {

	@Autowired
	private ServeyService serveyService;

	@GetMapping("/serveys/{serveyId}/questions")
	public List<Question> retriveQuestionsForServey(@PathVariable String serveyId) {
		return serveyService.retriveQuestions(serveyId);
	}

	@PostMapping("/serveys/{serveyId}/questions")
	public ResponseEntity<Void> retriveQuestionToServey(@PathVariable String serveyId, @RequestBody Question question) {
		final Question question2 = serveyService.addQuestion(serveyId, question);
		if (question2 == null) {
			return ResponseEntity.noContent().build();
		}
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(question2.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/serveys/{serveyId}/questions/{questionId}")
	public Question retriveQuestion(@PathVariable String serveyId, @PathVariable String questionId) {
		return serveyService.retriveQuestion(serveyId, questionId);
	}
}
