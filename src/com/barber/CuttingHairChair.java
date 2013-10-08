package com.barber;

import java.util.concurrent.Semaphore;

public class CuttingHairChair{
	
	Person person ;
	private Barber barber;
	
	public static Semaphore semaphore = new Semaphore(1);
	
	public CuttingHairChair() {
		// TODO Auto-generated constructor stub
	}
	public void setSittingPerson(Person person){
		try {
			semaphore.acquire();
		
			this.person = person ;
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
		
		
	}
	public void setSittingPerson (Barber barber){
		this.barber = barber ;
	}
	public Person getPerson(){
		return person ;
	}
	public void setTheChairNull(){
		this.person = null ;
	}

}
