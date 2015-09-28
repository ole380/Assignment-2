package assignment2;

/** ADT for the class Variable
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements 
 * 		A pair of a key of type K and a value of type V.
 * 	@structure
 * 		None.
 * 	@domain 
 * 		One pair.
 * 
 * 	@constructor
 * 	Variable(K key, V value);
 * 		precondition
 * 			None.
 * 		postcondition
 * 			A new Variable is created with key key and value value.
 * 
 * 	Variable(VariableInterface src);
 * 		precondition
 * 			None.
 * 		postcondition
 * 			A new Variable object which is a copy of src is created.
 * 	
 **/	

public interface VariableInterface<K extends Data<K>,V extends Clonable<V>> extends Data<K>{
	
	/* Initializes the Variable object with key key and value value.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The Variable has key key and value value
	 */
	void init(K key, V value);
	
	// Natural operations
	
	/* Compares two variables by key.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		Return 1: the key of this variable is bigger than that of variable2.
	 * 		Return 0: the key of this number is equal to that of variable2.
	 * 		Return -1: the key of this number is smaller than that of variable2.
	 */
	int compareTo(VariableInterface<K,V> variable2);
	
	/* Change the key of this variable to key.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The Variable has key key.
	 */
	void setKey(K key);
	
	/* Change the value of this variable to value.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The Variable has key key.
	 */
	void setValue(V value);
	
	/* Return the key of this variable.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The key of this variable is returned.
	 */
	K getKey();
	
	/* Return the value of this variable.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The value of this variable is returned.
	 */
	V getValue();
	
}