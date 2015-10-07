package assignment2;

/**
 * ADT for the class NaturalNumber
 * 
 * @author Erik Baalhuis & Niels van der Molen
 * @elements Characters of type char.
 * @structure Linear.
 * @domain Any number of digits. No leading zeros for non-zero numbers.
 * 
 * @constructor NaturalNumberInterface(char c);
 * @precondition c is a digit.
 * @postcondition A new NaturalNumberInterface object is created with value c.
 **/

public interface NaturalNumberInterface extends Data<NaturalNumberInterface> {

	/**
	 * Initializes the NaturalNumber object with value c.
	 * 
	 * @precondition: c is a digit.
	 * 
	 * @postcondition: The NaturalNumberIdentifer object has value c.
	 */
	void init(char c);
	
	/**
	 * Checks if the current value is zero.
	 * 
	 * @precondition: None.
	 * 
	 * @postcondition:
	 * 		TRUE: The current value is zero.
	 * 		FALSE: The current value is non-zero.
	 */
	boolean isZero();

	// Elementary operations for read & write

	/**
	 * Adds a digit c to the NaturalNumberInterface at the end.
	 * If the current value is zero, removes the leading zero.
	 * 
	 * @precondition Character c is a digit.
	 * 
	 * @postcondition The digit c is added to the end of the
	 * NaturalNumberInterface. Leading zeros are removed.
	 */
	void addDigit(char c);

	/**
	 * Returns the digit at position pos.
	 * 
	 * @precondition pos must be less than length().
	 * 
	 * @postcondition The digit at position pos is returned as a char.
	 */
	char getDigitAt(int pos);

	/**
	 * Returns an identifier as a String object.
	 * 
	 * @precondition None.
	 * 
	 * @postcondition The content of the NaturalNumberInterface is returned as a
	 * String object.
	 */
	String toString();

	// Natural operations

	/**
	 * Checks the amount of characters of an identifier
	 * 
	 * @precondition None.
	 * 
	 * @postcondition The amount of digits the NaturalNumberInterface contains
	 * is returned in type int.
	 */
	int length();
}