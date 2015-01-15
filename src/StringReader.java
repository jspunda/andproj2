
public class StringReader {
	
	private Exp exp;
	private String input;
	private StringBuilder builder;
	private int counter;
	
	public StringReader(String input) {
		this.input = input;
		counter = 0;
		builder = new StringBuilder();
		exp = buildExp(this.input);
	}
	
	private Exp buildExp(String input) {
		char head = input.charAt(0);
		switch (head) {
			case '(' :
				counter++;
				String temp = builder.toString();
				builder = new StringBuilder();
				return new FExp(temp, counter, buildExp(splitComma(input)[0]), buildExp(splitComma(input)[1]));
			default:
				builder.append(head);
				if(input.length() == 1) {
					String temp2 = builder.toString();
					builder = new StringBuilder();
					counter++;
					return new SExp(temp2, counter);
				}
				else 
					return buildExp(input.substring(1));
			}
}
	
	private String[] splitComma(String input) {
		String[] returnString = new String[2];
		int countBrackets = 0;
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(')
				countBrackets++;
			else if (input.charAt(i) == ')')
				countBrackets--;
			else if(countBrackets == 1 && input.charAt(i) == ',') { 
				returnString[0] = input.substring(1, i);
				returnString[1] = input.substring(i+1, input.length()-1);
				break;
			}
		}
		return returnString;
	}
	
	public Exp getExp() {
		return exp;
	}
}
