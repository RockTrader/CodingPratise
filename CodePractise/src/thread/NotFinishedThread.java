package thread;

public class NotFinishedThread {

	//method to prevent not finishing task;
	NotFinishedThread(){
		Runnable hello = new DisplayMessage("Not finishing ");
		Thread notfinish = new Thread(hello);
		notfinish.setName("Not finishing ");
		notfinish.setDaemon(true);
		System.out.println("Starting notfinish thread...");
		notfinish.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		// demo 2
		//demo2();
		demo3();



	}
	
	private static void demo3(){
		new NotFinishedThread();
	}
	
	private static void demo1(){
		// demo 1
		Runnable hello = new DisplayMessage("Not finishing ");
		Thread notfinish = new Thread(hello);
		notfinish.setName("Not finishing ");
		notfinish.setDaemon(true);
		System.out.println("Starting notfinish thread...");
		notfinish.start();
	}
	
	public static void demo2(){
		
		for (int i = 0; i < 3; i++) {
			Runnable r = new DisplayMessage(" thread" + i);
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.start();
		}
	}

}
