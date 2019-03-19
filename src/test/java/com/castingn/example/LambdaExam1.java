package com.castingn.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LambdaExam1 {

	public static void main(String[] args) {
		
		//명령형
		final List<String> names = Arrays.asList("Taeseok", "Heejung", "Jieun", "DongAh");
		
		int cnt = 0;
		for( String name : names ) {
			if( name.startsWith("D") ) {
				cnt++;
			}
		}
		
		System.out.println("D로 시작하는 사람은 몇명? " + cnt);

		System.out.println();
		
		//람다식(함수형)
		System.out.println("Q로 시작하는 사람은 몇명? " + names.stream().filter(name -> name.startsWith("Q")). count());
		
		List<String> list = Arrays.asList("박태석", "서혜정", "안지은", "신동아");
		Iterator<String> it = list.iterator();
		
		while( it.hasNext() ) {
			if( it.next().compareTo("신동아") == 0 ) {
				it.remove();
			}
		}
		
		//System.out.println(list.toString());
	} 

}
