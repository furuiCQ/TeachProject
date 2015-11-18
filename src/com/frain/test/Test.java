package com.frain.test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Vector;


public class Test {
	 class A extends Test{
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test=new Test();
		//A name=new A();
		String str1="aa";
		String str2="bb";
		String str3="aa";

		System.out.println(str1.hashCode());
		System.out.println(str3.hashCode());
		System.out.println(str1==str3);
		//List list=new List();
		Vector<String> vector=new Vector<String>();
		LinkedHashSet<String> hashSet=new LinkedHashSet<String>();
		hashSet.add("123");
		hashSet.add("123");
		
		Iterator iterator=hashSet.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			
		}
		
		
		
		

		
	}

}
