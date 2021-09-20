package com.hari.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hari.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Create custom methods syntax: findBy{Column_name} Then it will automatically
	 * search by that Column_name using passing parameters To Crate Methods:
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	 */

	List<User> findByRole(String role);

}
