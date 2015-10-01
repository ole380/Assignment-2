package assignment2;

/** ADT for the class Set<E>
 * 	@author
 * 		Erik Baalhuis & Niels van der Molen
 * 	@elements
 * 		Elements are of type E.
 * 	@structure
 * 		None.
 * 	@domain
 * 		Any number of elements.
 * 
 * 	@constructor
 * 	
 * 	Set<E>();
 * 		@precondition
 *  		None.
 * 		@postcondition
 * 			A new empty Set<E> object is created.
 **/

public interface SetInterface<E extends Data<E>> extends Clonable<SetInterface<E>> {

	/** Initializes the Set<E> object to the empty set. 
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The set is empty.
	 **/
	void init();

	/** Checks if an element is in the set.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		True: The element is in the set.
	 * 		False: The element is not in the set.
	 **/
	boolean contains(E element);

	/** Adds an element to the collection.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The element is in the POST-set.
	 **/
	void add(E element);

	/** Removes an element from the set.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The element is not in the POST-set.
	 **/
	void remove(E element);

	/** Return element that is in the set.
	 * @precondition
	 * 		The set is not empty.
	 * @postcondition
	 * 		An element of type E is returned.
	 **/
	E get();

	/**  Checks if this set is equal to set2.
	 * @precondition
	 * 		None.
	 * @POST-condition
	 * 		True: The sets are equal.
	 * 		False: The sets are not equal.
	 **/
	boolean isEqual(SetInterface<E> set2);

	/**  Returns the number of elements.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The number of elements in the set is returned as an int.
	 **/
	int size();

	/**  Checks if the set is empty.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		True: The set is empty.
	 * 		False: The set is not empty.
	 **/
	boolean isEmpty();

	/** Determines the difference of two sets.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The difference of this set with set2 is returned as a Set<E> object.
	 **/
	SetInterface<E> difference(SetInterface<E> set2);

	/** Determines the intersection of the sets.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The intersection of this set with set2 is returned as a Set<E> object.
	 **/
	SetInterface<E> intersection(SetInterface<E> set2);

	/** Determines the union of the sets.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The union of this set with set2 as a Set<E> object.
	 **/
	SetInterface<E> union(SetInterface<E> set2);

	/** Determines the symmetric difference of the sets.
	 * @precondition
	 * 		None.
	 * @postcondition
	 * 		The symmetric difference of this set with set2 is returned as a Set<E> object.
	 **/
	SetInterface<E> symmetricDifference(SetInterface<E> set2);
}