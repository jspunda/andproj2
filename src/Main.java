import java.util.ArrayList;


public class Main {

	public static IsSubTree sub = new IsSubTree();
	public static Exp target = null;
	public static int changed = 0;
	public static ArrayList<Exp> madeChanges;
	public static ArrayList<Exp> changedExpr;
	
	public static void main(String[] args) {
		madeChanges = new ArrayList<Exp>();
		changedExpr = new ArrayList<Exp>();
		String input = "f(g(h(a,f(a,a)),f(a,b)),f(a,a))";
		//f(g(h(a,f(4,4)),f(4,b)),5)
		StringReader reader = new StringReader(input);
		Exp output = reader.getExp();
		buildArrayList(output);
		System.out.println(output.getString());
		//pruneTree(output.left, output.right, output);
		//System.out.println(output.getString());
		
		madeChanges.clear();
		output = reader.getExp();
		System.out.println("Bouwen 2");
		System.out.println(output.getString());
		buildArrayList2(output);
		//rewrite(output,0);
		System.out.println(output.getString());
	}
	
	public static void rewrite(Exp in, int count) {
		if (in == null) {
			return;
		}
		if(in.changed) {
			in = changedExpr.get(count);
		}
		rewrite(in.left, count++);
		rewrite(in.right, count++);
	}
	
	public static void buildArrayList(Exp in) {
		if(in == null) {
			return;
		}
		madeChanges.add(in);
		buildArrayList(in.left);
		buildArrayList(in.right);
	}
	
	public static int contained(Exp in) {
		for(int i=0; i < madeChanges.size();i++) {
			Exp a = madeChanges.get(i);
			if (a.getString().equals(in.getString())) {
				return i;
			}
		}
		return -1;
	}
	
	public static void buildArrayList2(Exp in) {
		if(in == null) {
			return;
		}
		int x = contained(in);
		if(x == -1) {
			madeChanges.add(in); 
		} else {
			System.out.println(in.getString());
			//in.changed = true;
			in = new SExp(x+1+"",x);
			//changedExpr.add(new SExp(x+1+"",x));
			System.out.println(in.getString());
		}
		buildArrayList2(in.left);
		buildArrayList2(in.right);
	}
	
	public static void pruneTree(Exp left, Exp right, Exp parent) {
		if(left == null || right == null) {
			return;
		}
		System.out.println(left.getString() + " en " + right.getString() + " met parent " + parent.getString());
		Exp copy;
		if (sub.areIdentical(left, right)) {
			target = parent;
			if(left.left != null) {
				copy = new FExp(left);
			}
			else {
				copy = new SExp(left);
			}
			copy.counter = copy.counter-changed;
			copy.changed = true;
			target.right = copy;
			changed++;
			target = null;
		} else {
			dfsMatch(left, right);
			if(target != null){
				if(left.left != null) {
					copy = new FExp(left);
				}
				else {
					copy = new SExp(left);
				}
				if(sub.areIdentical(target.left,copy)) {
					target.left = copy;
					if(sub.areIdentical(target.right, copy)) {
						target.right = copy;
					}
				}
				if (sub.areIdentical(target.right,copy)) {
					target.right = copy;
				}
				copy.counter = copy.counter-changed;
				copy.changed = true;
				changed++;
				target = null;
			}
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
		}
		dfsMatch(match,targetNode.left);
		dfsMatch(match,targetNode.right);
	}
}
