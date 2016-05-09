package com.example;
import com.service.ExampleProgramming;

public class AnotherExampleClass extends ExampleProgramming{
	

	
	
	public void doSomething() {
		
		// The protected is available only from the subclass AnotherExampleClass not ExampleProgramming
		int z = getZ();
	}
	

	public void main(String[] args) {
		
		// The protected is available only from the subclass AnotherExampleClass
		AnotherExampleClass ac = new AnotherExampleClass();
		ac.getZ();
		
		ExampleProgramming ep = new ExampleProgramming();
		
		//ep.getZ();
	}

}
