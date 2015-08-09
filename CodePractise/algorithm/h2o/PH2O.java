package h2o;
//ʵ����������: H() and O(), �����������ᱻ���̵߳��á�������㵱ǰ2���̵߳���H(),��1���̵߳���O()��
//������3���̷߳��أ�����һ��ˮ����(������HHO,HOH,OHH)������H()�ĵ�ǰ�̲߳��ܴ���2������O()�ĵ�ǰ�̲߳��ܴ���1��
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
