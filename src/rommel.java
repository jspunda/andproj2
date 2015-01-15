
public class rommel {
	private String input;
	private int counter;
	private Exp root;
	
	public rommel(String input)  {
		this.input = input;
		counter = 0;
		BOUW(this.input);
	}
	
	private String[] splitComma(String input) {
		//System.out.println(input);
		String[] returnString = new String[2];
		int countBrackets = 0;
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(')
				countBrackets++;
			else if (input.charAt(i) == ')')
				countBrackets--;
			else if(countBrackets == 1 && input.charAt(i) == ',') {
				returnString[0] = input.substring(2, i);
				returnString[1] = input.substring(i+1, input.length()-1);
				break;
			}
		}
		return returnString;
	}
	
	public Exp BOUW(String in) {
		counter++;
		boolean isFExp = false;
		StringBuilder s = new StringBuilder();
		if (in.contains("(")) {
			isFExp = true;
		}
		int i;
		for(i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (c !='(') {
				s.append(in.charAt(i));
			} else {
				break;
			}
		}
		if(isFExp) {
			String n = in.substring(i-1);
			String left = splitComma(n)[0];
			String right = splitComma(n)[1];
			return new FExp(s.toString(), counter, BOUW(left), BOUW(right));
		}
		return new SExp(s.toString(), counter);
	}
	
	public Exp getRoot(){
		return root;
	}
}
