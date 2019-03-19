package com.castingn.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExam1 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("박태석", "서혜정", "안지은", "신동아");
		
		Stream<String> stream = list.stream();
				
		stream.forEach(StreamExam1::print);		

		System.out.println();
		
		//병렬처리 - 조각으로 쪼개서 처리후 병합
		Stream<String> parallelStream = list.parallelStream();
		parallelStream.forEach(StreamExam1::print);
	}
	
	public static void print(String string) {
		System.out.println(string + " : " + Thread.currentThread().getName());
	}
}
