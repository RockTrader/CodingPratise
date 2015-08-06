package oh;

class Queue {
    private int num;
    private boolean valueSet = false;
    
    synchronized void put(int n) {
         while(valueSet) {
              try {
                   wait();
              } catch(InterruptedException err) {
                   System.out.println("put() interrupted.");
              }
         }
         
         num = n;
         valueSet = true;
         notify();
         System.out.println("Put: "+n);
    }
    
    synchronized int get() {
         while(!valueSet) {
              try {
                   wait();
              } catch(InterruptedException err) {
                   System.out.println("get() interrupted.");
              }
         }
         
         valueSet = false;
         notify();
         System.out.println("Got: "+num);
         return num;
    }
}

//========================================================

class Producer implements Runnable {
    
    private Queue queue;
    private Thread thread;
    
    Producer(Queue q) {
         queue = q;
         thread = new Thread(this,"Producer");
         thread.start();
    }
    
    public void run() {
         int i=0;
         while (i<10)
              queue.put(++i);
    }
    
}

//========================================================

class Consumer implements Runnable {
    
    private Queue queue;
    private Thread thread;
    
    Consumer(Queue q) {
         queue = q;
         thread = new Thread(this,"Consumer");
         thread.start();
    }
    
    public void run() {
         for (int i=0;i<10;i++)
              queue.get();
    }
    
}

//========================================================

public class Training {
   
   public static void main(String[] args) {
        Queue q = new Queue();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        System.out.println("Main class is done...");
   }
}