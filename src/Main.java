import java.util.Scanner;


public class Main {

	public static IsSubTree sub = new IsSubTree();
	public static Exp target = null;
	
	public static void main(String[] args) {
		String input = "f(g(a,a),h(g(a,a),f))";
		StringReader reader = new StringReader(input);
		Exp output = reader.getExp();
		System.out.println(output.getString());
		pruneTree(output.left, output.right, output);
		System.out.println(output.getString());
	}
	
	public static void pruneTree(Exp left, Exp right, Exp parent) {
		if(left == null || right == null) {
			return;
		}
		System.out.println(left.getString() + " " + right.getString());
		if (sub.areIdentical(left, right)) {
			target = parent;
		} else {
			//System.out.println(left.getString() + " " + right.getString());
			dfsMatch(left, right);
		}
		if(target != null){
			Exp copy;
			if(left.left != null) {
				copy = new FExp(left);
			}
			else {
				copy = new SExp(left);
			}
			if(target.left == copy) {
				target.left = copy;
			} else {
				target.right = copy;
			}
			copy.changed = true;
			target = null;
		}
		pruneTree(left.left, left.right, left);
		pruneTree(right.left, right.right, right);
		
	}
	
	public static void dfsMatch(Exp match, Exp targetNode) {
		if (targetNode == null) {
			return;
		}
		if (sub.areIdentical(match, targetNode.right) || sub.areIdentical(match, targetNode.left)) {
			target = targetNode;
			//System.out.println(targetNode.getString());
		}
		dfsMatch(match,targetNode.left);
		dfsMatch(match,targetNode.right);
	}
}
