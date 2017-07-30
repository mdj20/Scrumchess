package com.scrumchess.operations.google.test;

import com.scrumchess.operations.google.MainOperationsGAEDS;

public class GoogleAppEngineOperationsTest {
	MainOperationsGAEDS mainOps = new MainOperationsGAEDS();
	
	
	private  int smokeTest(){
		MainOperationsGAEDS mainOps = new MainOperationsGAEDS();
		
		return 0;
	}
	
	GoogleAppEngineOperationsTest(){
		
	}
	
	public static void main(String args[]){
		GoogleAppEngineOperationsTest test = new GoogleAppEngineOperationsTest();
		System.exit(test.smokeTest());
	}
}
