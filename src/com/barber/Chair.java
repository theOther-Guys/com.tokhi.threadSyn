package com.barber;

public class Chair extends Thread{
	
	
	Person sittingPerson ;
	boolean isFull ;
	public Chair() {
		// TODO Auto-generated constructor stub
	}
	public boolean getIsFull (){
		return isFull ;
	}
	
	public void setChairForPerson (Person person){
		sittingPerson = person ;
		isFull = true ;
	}
	public Person getSittingPerson(){
		return sittingPerson ;
	}
	public void setSittingPersonNull (){
		sittingPerson = null ;
	}

}
