package h2o;
//ʵ����������: H() and O(), �����������ᱻ���̵߳��á�������㵱ǰ2���̵߳���H(),��1���̵߳���O()��
//������3���̷߳��أ�����һ��ˮ����(������HHO,HOH,OHH)������H()�ĵ�ǰ�̲߳��ܴ���2������O()�ĵ�ǰ�̲߳��ܴ���1��
import java.util.concurrent.BlockingQueue;
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

	}

	public void o() throws InterruptedException {
		requiredO.take();
		releasedH.offer(PLACE_HOLDER);
		releasedH.offer(PLACE_HOLDER);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
