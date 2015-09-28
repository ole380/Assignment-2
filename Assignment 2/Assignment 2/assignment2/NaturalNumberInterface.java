package assignment2;

/**
 * ADT for the class NaturalNumber
 * 
 * @author Erik Baalhuis & Niels van der Molen
 * @elements Characters of type char.
 * @structure Linear.
 * @domain Any number of digits. No leading zeros.
 * 
 * @constructor NaturalNumberInterface(char c);
 * @precondition c is a non-zero digit.
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

	// Elementary operations for read & write

	/**
	 * Adds a digit c to the NaturalNumberInterface at the end.
	 * 
	 * @precondition Character c is a digit.
	 * 
	 * @postcondition The digit c is added to the end of the
	 * NaturalNumberInterface.
	 */
	void addDigit(char c);

	/**
	 * Removes and returns the last digit that was added to the
	 * NaturalNumberInterface.
	 * 
	 * @precondition The NaturalNumberInterface contains more than one element.
	 * 
	 * @postcondition The last digit is removed from the PRE-identifier, and
	 * returned.
	 */
	char removeLastDigit();

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