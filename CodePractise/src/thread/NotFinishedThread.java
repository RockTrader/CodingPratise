package thread;

public class NotFinishedThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable hello = new DisplayMessage("Not finishing ");
		Thread notfinish = new Thread(hello);
		notfinish.setName("Not finishing ");
		notfinish.setDaemon(true);
		System.out.println("Starting notfinish thread...");
		notfinish.start();
		
		
		for(int i=0;i<3;i++){
			Runnable r = new DisplayMessage(" thread"+ i);
			   Thread t=new Thread(r);
			   t.setDaemon(true);
			   t.start();
			}

	}

}
