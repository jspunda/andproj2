
public abstract class Exp {
	
	protected Exp left;
	protected Exp right;
	protected String value;
	protected int counter;
	protected boolean changed;
	
	public Exp(String value, int counter) {
		this.value = value;
		this.counter = counter;
	}
	
	public Exp(String value, int counter, Exp left, Exp right) {
		this.value = value;
		this.counter = counter;
		this.left = left;
		this.right = right;
	}
	
	public Exp(Exp e) {
		if(e.left == null) {
			this.left = null;
			this.right = null;
		}
		else {
			this.left = e.left;
			this.right = e.right;
		}
		this.value = e.value;
		this.counter = e.counter;
	}
	
	public String toString() {
	    return toString(this);
	}

	private String toString(Exp exp) {
	    if (exp == null) {
	        return null;
	    }
	    return "[" + toString(exp.left) + "," + exp.value + " " + exp.counter + "," + toString(exp.right) + "]";
	}
	
	public String getString() {
		if(changed)
			return "" + this.counter;
		if(left == null) {
			return this.value;
		}
		return this.value + "(" + left.getString() + "," + right.getString() + ")";
	}
}
