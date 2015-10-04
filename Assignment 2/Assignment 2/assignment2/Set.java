package assignment2;

public class Set<NaturalNumber> implements SetInterface {

	List<NaturalNumber> content;

	Set<NaturalNumber>()

	{
		content = new List<NaturalNumber>;
		size = 0;
	}

	public SetInterface clone() {
		Set result = new Set();
		for (int i = 0; i < size; i++) {
			// TODO
		}
		return result;
	}

	public void init() {
		content = new List<NaturalNumber>;
	}

	public boolean contains(NaturalNumber element) {
		for (int i = 0; i < size; i++) {
			return content.find(element);
	}

	public void add(NaturalNumber element) {
		content.insert(element);
	}

	public void remove(NaturalNumber element) {
		content.find(element);
		content.remove();
	}

	// This does not work, why? SetInterface.get() wants to return Data
	// instead of NaturalNumber.
	public NaturalNumber get() {
		NaturalNumber result = content.retrieve();
		content.remove();
		return result;
	}

	public boolean isEqual(Set set2) {
		if (content.size != set2.content.size) {
			return false;
		}
		for (int i = 0; i < content.size; i++) {
			//TODO
		}
	}

	public int size() {
		return content.size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public SetInterface difference(SetInterface set2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetInterface intersection(SetInterface set2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetInterface union(SetInterface set2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetInterface symmetricDifference(SetInterface set2) {
		// TODO Auto-generated method stub
		return null;
	}

}
