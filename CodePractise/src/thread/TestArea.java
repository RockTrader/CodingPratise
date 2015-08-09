package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class TestArea {
	public static void main(String[] args) {
		Stack<String> s = new Stack<String>();
		String x = "*";
		
		System.out.println(Arrays.asList(1,2,3,4,5));
		System.out.println(Arrays.asList("*",'x',3,4,5));
		//Integer.MAX_VALUE;
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		
		Character c = '1';
		Character.isWhitespace(c);
//		Character.getNumericValue(ch)
		List<Character> q = new LinkedList<Character>();
		

		for(Map.Entry<Integer, Integer> e:m.entrySet()){
			
		}
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		
		
		System.out.println(Arrays.toString(infixToRPN(new String[]{"(","1","+","2",")","*","3"})));
				
		
	}
	
    public int calculate(String s) {
        Deque<Character> s1 = new LinkedList<Character>();
        Deque<Character> s2 = new LinkedList<Character>();
        Stack<Integer> num = new Stack<Integer>();
        for(Character c:s.toCharArray()){
            if(Character.isWhitespace(c)) continue;
            s1.add(c);
        }
        
        while(s2.size()>0){
            Character c = s1.pop();
            if(c == '(') s2.push(c);
            if(Character.isDigit(c))num.push(Character.getNumericValue(c));
            if(c =='+' ) {
                char p = s1.peek();
                if(Character.isDigit(p))num.push(Character.getNumericValue(s1.pop()) + num.pop());
            }
             if(c =='-' ) {
                char p = s1.peek();
                if(Character.isDigit(p))num.push(num.pop() - Character.getNumericValue(s1.pop()));
            }
            if(c == ')') s2.pop();
        }
        
        return num.pop();
        
   }
	
    static int getNumberOfPrimes(int n) {
        if(n<2) return 0;
        
        int counter = 1;
        for(int i=2;i<=n;i++){
            if(isPrime(i))counter++;
        }
        return counter;
    } 

    static boolean isPrime(int n){
        if(n%2==0)return false;
        for(int i=2;2*i<n;i++){
            if(n%i==0)return false;
        }
        return true;
    }
    
    
    public static String[] infixToRPN(String[] inputTokens) {
		ArrayList<String> out = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		// For all the input tokens [S1] read the next token [S2]
		for (String token : inputTokens) {
			if (isOperator(token)) {
				// If token is an operator (x) [S3]
				while (!stack.empty() && isOperator(stack.peek())) {
					// [S4]
					if ((
//							isAssociative(token, LEFT_ASSOC) && 
							cmpPrecedence(
							token, stack.peek()) <= 0)
//							|| (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
//									token, stack.peek()) < 0)
							) {
						out.add(stack.pop()); 	// [S5] [S6]
						continue;
					}
					break;
				}
				// Push the new operator on the stack [S7]
				stack.push(token);
			} else if (token.equals("(")) {
				stack.push(token); 	// [S8]
			} else if (token.equals(")")) {
				// [S9]
				while (!stack.empty() && !stack.peek().equals("(")) {
					out.add(stack.pop()); // [S10]
				}
				stack.pop(); // [S11]
			} else {
				out.add(token); // [S12]
			}
		}
		while (!stack.empty()) {
			out.add(stack.pop()); // [S13]
		}
		String[] output = new String[out.size()];
		return out.toArray(output);
	}
    
    static int LEFT_ASSOC = 1;
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
	static {
		// Map<"token", []{precendence, associativity}>
		OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("%", new int[] { 5, LEFT_ASSOC });
//		OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC });
	}
	private static boolean isAssociative(String token, int type) {
		if (!isOperator(token)) {
			throw new IllegalArgumentException("Invalid token: " + token);
		}
		if (OPERATORS.get(token)[1] == type) {
			return true;
		}
		return false;
	}
	/**
	 * Test if a certain is an operator .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private static boolean isOperator(String token) {
		return OPERATORS.containsKey(token);
	}
	
	private static final int cmpPrecedence(String token1, String token2) {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1
					+ " " + token2);
		}
		return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
	}
}

 interface Operator {
	 int eval(int x,int y);
 }
