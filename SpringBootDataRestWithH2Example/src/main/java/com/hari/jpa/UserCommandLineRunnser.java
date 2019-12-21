package com.hari.jpa;

import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hari.model.User;
import com.hari.repository.UserRestRepository;

/**
 * @author Narahari
 *
 */
@Component
public class UserCommandLineRunnser implements CommandLineRunner {

	/**
	 * To open H2 console -> http://localhost:8080/h2-comsole Default :
	 * jdbc:h2:mem:testdb username: sa , password:
	 */
	private final static Logger logger = LoggerFactory.getLogger(UserCommandLineRunnser.class);

	@Autowired
	private UserRestRepository userRestRepository;

	@Override
	public void run(String... args) throws Exception {
		logger.info("Data Saving started...");
		userRestRepository.save(new User("Hari1", "Role1"));
		userRestRepository.save(new User("Hari2", "Role2"));
		userRestRepository.save(new User("Hari3", "Role1"));
		userRestRepository.save(new User("Hari4", "Role4"));
		logger.info("Data Saving completed..");

		logger.info("Read all Users..");
		StreamSupport.stream(userRestRepository.findAll().spliterator(), false).forEach(System.out::println);

		logger.info("Reading the by Role::");
		userRestRepository.findByRole("Role1").stream().forEach(System.out::println);
	}

}
