package com.castingn.example;

public class LamdaExam1 {

	public static void main(String[] args) {
		//기본 문법
		new Thread(new Runnable() {
			public void run() {
				for( int i=0; i<10; i++ ) {
					System.out.println(i + " => hello");
				}
			}
		}).start();
		
		//람다식 문법
		new Thread(()->{
			for( int i=0; i<10; i++ ) {
				System.out.println(i + " => Lamda hello");
			}
		}).start();
	}
}
