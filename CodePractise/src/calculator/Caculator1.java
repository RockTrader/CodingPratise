package calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Caculator1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = new Caculator1().calculate("1+7-7+3+3+6-3+1-8-2-6-1+8-0+0-2+0+10-6-9-9+0+6+4+2+7+1-4-6-6-0+6+3-7+0-4+10-2-5+6-1-3+7+7+2+0+2-8+7+2-3-8-9-6+1");
		System.out.println(num);
	}
	
    public int calculate(String s) {
        List<Integer> list =  parse(s);
        int sum =0;
        for(int i:list){
            sum +=i;
        }
        
        return sum;
   }
   
   public List<Integer> parse(String s){
        List<Character> s1 = new LinkedList<Character>();
        List<Integer> num = new LinkedList<Integer>();
        for(Character c:s.toCharArray()){
            if(Character.isWhitespace(c)) continue;
            else s1.add(c);
        }
        for(int i=0;i<s1.size();){

            if(s1.get(i) == '(' ) {i++;continue;}
            if(s1.get(i) == ')' ) {i++;continue;}
            if(s1.get(i) == '+' ) {i++;continue;} 
            if(s1.get(i) == '-' ){
            	i++;
                int temp = s1.get(i) - '0';
                   while (i + 1 < s1.size() && Character.isDigit(s1.get(i + 1))){
                   temp = temp * 10 + s1.get(++i) - '0';
                   }
                num.add(-temp);
                
            }else if(Character.isDigit(s1.get(i))){
            	int j=i;
                int temp = s1.get(i) - '0';
                   while (i + 1 < s1.size() && Character.isDigit(s1.get(i + 1))){
                   temp = temp * 10 + s1.get(++i) - '0';
                   }
                num.add(temp);
                if(j==i){
                	i++;
                }
            }

            
        }
        return num;
       
   }

}
