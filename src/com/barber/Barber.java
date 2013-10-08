package com.barber;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barber extends Thread implements Runnable{
	
	boolean isCutting ;
	static Semaphore semaphore = new Semaphore(1);
	public Barber(){
		
	}
	public void cutHair(Person person){
		
		try {
			semaphore.acquire();
			
			System.out.println(" Cutting person's hair ");
			RunBarberShop.hairChair.setTheChairNull();

//			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaphore.release();
			finished(person);
		}
		
	}
	private void finished (Person person){
		System.out.println(" finished cutting his hair now you can go ");
		person = null ;
		isCutting = false ;
	}
	public boolean getIsCutting(){
		return isCutting ;
	}
	public boolean getPersonForCuttingHair()throws NullPointerException{
		Random generator = new Random();
		try {
			semaphore.acquire();
//		if (!RunBarberShop.hairChair.getPerson().equals(null)){
			cuttThePersonsHair();
//		}else {
//			System.err.println(" it's time for sleeping ");
//				Thread.sleep(generator.nextInt(2000));
//		}
		} catch(NullPointerException e){
			
		}catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}finally {
			semaphore.release();
		}
		return false ;
	}
	
	private void cuttThePersonsHair() {
		// TODO Auto-generated method stub
		cutHair(RunBarberShop.hairChair.getPerson());
		
	}
	public void run (){
		while (true ){
		
			getPersonForCuttingHair();
//		sleepBarber();
		}
	}
	public void sleepBarber(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
