package com.barber;

import java.io.InterruptedIOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person extends Thread implements Runnable {

	private String name;
		public static Lock lock = new ReentrantLock();
		static Condition condition = lock.newCondition();

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		this.name = name;
	}

	public void run() {
		try {
			
while (true){
				checkIfSitIsFree();
				ifItIsSitNumberOne();
				goToCuttingHairChairForCuttingYourHair();
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(" interupt occurs here ");
			e.printStackTrace();
		}catch (NullPointerException e){
			System.out.println(" no one in ");
			try {
				goToCuttingHairChairForCuttingYourHair();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			this.notifyAll();
		}catch (IllegalMonitorStateException e){
			System.out.println("couldn't solve buddy ");
			try {
				ifItIsSitNumberOne();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public synchronized void checkIfSitIsFree() throws InterruptedException , NullPointerException{
		lock.lock();
		for (int index = 0; index < RunBarberShop.chairs.size(); index++) {
			if (RunBarberShop.chairs.get(index).getSittingPerson().equals(null)) {
				RunBarberShop.chairs.get(index).setChairForPerson(new Person(" ad "));
				System.out.println(" new person has came in ");
				condition.await();
				break;
				
			} else {
				System.out.println(" it's not my turn ");
				ifItIsSitNumberOne();
				condition.signalAll();
				lock.unlock();
				break;
			}
		}
		lock.unlock();
		condition.signalAll();
	}

	public  boolean ifItIsSitNumberOne() throws InterruptedException{

		if (RunBarberShop.chairs.indexOf(this) > 0) {
			int index = RunBarberShop.chairs.indexOf(this);
				RunBarberShop.chairs.get(index - 1).setChairForPerson(this);
				System.out.println(" i am one step forward ");
//				Thread.sleep(3000);
			} else {
//				Thread.sleep(7000);
			System.out.println(" it's my turn ");
			goToCuttingHairChairForCuttingYourHair();
		}

		return true;
	}

	public synchronized void goToCuttingHairChairForCuttingYourHair() throws InterruptedException {
		
		RunBarberShop.chairs.get(0).setSittingPersonNull();
		RunBarberShop.hairChair.setSittingPerson(this);
	}

}
