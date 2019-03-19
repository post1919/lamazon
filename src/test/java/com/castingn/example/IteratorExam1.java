package com.castingn.example;

import java.util.Iterator;
import java.util.LinkedList;

public class IteratorExam1 {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("박태석");
		list.add("서혜정");
		list.add("신동아");
		list.add("안지은");
		
		System.out.println("삭제 전 List 사이즈 => " + list.size());
		
		Iterator<String> it = list.iterator();
		
		while( it.hasNext() ) {
			String curStr = it.next();
			System.out.println(curStr);
			if( curStr.compareTo("신동아") == 0 ) {
				it.remove();
			}
		}
		
		System.out.println();
		
		it = list.iterator();
		
		while( it.hasNext() ) {
			System.out.println(it.next());
		}
		
		System.out.println("삭제 후 List 사이즈 => " + list.size());
	}
}
