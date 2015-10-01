package assignment2;

import java.util.Scanner;
import java.io.PrintStream;

public class Main {

	static final String ERROR_TYPE_STRING = "error";
	static final String ASSIGNMENT_TYPE_STRING = "assignment";
	static final String PRINT_STATEMENT_TYPE_STRING = "print_statement";
	static final String COMMENT_TYPE_STRING = "comment";
	Scanner in;
	// Table setTable;
	PrintStream out;

	Main() {
		// setTable = new Table();
		out = new PrintStream(System.out);
	}

	String getInput(Scanner in) {
		// reads input
		return null;
	}

	String checkInput(String inputString) {
		// checks input and returns the type of input as a String
		return null;
	}

	void processPrintStatement(String printStatementString) {

	}

	void processAssignment(String assignmentString) {

	}

	void processInput(String inputString, String inputType) {
		if (inputType.equals(ASSIGNMENT_TYPE_STRING)) {
			processAssignment(inputString);
		} else {
			processPrintStatement(inputString);
		}
	}

	void start() {
		in = new Scanner(System.in);
		while (true) {
			String inputString = getInput(in);
			String inputType = checkInput(inputString);
			if (inputType.equals(ASSIGNMENT_TYPE_STRING) | inputType.equals(PRINT_STATEMENT_TYPE_STRING)) {
				processInput(inputString, inputType);
			}
		}
	}

	public static void main(String[] args) {
		new Main().start();

	}

}
