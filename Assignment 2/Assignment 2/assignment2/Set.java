package assignment2;

public class Set<E extends Data<E>> implements SetInterface<E> {

	List<E> content;

	Set() {
		content = new List<E>();
	}

	public Set<E> clone() {
		Set<E> result = new Set<E>();
		result.content = content.clone();
		return result;
	}

	public void init() {
		content = new List<E>();
	}

	public boolean contains(E element) {
		return content.find(element);
	}

	public void add(E element) {
		if (!contains(element)) {
			content.insert(element);
		}
	}

	public void remove(E element) {
		content.find(element);
		content.remove();
	}

	public E get() {
		E result = content.retrieve();
		return result;
	}

	public boolean isEqual(Set<E> set2) {
		if (size() != set2.size()) {
			return false;
		}
		Set<E> temp = clone();
		for (int i = 0; i < size(); i++) {
			E data = temp.get();
			temp.remove(data);
			if (!set2.contains(data)) {
				return false;
			}
		}
		return true;
	}

	public int size() {
		return content.size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Set<E> difference(Set<E> set2) {
		Set<E> result = clone();
		Set<E> temp = set2.clone();
		for (int i = 0; i < temp.size(); i++) {
			E data = temp.get();
			temp.remove(data);
			result.remove(data);
		}
		return result;
	}

	public Set<E> intersection(Set<E> set2) {
		Set<E> result = new Set<E>();
		Set<E> temp = clone();
		for (int i = 0; i < temp.size(); i++) {
			E data = temp.get();
			temp.remove(data);
			if (set2.contains(data)) {
				result.add(data);
			}
		}
		return result;
	}

	public Set<E> union(Set<E> set2) {
		Set<E> result = clone();
		Set<E> temp = set2.clone();
		for (int i = 0; i < temp.size(); i++) {
			E data = temp.get();
			temp.remove(data);
			result.add(data);
		}
		return result;
	}

	public Set<E> symmetricDifference(Set<E> set2) {
		return difference(set2).union(set2.difference(this));
	}
}
