package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

	int size;
	Node<E> current;
	
	List() {
		size = 0;
		current = null;
	}
	
	public class Node<E extends Data<E>> {
	    E data;
	    Node<E> prior, next;

	    public Node(E d) {
	        this(d, null, null);
	    }

	    public Node(E data, Node<E> prior, Node<E> next) {
	        this.data = data == null ? null : data;
	        this.prior = prior;
	        this.next = next;
	    }

	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public ListInterface<E> init() {
		size = 0;
		current = null;
		return this;
	}

	public int size() {
		return size;
	}
	
	public ListInterface<E> insert(E d) {
		Node<E> newNode = new Node<E>(d, null, null);
		
		if (!isEmpty()) {
			if (current.data.compareTo(d) == 1) { 
				//newNode has to come first in the list
				newNode.next = current;
				current.prior = newNode;
			} else { //current points to last element <= d
				if (current.next != null) {
					newNode.next = current.next;
					current.next.prior = newNode;
				}
				newNode.prior = current;
				current.next = newNode;
			}
		}
		current = newNode;
		size++;
		return this;
	}

	public E retrieve() {
		return current.data.clone();
	}

	public ListInterface<E> remove() {
		size--;
		if (this.isEmpty()) {
			current = null;
		} else { //list is not empty
			if (current.next == null) { //the removed element was the last in the list
				current = current.prior;
				current.next = null;
			} else { //the removed element was not the last in the list
				current.prior.next = current.next;
				current.next.prior = current.prior;
				current = current.next;
			}
		}
		return this;
	}

	public boolean find(E d) {
		if (goToFirst()) {
			while (current.data.compareTo(d) == -1) {
				if (!goToNext()) {
					return false;
				}
			}
			if (current.data.compareTo(d) == 0) {
				return true;
			} else {
				goToPrevious();
			}
		}
		return false;
	}

	public boolean goToFirst() {
		if (this.isEmpty()) {
			return false;
		} else {
			while (current.prior != null) {
				current = current.prior;
			}
			return true;
		}
	}

	public boolean goToLast() {
		if (this.isEmpty()) {
			return false;
		} else {
			while (current.next != null) {
				current = current.next;
			}
			return true;
		}
	}

	public boolean goToNext() {
		if (current.next == null) {
			return false;
		} else {
			current = current.next;
			return true;
		}
	}

	public boolean goToPrevious() {
		if (current.prior == null) {
			return false;
		} else {
			current = current.prior;
			return true;
		}
	}

	public List<E> clone() {
		List<E> result = new List<E>();
		Node<E> originalCurrent = current;
		
		if (this.isEmpty()) {
			return result;
		} else { //list is not empty
			this.goToFirst();
			result.insert(current.data.clone());
			while (this.goToNext()) {
				result.insert(current.data.clone());
			}
			result.current = originalCurrent;
			current = originalCurrent;
			return result;
		}
	}
	
//	public List<E> clone2(){
//		List<E> copy;
//		try {
//			copy = (List<E>)super.clone();
//		} catch (CloneNotSupportedException e) {
//			throw new Error("Unexpected error in clone method");
//			//we do not expect an error here as List<E> is clonable.
//		}
//		copy.current = (List<E>.Node<E>) current.clone();
//	}
}
