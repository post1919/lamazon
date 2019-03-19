package com.castingn.example;

import java.util.Arrays;
import java.util.List;

public class StreamExam2 {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(
				  new Student("박태석", 90)
				, new Student("신동아", 85)
				);

		double avg = list.stream()
				.mapToInt(Student::getScore)
				.average()
				.getAsDouble();
		
		System.out.println("평균점수 : " + avg);
		
	}

}
