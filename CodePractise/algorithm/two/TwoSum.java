package two;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static void main(String[] args) {
		TwoSum t = new TwoSum();
		
		System.out.println(Arrays.toString(t.twoSumSortedReturnIndex(new int[]{1,2,3,4,5,6},12)));
		System.out.println(Arrays.toString(t.twoSumSortedReturnNumber(new int[]{1,2,3,4,5,6},12)));
		System.out.println(Arrays.toString(t.twoSumReturnNumber(new int[]{1,2,3,4,5,6},11)));
		System.out.println(Arrays.toString(t.twoSumReturnIndex(new int[]{1,2,3,4,5,6},11)));
		
	}
	
	public int[] twoSumReturnNumber(int[] array, int target){
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		for(int x:array){
			if(m.containsKey(target - x))return new int[]{x,target-x};
			m.put(x, 1);
		}
		
		return null;
	}
	
	public int[] twoSumReturnIndex(int[] array, int target){
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		for (int i=0;i<array.length;i++) {
			int x = array[i];
			if (m.containsKey(target-x)) {
				return i<m.get(target-x)? new int[]{i,m.get(target-x)}: new int[]{m.get(target-x),i};
			}
			m.put(x, i);
		}
		
		return null;
	}
	
	public int[] twoSumSortedReturnIndex(int[] array, int target){
		int L = 0, R = array.length -1;
		if(R < 0){throw new IllegalArgumentException("array is empty");}
		if(array[L]*2>target || array[R]*2 < target) return null;
		while(L < R){
			if(array[L]+array[R]<target){
				L++;
			}else if(array[L]+array[R]<target){
				R--;
			}else{
				return new int[]{L,R};
			}
			
		}
		
		return null;
	}
	
	public int[] twoSumSortedReturnNumber(int[] array, int target){
		
		int L = 0, R = array.length -1;
		if(R < 0){throw new IllegalArgumentException("array is empty");}
		if(array[L]*2>target || array[R]*2 < target) return null;
		while(L < R){
			if(array[L]+array[R]<target){
				L++;
			}else if(array[L]+array[R]<target){
				R--;
			}else{
				return new int[]{array[L],array[R]};
			}
			
		}
		
		return null;
	}
}
