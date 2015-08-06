package h2o;


//correct framework
//http://examples.javacodegeeks.com/core-java/util/concurrent/cyclicbarrier/java-util-concurrent-cyclicbarrier-example/
//实现两个函数: H() and O(), 这两个函数会被多线程调用。如果满足当前2个线程调用H(),和1个线程调用O()，
//让以上3个线程返回，产生一个水分子(可能是HHO,HOH,OHH)。调用H()的当前线程不能大于2，调用O()的当前线程不能大于1。

import java.util.Random;

public class H2O implements Runnable {

	
	static int countH = 0;
	static int countO = 0;
	
	static int countALL = 0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		for(int i=0;i<100;i++){
			new H2O().run();
		//	Thread.sleep(2000);
			countALL ++;
		}

		
		
	}
	
	public synchronized void H()  {
//		System.out.println("H");
		countH++;
		

	}
	
	public synchronized void O()  {
//		System.out.println("O");
		countO++;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub		
//		if(countALL>=1){
//			notifyAll();
//		}
		if(getTaskChoice()){
			H();
		}else{
			O();	
		}
		judge();

	}
	
	public synchronized void judge(){
		System.out.println("H thread =" + countH + "; O thread = " + countO);
		if(countH >= 2 && countO >= 1){
			System.out.println("H2O");
			countH -= 2;
			countO -= 1;
		}
		
	}
	
	public boolean getTaskChoice(){
		Random r = new Random();
		int num01 = r.nextInt(2);
	//	System.out.println(num01);
		if(num01 == 0){
			
			return true;
		}else{
			
			return false;
		}

	}
	
	

}
