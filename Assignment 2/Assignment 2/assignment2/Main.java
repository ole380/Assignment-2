package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.PrintStream;

public class Main {

	static final char	COMMENT_TYPE_MARKER = '/',
						PRINT_STATEMENT_TYPE_MARKER = '?';

	static final String LETTER_PATTERN = "[a-zA-Z]",
						ERROR_TYPE_STRING = "error",
						ASSIGNMENT_TYPE_STRING = "assignment",
						PRINT_STATEMENT_TYPE_STRING = "print_statement",
						COMMENT_TYPE_STRING = "comment";

	Scanner in;
	// Table setTable;
	PrintStream out;

	Main() {
		// setTable = new Table();
		out = new PrintStream(System.out);
	}

	String getInput(Scanner in) {
		if(!in.hasNextLine()){
			System.exit(0);
		}
		return in.nextLine();
	}

	void processPrintStatement(String printStatementString) {

	}

	void processAssignment(String assignmentString) {

	}

	boolean nextCharIs(Scanner in, char c){
		return in.hasNext(Pattern.quote(c+""));
	}
	
	boolean nextCharIsLetter(Scanner in){
		return in.hasNext(LETTER_PATTERN);
	}
	
	void readWhiteSpaces(Scanner in){
		while(in.hasNext(" ")){
			in.next();
		}
	}
	
	String getInputType(Scanner inputScanner)throws APException{
		readWhiteSpaces(inputScanner);
		if(nextCharIsLetter(inputScanner)){
			return ASSIGNMENT_TYPE_STRING;
		}else if(nextCharIs(inputScanner, PRINT_STATEMENT_TYPE_MARKER)){
			return PRINT_STATEMENT_TYPE_STRING;
		}else if(nextCharIs(inputScanner, COMMENT_TYPE_MARKER)){
			return COMMENT_TYPE_STRING;
		}else{
			throw new APException("Input error, expecting a letter, question mark or back-slash.");
		}
	}

	void processInput(Scanner inputScanner)throws APException {
		String inputType = getInputType(inputScanner);
			out.printf("%s", inputType);
	}

	void runProgram(Scanner in)throws APException{
		String inputString = getInput(in);
		Scanner inputScanner = new Scanner(inputString);
		inputScanner.useDelimiter("");
		processInput(inputScanner);
	}

	void start() {
		in = new Scanner(System.in);
		while (true) {
			try {
				runProgram(in);
			} catch (APException e) {
				out.printf("%s\n", e.getMessage());
			}
		}

	}

	public static void main(String[] args) {
		new Main().start();

	}

}
