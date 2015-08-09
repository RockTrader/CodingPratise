package two;

import java.util.Arrays;
import java.util.Stack;

public class BSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] test = {1,2,3,4,5,6,7};
		int target = 5;
		int start =0;
		System.out.println(new BSearch().bsearch(test, target, start));
		
	}
	
	public int bsearch(int[] array, int target,int start){
		int L = start, R = array.length;
		while(L<R) {
			 int M = (L+R)/2;
			if(array[M]<target){
				L = M+1;
			}else{
				R = M;
			}
		}
		
		return (L==R && array[L]== target)?L:-1;
		
	}

}
