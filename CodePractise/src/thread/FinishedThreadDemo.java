package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FinishedThreadDemo implements Callable<Integer> {

	Integer threadIndex;
	
	public static void main(String[] args) throws InterruptedException {
		

		finishedDemo2();


	}
	
	static void finishedDemo1() throws InterruptedException{
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<3;i++){
			Runnable r = new DisplayMessage(" thread"+ i);
			Thread t=new Thread(r);
			t.setDaemon(true);
			t.start();
		    threads.add(t);
		}

		for(Thread t: threads) t.join();
		
	}
	
	static void finishedDemo2(){
		
		ExecutorService es = Executors.newCachedThreadPool();
		try {
			es.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<3;i++)
		   es.submit(new DisplayMessage(" thread"+ i));
		
		es.shutdown();

	}

	FinishedThreadDemo(Integer i){
		threadIndex = i;
	}
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("thread"+threadIndex);
		return threadIndex;
	}

}
