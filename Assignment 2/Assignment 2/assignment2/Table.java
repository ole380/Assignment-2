package assignment2;

public class Table<K extends Data<K>, V extends Clonable<V>> implements TableInterface<K,V>{

	List<Variable<K,V>> content;
	
	Table() {
		content = new List<Variable<K,V>>();
	}
	
	public Table<K, V> clone() {
		Table<K,V> result = new Table<K,V>();
		result.content = content.clone();
		return result;
	}

	public void init() {
		content.init();
	}

	public boolean contains(K key) {
		Variable<K,V> temp = new Variable<K,V>(key,null);
		return content.find(temp);
	}

	public V find(K key) {
		Variable<K,V> temp = new Variable<K,V>(key,null);
		content.find(temp);
		return content.retrieve().value;
	}

	public void add(K key, V value) {
		Variable<K,V> newVar = new Variable<K,V>(key,value);
		if(contains(key)){
			remove(key);
		}
		content.insert(newVar);
	}
	
	public void remove(K key){
		Variable<K,V> temp = new Variable<K,V>(key,null);
		content.find(temp);
		content.remove();
	}
	
	private class Variable<K extends Data<K>, V extends Clonable<V>> implements Data<Variable<K,V>>{
		K key;
		V value;
		
		Variable(K argKey, V argValue) {
			key = argKey;
			value = argValue == null ? null : argValue;
		}
		
		public Variable<K,V> clone() {
			K newKey = key.clone();
			V newValue = value.clone();
			Variable<K,V> result = new Variable<K,V>(newKey,newValue);
			return result;
		}
		
		public int compareTo(Variable<K,V> other) {
			return key.compareTo(other.key);
		}
	}
	
}
