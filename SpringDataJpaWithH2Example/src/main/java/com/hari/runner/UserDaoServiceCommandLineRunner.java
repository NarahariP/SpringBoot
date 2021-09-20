package com.hari.runner;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hari.entity.User;
import com.hari.repository.UserRepository;
import com.hari.service.UserDaoService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		final User user1 = new User("User1", "Role");
		final User user2 = new User("User2", "Role");
		userDaoService.insert(user1);
		logger.info("User Details with Jpa:: " + user1);
		final User returnedUser = userRepository.save(user2);
		logger.info("User Deatils with Repository:: " + returnedUser);

		final Optional<User> optionalUser = userRepository.findById(1L);
		if (optionalUser.isPresent()) {
			logger.info("Optional User Details:: " + optionalUser.get());
		}

		final List<User> usersList = userRepository.findAll();
		usersList.stream().forEach(System.out::println);
	}

	/**
	 * To check data in Database user http://localhost:8080/h2-console DB URL:
	 * jdbc:h2:mem:testdb username: sa, password: (empty)
	 */
}
