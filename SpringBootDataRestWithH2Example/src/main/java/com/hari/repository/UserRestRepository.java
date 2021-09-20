package com.hari.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hari.model.User;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

	/**
	 * Create custom methods syntax: findBy{Column_name} Then it will automatically
	 * search by that Column_name using passing parameters To Crate Methods:
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	 */

	/**
	 * To Search from browser ->
	 * http://localhost:8080/users/search/findByRole?role=Role1
	 * 
	 * @param role
	 * @return
	 */
	List<User> findByRole(@Param("role") String role);
}
