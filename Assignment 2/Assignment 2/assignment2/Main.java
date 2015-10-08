package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.InputStream;
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

	NaturalNumberInterface readNaturalNumber(Scanner naturalNumberScanner)throws APException{
		NaturalNumberInterface result = new NaturalNumber(nextChar(naturalNumberScanner, false));
		while(!nextCharIs(naturalNumberScanner, NATURAL_NUMBER_SEPERATOR) && !nextCharIs(naturalNumberScanner, SET_CLOSE_MARK)){
			if(result.isZero() && nextCharIs(naturalNumberScanner, '0')){
				throw new APException("Error in input: Zero should be entered as '0', natural numbers should be seperated by ','.");
			}else if(nextCharIsDigit(naturalNumberScanner)){
				result.addDigit(nextChar(naturalNumberScanner, false));
			}else if(nextCharIs(naturalNumberScanner, ' ')){
				readWhiteSpaces(naturalNumberScanner);
				if (!(nextCharIs(naturalNumberScanner, NATURAL_NUMBER_SEPERATOR) || nextCharIs(naturalNumberScanner, SET_CLOSE_MARK))){
					throw new APException("Error in input: natural numbers cannot contain whitespaces and should be seperated by ','.");
				}
			}else{
				throw new APException("Error in input: natural numbers can only consist of digits and should be seperated by ','.");
			}
		}
		return result;
	}

	SetInterface<NaturalNumberInterface> readSet(Scanner setScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		nextChar(setScanner, true);
		while(!nextCharIs(setScanner, SET_CLOSE_MARK)){
			if(nextCharIsDigit(setScanner)){
				result.add(readNaturalNumber(setScanner));
				if(nextCharIs(setScanner, NATURAL_NUMBER_SEPERATOR)){
					nextChar(setScanner, true);
				}
			}else{
				throw new APException("Error in input: Sets can only contain natural numbers seperated by ',' and should be closed by '}'.");
			}
		}
		nextChar(setScanner, true);
		return result;
	}

	SetInterface<NaturalNumberInterface> readFactor(Scanner factorScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		if(nextCharIsLetter(factorScanner)){
			IdentifierInterface key = readIdentifier(factorScanner, false);
			if(setTable.contains(key)){
				result = setTable.find(key);
			}else{
				out.printf("Identifier %s",key.toString());
				throw new APException(" has no value assigned to it.");
			}
		}else if(nextCharIs(factorScanner, SET_OPEN_MARK)){
			result = readSet(factorScanner);
		}else if(nextCharIs(factorScanner, COMPLEX_FACTOR_OPEN_MARK)){
			nextChar(factorScanner, true);
			result = readExpression(factorScanner);
			if(factorScanner.hasNext()){
				nextChar(factorScanner, true);
			}else{
				throw new APException("A ')' was expected to close the factor, but no character could be read.");
			}
		}else{
			throw new APException("Error in input:");
		}
		return result;
	}

	SetInterface<NaturalNumberInterface> readTerm(Scanner termScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		result = readFactor(termScanner);
		while(nextCharIs(termScanner, INTERSECTION_OPERATOR)){
			nextChar(termScanner, true);
			result = result.intersection(readFactor(termScanner));
		}
		return result;
	}

	SetInterface<NaturalNumberInterface> readExpression(Scanner expressionScanner)throws APException{
		SetInterface<NaturalNumberInterface> result = new Set<NaturalNumberInterface>();
		result = readTerm(expressionScanner);
		while (nextCharIsAdditiveOperator(expressionScanner)){
			if(nextCharIs(expressionScanner, UNION_OPERATOR)){
				nextChar(expressionScanner, true);
				result = result.union(readTerm(expressionScanner));
			}else if(nextCharIs(expressionScanner, COMPLEMENT_OPERATOR)){
				nextChar(expressionScanner, true);
				result = result.difference(readTerm(expressionScanner));
			}else if(nextCharIs(expressionScanner, SYMMETRIC_DIFFERENCE_OPERATOR)){
				nextChar(expressionScanner, true);
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
			result = new Identifier(nextChar(identifierScanner, false));
			while(!nextCharIs(identifierScanner, IDENTIFIER_EXPRESSION_SEPERATOR) && !nextCharIsOperator(identifierScanner) && identifierScanner.hasNextLine()){
				if(nextCharIsAlphanumeric(identifierScanner)){
					result.addCharacter(nextChar(identifierScanner, false));
				}else if(nextCharIs(identifierScanner, ' ')){
					readWhiteSpaces(identifierScanner);
					if (!((nextCharIs(identifierScanner, IDENTIFIER_EXPRESSION_SEPERATOR)) || nextCharIsAdditiveOperator(identifierScanner))){
						throw new APException("Error in input: identifiers cannot contain whitespaces and should be seperated by '='.");
					}
				}else{
					if(isAssignment == true){
						throw new APException("Identifiers should only consist of alphanumeric characters and should be separated from expressions by '='.");
					}else{
						throw new APException("Identifiers should only consist of alphanumeric characters.");
					}
				}
			}
		}else{
			throw new Error("The program made an attempt to read an identifier that starts with another character than a letter, this should not be possible.");
			//This error should not be thrown as this is checked earlier in the program.
		}
		return result;
	}

	void processPrintStatement(Scanner printStatementScanner)throws APException {
		nextChar(printStatementScanner,true);
		SetInterface<NaturalNumberInterface> value = readExpression(printStatementScanner);
		if (printStatementScanner.hasNext()) {
			throw new APException("Error: expressions must be followed by end of line.");
		}
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
		if(assignmentScanner.hasNext()){
			nextChar(assignmentScanner, true);
		}else{
			throw new APException("A '=' was expected to assign a value, but no character could be read.");
		}
		SetInterface<NaturalNumberInterface> value = readExpression(assignmentScanner);
		if (assignmentScanner.hasNext()) {
			throw new APException("Error: expressions must be followed by end of line.");
		}
		if(setTable.contains(key)){
			setTable.remove(key);
		}
		setTable.add(key, value);
	}

	char nextChar(Scanner in, boolean whiteSpaceAllowed){
		char result = in.next().charAt(0);
		if(whiteSpaceAllowed){
			readWhiteSpaces(in);
		}
		return result;
	}

	boolean nextCharIsAlphanumeric(Scanner in){
		return nextCharIsDigit(in)||nextCharIsLetter(in);
	}

	boolean nextCharIsAdditiveOperator(Scanner in){
		return in.hasNext(Pattern.quote(UNION_OPERATOR+"")) || in.hasNext((Pattern.quote(COMPLEMENT_OPERATOR+""))) || in.hasNext(Pattern.quote(SYMMETRIC_DIFFERENCE_OPERATOR+""));
	}
	
	boolean nextCharIsOperator(Scanner in) {
		return in.hasNext(Pattern.quote(INTERSECTION_OPERATOR+"")) || nextCharIsAdditiveOperator(in);
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

	void readStatement(Scanner inputScanner)throws APException {
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

	void start() {
		in = new Scanner(System.in);		
		while(in.hasNextLine()){
			Scanner inputScanner = new Scanner(in.nextLine());
			inputScanner.useDelimiter("");
			readWhiteSpaces(inputScanner);
			try {
				readStatement(inputScanner);
			} catch (APException e) {
				out.printf("%s\n", e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new Main().start();

	}
}
