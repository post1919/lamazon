package com.castingn.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LamdaExam2 {

	public static void main(String[] args) {
		final List<String> names = new ArrayList<String>(Arrays.asList("박태석", "서희정", "안지은", "신동아"));
		
		names.add("aaa");
		
		System.out.println("JAVA 7");
		for( String name : names ) {
			System.out.println(name.toUpperCase());
		}
		
		System.out.println();
		
		System.out.println("JAVA 8 Lambda");
		//JAVA 8 Lambda
		names.stream()
			 .map( name -> name.toUpperCase() )
			 .forEach(name -> System.out.println(name));
	}

}
