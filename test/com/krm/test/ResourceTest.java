package com.krm.test;

import java.io.IOException;
import java.net.URL;

public class ResourceTest {
	public static void main(String[] args) {
		URL url = ResourceTest.class.getResource("");
		try {
			System.out.println(url.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String file = url.getFile();
		System.out.println(file);
		
		
		URL url1 = ResourceTest.class.getResource("/");
		try {
			System.out.println(url1.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String file1 = url1.getFile();
		System.out.println(file1);
		URL url2 = ResourceTest.class.getClassLoader().getResource("organ.xml");
		try {
			System.out.println(url2.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String file2 = url2.getFile();
		System.out.println(file2);
		
		
		
//		URL url3 = ResourceTest.class.getClassLoader().getResource("/");
//		try {
//			System.out.println(url3.getContent());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String file3 = url3.getFile();
//		System.out.println(file3);
	}
}
