package h2o;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Water {

	public static Integer numberOfHydrogenElements;
	public static Semaphore semaphoreHydrogen;
	public static Semaphore semaphoreOxygen;

	public Water() {
		// TODO Auto-generated constructor stub
		numberOfHydrogenElements = new Integer(0);
		semaphoreHydrogen = new Semaphore(0);
		semaphoreOxygen = new Semaphore(0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Water water = new Water();
		Random randomElement = new Random();
		for (int i = 0; i < 30; i++) {
			int n = randomElement.nextInt(30);
			System.out.print(" random num=" + n);
			if (n % 3 == 0) {
				// Generate random either an Hydrogen Thread or an Oxygen Thread
				// If number of Hydrogen Threads is not double the number of
				// Oxygen Thread then the program should block
				Oxygen ThreadO = new Oxygen();
				ThreadO.start();
			} else {
				Hydrogen ThreadH = new Hydrogen();
				ThreadH.start();
			}
		}
		System.out.println("Number of Hydrogen Threads generated: " + Hydrogen.noOfHydrogen
				+ " Number of Oxigen Threads generated: " + Oxygen.noOfOxygen);
	}
}

class Hydrogen extends Thread {

	public static int noOfHydrogen;

	public Hydrogen() {
		// TODO Auto-generated constructor stub
		noOfHydrogen++;
	}

	public void run() {
		synchronized (Water.numberOfHydrogenElements) {
			Water.numberOfHydrogenElements++;
			if ((Water.numberOfHydrogenElements > 0) && (Water.numberOfHydrogenElements % 2 == 0)) {
				Water.semaphoreOxygen.release();
			}
		}
		try {
			Water.semaphoreHydrogen.acquire();
			System.out.println("Put hydrogen");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Oxygen extends Thread {

	public static int noOfOxygen;

	public Oxygen() {
		// TODO Auto-generated constructor stub
		noOfOxygen++;
	}

	public void run() {
		synchronized (Water.numberOfHydrogenElements) {
			try {
				Water.semaphoreOxygen.acquire();
				Water.semaphoreHydrogen.release(2);
				System.out.println("Put Oxygen");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
