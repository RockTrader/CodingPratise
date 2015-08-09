package h2o;


//correct framework
//http://examples.javacodegeeks.com/core-java/util/concurrent/cyclicbarrier/java-util-concurrent-cyclicbarrier-example/
//实现两个函数: H() and O(), 这两个函数会被多线程调用。如果满足当前2个线程调用H(),和1个线程调用O()，
//让以上3个线程返回，产生一个水分子(可能是HHO,HOH,OHH)。调用H()的当前线程不能大于2，调用O()的当前线程不能大于1。

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class H2OCyclicBarrier  {

	final static CyclicBarrier barrier = new CyclicBarrier(3,new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Out Put 1 H20");
		}
		
	});
//	static BlockingQueue<Integer> hqueue = new LinkedBlockingQueue<Integer>();
//	static BlockingQueue<Integer> oqueue = new LinkedBlockingQueue<Integer>();
	private Semaphore semaphoreH = new Semaphore(0);
	private Semaphore semaphoreO = new Semaphore(0);
	
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

//		h2.start();
		o.start();
		h2.start();
		h1.start();
	}
	
	public void H(){

		
		try {
		//	synchronized(this){
		//	while(hqueue.size()<2){
		//		hqueue.add(1);
			semaphoreH.release();
				System.out.println("added H");
			semaphoreO.acquire();
				this.barrier.await();
		//	}
		//	}
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	hqueue.poll();
	//	hqueue.poll();
	}
	public void O(){	
		try {
		//	while(oqueue.size()<1){
		//		oqueue.add(1);
			semaphoreO.release(2);
			semaphoreH.acquire(2);
				System.out.println("added O ");
				this.barrier.await();
		//	}
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Out put an H2O");
//		oqueue.poll();
		
	}

}