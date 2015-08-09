package fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findFibonacciNumbersUpto(0));
		
		System.out.println(findFibonacciNumbersUpto(1));
		
		System.out.println(findFibonacciNumbersUpto(3));
		
		System.out.println(findFibonacciNumbersUpto(45354));
	}
	
	static List<Integer> findFibonacciNumbersUpto(int max){
		List<Integer> l = new ArrayList<Integer>();
		if(max <=0) return l;
		int first = 1, second =1;
		l.add(first);
		l.add(second);
		int next;
		while((next = first + second) <= max ){
			l.add(next);
			first = second;
			second = next; 	
		}
		
		return l;
	}
	
	

}
