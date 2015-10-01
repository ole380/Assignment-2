package assignment2;

/*** ADT for the class Table
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements 
 * 		Variables that have a key and a value.
 * 	@structure
 * 		Linear.
 * 	@domain 
 * 		Any number of variables.
 * 
 * 	@constructor
 * 	Table();
 * 		@precondition
 * 			None.
 * 		@postcondition
 * 			A new empty table is created.
 * 	
 **/	

public interface TableInterface<Variable extends Clonable<Variable>, K extends Data<K>, V extends Clonable<V>> extends Data<TableInterface<Variable,K,V>> {

	/** Initializes the TableInterface object to the empty table.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The TableInterface is empty.
	 */
	void init();
	
	/** Find the value belonging to key K, if present.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The value belonging to K is returned, or null if K is not in the table's keys.
	 */
	V findValue(K key);
	
}
