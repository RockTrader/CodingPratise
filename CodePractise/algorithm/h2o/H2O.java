package h2o;


//correct framework
//http://examples.javacodegeeks.com/core-java/util/concurrent/cyclicbarrier/java-util-concurrent-cyclicbarrier-example/
//ʵ����������: H() and O(), �����������ᱻ���̵߳��á�������㵱ǰ2���̵߳���H(),��1���̵߳���O()��
//������3���̷߳��أ�����һ��ˮ����(������HHO,HOH,OHH)������H()�ĵ�ǰ�̲߳��ܴ���2������O()�ĵ�ǰ�̲߳��ܴ���1��

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
