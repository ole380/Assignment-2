package assignment2;

public class Identifier implements IdentifierInterface {

	private StringBuffer content;
	private int length;

	Identifier(char c) {
		content = new StringBuffer();
		content.append(c);
		length = 1;
	}

	public Identifier clone() {
		char firstChar = content.charAt(0);
		Identifier result = new Identifier(firstChar);
		for (int i = 1; i < length; i++) {
			result.content.append(content.charAt(i));
		}
		return result;
	}

	public int compareTo(IdentifierInterface other) {
		return toString().compareTo(other.toString());
	}

	public void init(char c) {
		content = new StringBuffer();
		content.append(c);
		length = 1;
	}

	public void addCharacter(char c) {
		content.append(c);
		length++;
	}

	public char getCharAt(int pos){
		return content.charAt(pos);
	}

	public String toString(){
		return content.toString();
	}

	public int length() {
		return length;
	}
}