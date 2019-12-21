package com.hari.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hari.model.Question;
import com.hari.model.Servey;

@Component
public class ServeyService {

	private final SecureRandom random = new SecureRandom();

	private static List<Servey> serveys = new ArrayList<>();

	static {
		final Question question1 = new Question("Q1", "Largest Country in World", "Russia",
				Arrays.asList("India", "Russia", "USA", "Chaina"));

		final Question question2 = new Question("Q2", "Most population Country in World", "Chaina",
				Arrays.asList("India", "Russia", "USA", "Chaina"));

		final Question question3 = new Question("Q3", "Highest GDP in World", "USA",
				Arrays.asList("India", "Russia", "USA", "Chaina"));

		final Question question4 = new Question("Q4", "Second largest english speaking Country in World", "India",
				Arrays.asList("India", "Russia", "USA", "Chaina"));

		final List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3, question4));

		final Servey servey = new Servey("Servey1", "First Servey", "Description of Servey", questions);
		serveys.add(servey);
	}

	public List<Servey> retriveServeys() {
		return serveys;
	}

	public Servey retriveServey(String serveyId) {
		return serveys.stream().filter(servey -> servey.getId().equals(serveyId)).findAny().orElse(new Servey());
	}

	public List<Question> retriveQuestions(String serveyId) {
		return retriveServey(serveyId).getQuestions();
	}

	public Question retriveQuestion(String serveyId, String questionId) {
		return retriveServey(serveyId).getQuestions().stream().filter(question -> question.getId().equals(questionId))
				.findAny().orElse(null);
	}

	public Question addQuestion(String serveyId, Question question) {

		final Servey servey = retriveServey(serveyId);
		if (servey == null) {
			return null;
		}
		final String randonId = new BigInteger(100, random).toString(32);
		question.setId(randonId);
		servey.getQuestions().add(question);
		return question;
	}

}
