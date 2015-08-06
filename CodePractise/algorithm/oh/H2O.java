package oh;
//实现两个函数: H() and O(), 这两个函数会被多线程调用。如果满足当前2个线程调用H(),和1个线程调用O()，
//让以上3个线程返回，产生一个水分子(可能是HHO,HOH,OHH)。调用H()的当前线程不能大于2，调用O()的当前线程不能大于1。
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class H2O {
	private BlockingQueue<CountDownLatch> hq = new ArrayBlockingQueue<CountDownLatch>(1);
	private BlockingQueue<CountDownLatch> oq = new ArrayBlockingQueue<CountDownLatch>(1);

	public H2O H() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		hq.add(latch);
		latch.await();
		return this;
	}

	public H2O O() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		oq.add(latch);
		while (true) {
			CountDownLatch o = (CountDownLatch) oq.take(); // take an O
			CountDownLatch h1 = (CountDownLatch) hq.take(); // take an H
			h1.countDown(); // take another H
			System.out.println("release H ");
			h1.countDown(); // count down all the rest
			System.out.println("release H ");
			o.countDown();
			System.out.println("release O ");
			return this;
		}

	}
	
	public static void main(String[] args) {
		
		final H2O h2o = new H2O();
		Thread h = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					h2o.H();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		Thread o = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					h2o.O();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		h.start();
		o.start();
	}



}



