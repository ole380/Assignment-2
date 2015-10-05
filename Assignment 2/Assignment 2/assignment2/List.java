package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

	int size;
	Node<E> current;
	Node<E> first;
	Node<E> last;

	List() {
		size = 0;
		current = null;
		first = null;
		last = null;
	}

	public List<E> clone() {
		List<E> result = new List<E>();
		Node<E> originalCurrent = current == null ? null : current;

		if (this.isEmpty()) {
			return result;
		} else { // list is not empty
			this.goToFirst();
			result.insert(current.data.clone());
			while (this.goToNext()) {
				result.insert(current.data.clone());
			}
			current = originalCurrent;
			result.find(current.data);
			return result;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public ListInterface<E> init() {
		size = 0;
		current = null;
		first = null;
		last = null;
		return this;
	}

	public int size() {
		return size;
	}

	public ListInterface<E> insert(E d) {
		Node<E> newNode = new Node<E>(d);

		if (!isEmpty()) {
			find(d);
			if (current.data.compareTo(d) == 1) {
				// newNode has to come first in the list
				newNode.next = current;
				current.prior = newNode;
				first = newNode;
			} else { // current points to last element <= d
				if (current.next != null) { // newNode comes after current and
											// will not be last
					newNode.next = current.next;
					current.next.prior = newNode;	
				}
				newNode.prior = current;
				current.next = newNode;
				last = newNode;
			}
		} else { // list was empty, so newNode will be first and last
			first = newNode;
			last = newNode;
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
		} else { // POST-list is not empty
			if (current.next == null) { // the removed element was the last in
										// the list
				current = current.prior;
				current.next = null;
			} else { // the removed element was not the last in the list
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
			current = first;
			return true;
		}
	}

	public boolean goToLast() {
		if (this.isEmpty()) {
			return false;
		} else {
			current = last;
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
	
	private class Node<E extends Data<E>> {
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
}
