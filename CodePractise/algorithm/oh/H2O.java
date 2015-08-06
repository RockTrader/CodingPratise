package oh;
//ʵ����������: H() and O(), �����������ᱻ���̵߳��á�������㵱ǰ2���̵߳���H(),��1���̵߳���O()��
//������3���̷߳��أ�����һ��ˮ����(������HHO,HOH,OHH)������H()�ĵ�ǰ�̲߳��ܴ���2������O()�ĵ�ǰ�̲߳��ܴ���1��
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



