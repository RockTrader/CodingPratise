package two;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoDifference {
	public static void main(String[] args) {
		
		int[] test = {1,44,35,5,6,7,8,9};
		int target = 7;
		int[] indices = new TwoDifference().getIndexes(test, target);
		System.out.println(Arrays.toString(indices));

		int[] test2 = {1,2,5,5,6,7,8,9};
		System.out.println(new TwoDifference().getIndexesFromSorted(test2, target));
		
	}
	
	public int[] getIndexes(int[] array,int target){
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		for (int i = 0; i < array.length; i++) {
			int x = array[i];
			if(m.containsKey(x+target)){
				return new int[]{i,m.get(x+target)};
			}else if( m.containsKey(x - target)){
				return new int[]{i,m.get(x-target)};
			}
			
			m.put(x, i);
		}
		
		return null;
	}
	
	public List<List<Integer>> getIndexesFromSorted(int[] array,int target){
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		for (int i = 0; i < array.length; i++) {
			int c1 = bsearch(array, array[i] - target);
			int c2 = bsearch(array,target + array[i]);
			if(c1 >=0) {
				List<Integer> m = new ArrayList<Integer>();
				m.add(i);
				m.add(c1);
				l.add(m);
			}
			if(c2 >=0) {
				List<Integer> m = new ArrayList<Integer>();
				m.add(i);
				m.add(c2);
				l.add(m);
			}
		}
		
		return l;

	}

	private int bsearch(int[] array, int target) {
		// TODO Auto-generated method stub
		int L = 0, R = array.length;
		while(L<R){
			int M = (L + R)/2;
			if(array[M] < target){
				L = M+1;
			}else if(array[M] > target){
				R = M;
			}else{
				return M;
			}

		}
		
		return -1;
	}
	
	
}
