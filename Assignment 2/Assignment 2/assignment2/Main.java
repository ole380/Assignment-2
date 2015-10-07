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
						SYMMETRIC_DIFFERENCE_OPERATOR = '|',
						INTERSECTION_OPERATOR = '*',
						SET_OPEN_MARK = '{',
						SET_CLOSE_MARK = '}',
						COMPLEX_FACTOR_OPEN_MARK = '(',
						COMPLEX_FACTOR_CLOSE_MARK = ')',
						NATURAL_NUMBER_SEPERATOR = ',';

	static final String LETTER_PATTERN = "[a-zA-Z]",
						DIGIT_PATTERN = "[0-9]";

	Scanner in;
	Table<Identifier, SetInterface<NaturalNumber>> setTable;
	PrintStream out;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	Main() {
		setTable = new Table();
		out = new PrintStream(System.out);
	}

	String getInput(Scanner in) {
		if(!in.hasNextLine()){
			System.exit(0);
		}
		return in.nextLine();
	}

	NaturalNumber readNaturalNumber(Scanner naturalNumberScanner)throws APException{
		NaturalNumber result = new NaturalNumber(nextChar(naturalNumberScanner));
		while(!nextCharIs(naturalNumberScanner, NATURAL_NUMBER_SEPERATOR)){
			if(result.isZero() && nextCharIs(naturalNumberScanner, '0')){
				throw new APException("Error in input: Zero should be entered as '0', natural numbers should be seperated by ','.");
			}else if(nextCharIsDigit(naturalNumberScanner)){
				result.addDigit(nextChar(naturalNumberScanner));
			}else{
				throw new APException("Error in input: natural numbers can only consist of digits and should be seperated by ','.");
			}
		}
		return result;
	}
	
	Set<NaturalNumber> readSet(Scanner setScanner)throws APException{
		Set<NaturalNumber> result = new Set<NaturalNumber>();
		nextChar(setScanner);
		while(!nextCharIs(setScanner, SET_CLOSE_MARK)){
			if(nextCharIsDigit(setScanner)){
				result.add(readNaturalNumber(setScanner));
				nextChar(setScanner);
			}else{
				throw new APException("Error in input: Sets can only contain natural numbers seperated by ',' and should be closed by '}'.");
			}
			nextChar(setScanner);
		}
		return result;
	}
	
	Set<NaturalNumber> readFactor(Scanner factorScanner)throws APException{
		Set<NaturalNumber> result = new Set<NaturalNumber>();
		if(nextCharIsLetter(factorScanner)){
			Identifier key = readIdentifier(factorScanner, false);
		}else if(nextCharIs(factorScanner, SET_OPEN_MARK)){
			result = readSet(factorScanner);
		}else if(nextCharIs(factorScanner, COMPLEX_FACTOR_OPEN_MARK)){
			readExpression(factorScanner);
		}else{
			throw new APException("Error in input:");
		}
		//return result;
	}
	
	Set<NaturalNumber> readTerm(Scanner termScanner)throws APException{
		readFactor(termScanner);
		while(nextCharIs(termScanner, INTERSECTION_OPERATOR)){
			if(nextCharIs(termScanner, INTERSECTION_OPERATOR)){
				nextChar(termScanner);
				readFactor(termScanner);
			}else{
				throw new APException("wrong character detected");
			}
		}
	}
	
	Set<NaturalNumber> readExpression(Scanner expressionScanner)throws APException{
		readTerm(expressionScanner);
		while (nextCharIsAdditiveOperator(expressionScanner)){
			if(nextCharIs(expressionScanner, UNION_OPERATOR)){
				nextChar(expressionScanner);
				readTerm(expressionScanner);
			}else if(nextCharIs(expressionScanner, COMPLEMENT_OPERATOR)){
				nextChar(expressionScanner);
				readTerm(expressionScanner);
			}else if(nextCharIs(expressionScanner, SYMMETRIC_DIFFERENCE_OPERATOR)){
				nextChar(expressionScanner);
				readTerm(expressionScanner);
			}else if(nextCharIs(expressionScanner, COMPLEX_FACTOR_CLOSE_MARK)){
				
			}else{
				throw new APException("wrong character detected");
			}
		}
	}
	
	Identifier readIdentifier(Scanner identifierScanner, boolean isAssignment)throws APException{
		Identifier result;
		if(nextCharIsLetter(identifierScanner)){
			result = new Identifier(nextChar(identifierScanner));
			while(!nextCharIs(identifierScanner, IDENTIFIER_EXPRESSION_SEPERATOR)){
				if(nextCharIsAlphanumeric(identifierScanner)){
					result.addCharacter(nextChar(identifierScanner));
				}else{
					if(isAssignment == true){
						throw new APException("Identifiers should only consist of alphanumeric characters and should be separated from expressions by '='.");
					}else{
						throw new APException("Identifiers should only consist of alphanumeric characters .");
					}
				}
			}
		}else{
			throw new APException("Identifiers should start with a letter.");
			//This exception should not be thrown as this is checked earlier.
		}
		return result;
	}
	
	void processPrintStatement(Scanner printStatementScanner)throws APException {
	}

	void processAssignment(Scanner assignmentScanner)throws APException {
		Identifier test = readIdentifier(assignmentScanner, true);
		out.printf("%d", test.length());
	}

	char nextChar(Scanner in){
		char result = in.next().charAt(0);
		readWhiteSpaces(in);
		return result;
	}
	
	boolean nextCharIsAlphanumeric(Scanner in){
		return nextCharIsDigit(in)||nextCharIsLetter(in);
	}
	
	boolean nextCharIsAdditiveOperator(Scanner in){
		return in.hasNext((Pattern.quote(UNION_OPERATOR+""))) || in.hasNext((Pattern.quote(COMPLEMENT_OPERATOR+""))) || in.hasNext((Pattern.quote(SYMMETRIC_DIFFERENCE_OPERATOR+"")));
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
		inputScanner.close();
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
