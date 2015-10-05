package assignment2;

/*ADT for the class Identifier
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements 
 * 		Characters of type char.
 * 	@structure
 * 		Linear.
 * 	@domain 
 * 		All combinations of one or more alphanumeric characters that start with a letter.
 * 
 * 	@constructor
 * 	Identifier(char c);
 * 		precondition
 * 			Character c is a letter.
 * 		postcondition
 * 			A new Identifier which contains the c is created.
 * 
 * 	Identifier(IdentifierInterface src);
 * 		precondition
 * 			None.
 * 		postcondition
 * 			A new Identifier object which is a copy of src is created.
 * 	
*/	
public interface IdentifierInterface extends Data<Identifier>{
	
	/* Initializes the Identifier object with content c.
	 *  @precondition:
	 *  	Character c is a letter.
	 *  @postcondition:
	 *  	The Identifier object contains only the content c.
	 */
	void init(char c);
	
	// Elementary operations for read & write
	
	/* Adds a character c to the Identifier.
	 * @precondition
	 * 		Character c is a letter.
	 * @postcondition
	 * 		The character c is added to the end of the Identifier.
	 */
	void addCharacter(char c);
	
	/* Removes and returns the last character that was added to the identifier.
	 * @precondition
	 * 		The identifier contains more than one element.
	 * @postcondition
	 * 		The character added last is removed from the PRE-identifier, and returned.
	 */
	char removeLastCharacter();
	
	/* Returns an identifier as a String object.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The content of the identifier is returned as a String object.
	 */
	String toString();
	
	// Natural operations
	
	/* Checks the amount of characters in an identifier.
	 * precondition
	 * 		None.
	 * postcondition
	 * 		The amount of characters the identifier contains is returned in type int.
	 */
	int length();
}