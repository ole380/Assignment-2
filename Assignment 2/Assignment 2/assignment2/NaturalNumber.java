package assignment2;


public class NaturalNumber implements NaturalNumberInterface {

	private StringBuffer content;
	private int length;

	NaturalNumber(char c) {
		content = new StringBuffer();
		content.append(c);
		length = 1;
	}
	
	public NaturalNumber clone() {
		char firstDigit = content.charAt(0);
		NaturalNumber result = new NaturalNumber(firstDigit);
		for (int i = 1; i < length; i++) {
			result.content.append(content.charAt(i));
		}
		return result;
	}

	public int compareTo(NaturalNumberInterface other) {
		if (length > other.length()) {
			return 1;
		} else if (length < other.length()) {
			return -1;
		} else { // both have the same number of digits
			for (int i = 0; i < length; i++) {
				int digit = Character.getNumericValue(getDigitAt(i));
				int digitOther = Character.getNumericValue(other.getDigitAt(i));
				if (digit != digitOther) {
					return Integer.compare(digit, digitOther);
				}
			}
		}
		return 0;
	}

	public void init(char c) {
		content = new StringBuffer();
		content.append(c);
		length = 1;
	}

	public boolean isZero() {
		return content.charAt(0) == '0';
	}

	public void addDigit(char c) {
		if (isZero()) {
			init(c);
		} else {
			content.append(c);
			length++;
		}
	}

	public char getDigitAt(int pos) {
		return content.charAt(pos);
	}
	
	public String toString() {
		return content.toString();
	}

	public int length() {
		return length;
	}

}
