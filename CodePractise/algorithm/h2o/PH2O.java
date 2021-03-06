package h2o;
//实现两个函数: H() and O(), 这两个函数会被多线程调用。如果满足当前2个线程调用H(),和1个线程调用O()，
//让以上3个线程返回，产生一个水分子(可能是HHO,HOH,OHH)。调用H()的当前线程不能大于2，调用O()的当前线程不能大于1。
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class PH2O {

	private static final Object PLACE_HOLDER = new Object();
	AtomicInteger extraH = new AtomicInteger();
	BlockingQueue<Object> requiredO = new LinkedBlockingDeque<Object>();
	BlockingQueue<Object> releasedH = new LinkedBlockingDeque<Object>();

	public void h() throws InterruptedException {
		while (true) {
			if (extraH.compareAndSet(1, 0)) {
				requiredO.add(new Object());
				break;
			}
			if (extraH.compareAndSet(0, 1)) {
				break;
			}
		}
		releasedH.take();
//		System.out.println("take H");
	}

	public void o() throws InterruptedException {
		requiredO.take();
		System.out.println("take O");
		releasedH.offer(PLACE_HOLDER);
		System.out.println("Offer H");		
		releasedH.offer(PLACE_HOLDER);
		System.out.println("Offer H");		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExecutorService executor = Executors.newFixedThreadPool(5);
        final PH2O test =new  PH2O();
        for (int i = 0; i < 10; i++) {
            Runnable workerH = new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						test.h();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            	
            };
            executor.execute(workerH);
            Runnable workerO = new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						test.o();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            	
            };
            executor.execute(workerO);
          }
        executor.shutdown();

	}

}
