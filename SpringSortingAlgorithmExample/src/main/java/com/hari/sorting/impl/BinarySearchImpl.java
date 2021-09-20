package com.hari.sorting.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hari.sorting.SortAlgorith;

@Component
public class BinarySearchImpl {

	@Autowired
	private SortAlgorith sortAlgorith;

	public int binarySearch(int[] numbers, int numberForSearch) {
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
		final int[] sortedNumbers = sortAlgorith.sort(numbers);
		for (int i = 0; i < sortedNumbers.length; i++) {
			System.out.print(sortedNumbers[i] + " ");
		}
		int position = 0;
		int first = 0;
		int last = sortedNumbers.length - 1;
		int middle = (first + last) / 2;
		while (first <= last) {
			if (sortedNumbers[middle] < numberForSearch) {
				first = middle + 1;
			} else if (sortedNumbers[middle] == numberForSearch) {
				position = middle + 1;
				System.out.println(numberForSearch + " found at location " + position + ".");
				break;
			} else {
				last = middle - 1;
			}
			middle = (first + last) / 2;
		}
		if (first > last) {
			System.out.println(numberForSearch + " isn't present in the list.\n");
		}
		return position;
	}
}
