package assignment2;

import java.util.Scanner;

public class Main {

	static final String ERROR_TYPE_STRING = "error";
	static final String ASSIGNMENT_TYPE_STRING = "assignment";
	static final String PRINT_STATEMENT_TYPE_STRING = "print_statement";
	Scanner in;
	
	Main(){
		
	}
	
	String getInput(Scanner in){
		//reads input
		return null;
	}
	
	String checkInput(String inputString){
		//checks input and returns the type of input as a String
		return null;
	}
	
	void processComment(String inputString) {
		
	}

	void processPrintStatement(String inputString) {
		
	}

	void processAssignment(String inputString) {
		
	}
	
	void processInput(String inputString, String inputType){
		if(inputType.equals(ASSIGNMENT_TYPE_STRING)){
			processAssignment(inputString);
		}else if(inputType.equals(PRINT_STATEMENT_TYPE_STRING)){
			processPrintStatement(inputString);
		}else{
			processComment(inputString);
		}
	}
	

	void start(){
		in = new Scanner(System.in);
		while(true){
			String inputString = getInput(in);
			String inputType = checkInput(inputString);
			if(!inputType.equals(ERROR_TYPE_STRING)){
				processInput(inputString, inputType);
			}
		}
	}
	
	public static void main(String[] args) {
		new Main().start(); 

	}

}
