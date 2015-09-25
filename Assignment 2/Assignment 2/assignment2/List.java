package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

	int size;
	Node<E> current;
	
	List() {
		size = 0;
		current = null;
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
		Node<E> newNode = new Node<E>(d, current, null);
		current.next = newNode;
		current = newNode;
		size++;
		return this;
	}

	public E retrieve() {
		return current.data;
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

	public ListInterface<E> clone() {
		ListInterface<E> result = new List<E>();
		Node<E> originalCurrent = current;
		
		if (this.isEmpty()) {
			return result;
		} else { //list is not empty
			this.goToFirst();
			result.insert(current.data);
			while (this.goToNext()) {
				result.insert(current.data);
			}
			current = originalCurrent;
			return result;
		}
	}

}
