package assignment2;

/*** ADT for the class Table
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements 
 * 		Pairs of a key and a value.
 * 	@structure
 * 		None.
 * 	@domain 
 * 		Any number of pairs.
 * 
 * 	@constructor
 * 	Table();
 * 		@precondition
 * 			None.
 * 		@postcondition
 * 			A new empty table is created.
 * 	
 **/	

public interface TableInterface<K extends Data<K>, V extends Clonable<V>> extends Clonable<TableInterface<K,V>> {

	/** Initializes the TableInterface object to the empty table.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 *  	The TableInterface is empty.
	 */
	void init();
	
	/** Check if key K is in the table.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 * 		TRUE: K is in the table.
	 * 		FALSE: K is not in the table.
	 */
	boolean contains(K key);
	
	/** Find the value belonging to key K, if present.
	 *  @precondition:
	 *  	K is in the table.
	 *  @postcondition:
	 *  	The value belonging to K is returned.
	 */
	V find(K key);
	
	/** Add the pair (key, value) to the table.
	 *  If key is already present, overwrite the value.
	 *  @precondition:
	 *  	None.
	 *  @postcondition:
	 * 		The table contains (key, value).
	 */
	void add(K key, V value);
}
