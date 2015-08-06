package h2o;


//correct framework
//http://examples.javacodegeeks.com/core-java/util/concurrent/cyclicbarrier/java-util-concurrent-cyclicbarrier-example/
//ʵ����������: H() and O(), �����������ᱻ���̵߳��á�������㵱ǰ2���̵߳���H(),��1���̵߳���O()��
//������3���̷߳��أ�����һ��ˮ����(������HHO,HOH,OHH)������H()�ĵ�ǰ�̲߳��ܴ���2������O()�ĵ�ǰ�̲߳��ܴ���1��

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public class H2OCyclicBarrier  {

	final static CyclicBarrier barrier = new CyclicBarrier(3);
	static BlockingQueue<Integer> hqueue = new LinkedBlockingQueue<Integer>();
	static BlockingQueue<Integer> oqueue = new LinkedBlockingQueue<Integer>();
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final H2OCyclicBarrier test = new H2OCyclicBarrier();
		Thread h1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.H();
			}
			
		});
		Thread h2 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.H();
			}
			
		});
		Thread o = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.O();
			}
			
		});
		h1.start();
		h2.start();
		o.start();
	}
	
	public void H(){

		
		try {
		//	synchronized(this){
		//	while(hqueue.size()<2){
				hqueue.add(1);
				System.out.println("added H");
				this.barrier.await();
		//	}
		//	}
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hqueue.poll();
		hqueue.poll();
	}
	public void O(){	
		try {
		//	while(oqueue.size()<1){
				oqueue.add(1);
				System.out.println("added O ");
				this.barrier.await();
		//	}
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Out put an H2O");
		oqueue.poll();
		
	}

}