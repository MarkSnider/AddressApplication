package com.service;
import com.example.*;

public class ExampleProgramming {

	protected int z;
	
	protected int getZ() {
		return(z);
	}
	
	public void doSomething() {
		SomeExampleClass theEx = new SomeExampleClass();
		
		// We can access the getY variable its public
		theEx.getY();
		
		//  We cannot access the getX variable its protected and not in a subclass
		// theEx.getX();
		

	}
}
