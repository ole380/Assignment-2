package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.beans.Expression;
import java.io.PrintStream;

public class Main {

	static final char	COMMENT_TYPE_MARKER = '/',
						PRINT_STATEMENT_TYPE_MARKER = '?',
						IDENTIFIER_EXPRESSION_SEPERATOR = '=',
						UNION_OPERATOR = '+',
						COMPLEMENT_OPERATOR = '-',
						SYMMETRIC_DIFFERENCE_OPERATOR = '|';

	static final String LETTER_PATTERN = "[a-zA-Z]",
						DIGIT_PATTERN = "[0-9]",
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

	void readFactor(){
		
	}
	
	void readTerm(){
		
	}
	
	void readExpression(Scanner expressionScanner)throws APException{
		readWhiteSpaces(expressionScanner);
		readTerm();
		while (expressionScanner.hasNext()){
			if(nextCharIs(expressionScanner, UNION_OPERATOR)){
				
			}else if(nextCharIs(expressionScanner, COMPLEMENT_OPERATOR)){
				
			}else if(nextCharIs(expressionScanner, SYMMETRIC_DIFFERENCE_OPERATOR)){
				
			}else{
				throw new APException("omdat er een teken is gevonden dat niet klopt");
			}
		}
	}
	
	Identifier readIdentifier(Scanner identifierScanner)throws APException{
		Identifier result;
		if(nextCharIsLetter(identifierScanner)){
			result = new Identifier(nextChar(identifierScanner));
			while(!nextCharIs(identifierScanner, IDENTIFIER_EXPRESSION_SEPERATOR)){
				readWhiteSpaces(identifierScanner);
				if(nextCharIsAlphanumeric(identifierScanner)){
					result.addCharacter(nextChar(identifierScanner));
				}else{
					throw new APException("Identifiers should only consist of alphanumeric characters and should be separated by \"=\".");
				}
			}
		}else{
			throw new APException("Identifiers should start with a letter.");
			//This exception should not be thrown as this is checked earlier.
		}
		return result;
	}
	
	void processPrintStatement(Scanner printStatementScanner) {
		printStatementScanner.next();
	}

	void processAssignment(Scanner assignmentScanner)throws APException {
		Identifier test = readIdentifier(assignmentScanner);
		out.printf("%d", test.length());
	}

	char nextChar(Scanner in){
		return in.next().charAt(0);
	}
	
	boolean nextCharIsAlphanumeric(Scanner in){
		return nextCharIsDigit(in)||nextCharIsLetter(in);
	}
	
	boolean nextCharIs(Scanner in, char c){
		return in.hasNext(Pattern.quote(c+""));
	}

	boolean nextCharIsDigit(Scanner in){
		return in.hasNext(DIGIT_PATTERN);
	}
	
	boolean nextCharIsLetter(Scanner in){
		return in.hasNext(LETTER_PATTERN);
	}

	void readWhiteSpaces(Scanner in){
		while(in.hasNext(" ")){
			in.next();
		}
	}

	void processInput(Scanner inputScanner)throws APException {
		readWhiteSpaces(inputScanner);
		if(nextCharIsLetter(inputScanner)){
			processAssignment(inputScanner);
		}else if(nextCharIs(inputScanner, PRINT_STATEMENT_TYPE_MARKER)){
			processPrintStatement(inputScanner);
		}else if(nextCharIs(inputScanner, COMMENT_TYPE_MARKER)){
			//does nothing when a comment is detected
		}else{
			throw new APException("Input error, expecting a letter, question mark or back-slash.");
		}
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
