package com.hari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.hari.sorting.impl.BinarySearchImpl;

@SpringBootApplication
public class SpringSortingAlgorithmExampleApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication
				.run(SpringSortingAlgorithmExampleApplication.class, args);
		final BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);
		final int result = binarySearchImpl.binarySearch(new int[] { 2, 4, 10, 8, 9, 7, 3, 5, 11, 6 }, 11);
		System.out.println(result);
	}

}
