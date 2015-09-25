package assignment2;

/** ADT for the class NaturalNumber
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements 
 * 		Integers of type int.
 * 	@structure
 * 		None.
 * 	@domain 
 * 		One non-negative integer.
 * 
 * 	@constructor
 * 	NaturalNumber(int n);
 * 		precondition
 * 			n is non-negative.
 * 		postcondition
 * 			A new NaturalNumber is created with value n.
 * 
 * 	NaturalNumber(NaturalNumber src);
 * 		precondition
 * 			None.
 * 		postcondition
 * 			A new NaturalNumber object which is a copy of src is created.
 * 	
 **/	

public interface NaturalNumberInterface extends Data<Integer>{
	
	/* Initializes the NaturalNumber object with value n.
	 *  @precondition:
	 *  	n is non-negative.
	 *  @postcondition:
	 *  	The NaturalNumber object has value n.
	 */
	void init(int n);
	
	// Natural operations
	
	/* Compares two natural numbers.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		Return 1: the value of this number is bigger than that of number2.
	 * 		Return 0: the value of this number is equal to that of number2.
	 * 		Return -1: the value of this number is smaller than that of number2.
	 */
	int compareTo(NaturalNumberInterface number2);
	
}