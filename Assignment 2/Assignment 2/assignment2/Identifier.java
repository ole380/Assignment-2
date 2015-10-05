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
	
	public int compareTo(Identifier other) {
		return content.toString().compareTo(other.content.toString());
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

	public char removeLastCharacter() {
		char result = content.charAt(length-1);
		content.setLength(length-1);
		return result;
	}

	public int length() {
		return length;
	}
}
