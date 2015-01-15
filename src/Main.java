
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "f(g(a,a),g(a,a))";
		String input2 = "f(sssa,a)";
		StringReader reader = new StringReader(input);
		StringReader reader2 = new StringReader(input2);
		Exp exp = reader.getExp();
		Exp exp2 = reader.getExp();
		System.out.println(exp.toString());
		System.out.println("Building expression DONE!");
		IsSubTree test = new IsSubTree(exp, exp2);
		System.out.println(test.getisSub());
	}

}
