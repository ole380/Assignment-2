package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;
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
	Table<IdentifierInterface, SetInterface<NaturalNumberInterface>> setTable;
	PrintStream out;

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

	NaturalNumberInterface readNaturalNumber(Scanner naturalNumberScanner)throws APException{
		NaturalNumberInterface result = new NaturalNumber(nextChar(naturalNumberScanner));
		while(!nextCharIs(naturalNumberScanner, NATURAL_NUMBER_SEPERATOR) && !nextCharIs(naturalNumberScanner, SET_CLOSE_MARK)){
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

	SetInterface<NaturalNumberInterface> readSet(Scanner setScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		nextChar(setScanner);
		while(!nextCharIs(setScanner, SET_CLOSE_MARK)){
			if(nextCharIsDigit(setScanner)){
				result.add(readNaturalNumber(setScanner));
				if(nextCharIs(setScanner, NATURAL_NUMBER_SEPERATOR)){
					nextChar(setScanner);
				}
			}else{
				throw new APException("Error in input: Sets can only contain natural numbers seperated by ',' and should be closed by '}'.");
			}
		}
		nextChar(setScanner);
		return result;
	}

	SetInterface<NaturalNumberInterface> readFactor(Scanner factorScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		if(nextCharIsLetter(factorScanner)){
			IdentifierInterface key = readIdentifier(factorScanner, false);
			result = setTable.find(key);
		}else if(nextCharIs(factorScanner, SET_OPEN_MARK)){
			result = readSet(factorScanner);
		}else if(nextCharIs(factorScanner, COMPLEX_FACTOR_OPEN_MARK)){
			nextChar(factorScanner);
			result = readExpression(factorScanner);
			nextChar(factorScanner);
		}else{
			throw new APException("Error in input:");
		}
		return result;
	}

	SetInterface<NaturalNumberInterface> readTerm(Scanner termScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		result = readFactor(termScanner);
		while(nextCharIs(termScanner, INTERSECTION_OPERATOR)){
			nextChar(termScanner);
			result = result.intersection(readFactor(termScanner));
		}
		return result;
	}

	SetInterface<NaturalNumberInterface> readExpression(Scanner expressionScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		result = readTerm(expressionScanner);
		while (nextCharIsAdditiveOperator(expressionScanner)){
			if(nextCharIs(expressionScanner, UNION_OPERATOR)){
				nextChar(expressionScanner);
				result = result.union(readTerm(expressionScanner));
			}else if(nextCharIs(expressionScanner, COMPLEMENT_OPERATOR)){
				nextChar(expressionScanner);
				result = result.difference(readTerm(expressionScanner));
			}else if(nextCharIs(expressionScanner, SYMMETRIC_DIFFERENCE_OPERATOR)){
				nextChar(expressionScanner);
				result = result.symmetricDifference(readTerm(expressionScanner));
			}else{
				throw new APException("wrong character detected");
			}
		}
		return result;
	}

	IdentifierInterface readIdentifier(Scanner identifierScanner, boolean isAssignment)throws APException{
		IdentifierInterface result;
		if(nextCharIsLetter(identifierScanner)){
			result = new Identifier(nextChar(identifierScanner));
			while(identifierScanner.hasNext() && !nextCharIs(identifierScanner, IDENTIFIER_EXPRESSION_SEPERATOR)){
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
		nextChar(printStatementScanner);
		SetInterface<NaturalNumberInterface> value = readExpression(printStatementScanner);
		String result = "";
		while(!value.isEmpty()){
			NaturalNumberInterface naturalNumber = value.get();
			value.remove(naturalNumber);
			result += naturalNumber.toString() + " ";	
		}
		if(!(result.length() == 0)){
			result = result.substring(0, result.length()-1);
		}
		out.printf("%c%s%c\n", SET_OPEN_MARK, result, SET_CLOSE_MARK);
	}

	void processAssignment(Scanner assignmentScanner)throws APException {
		IdentifierInterface key = readIdentifier(assignmentScanner, true);
		nextChar(assignmentScanner);
		SetInterface<NaturalNumberInterface> value = readExpression(assignmentScanner);
		setTable.add(key, value);
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
