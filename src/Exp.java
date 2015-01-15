
public abstract class Exp {
	
	protected Exp left;
	protected Exp right;
	protected String value;
	protected int counter;
	
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
	
	public String toString() {
	    return toString(this);
	}

	private String toString(Exp exp) {
	    if (exp == null) {
	        return null;
	    }
	    return "[" + toString(exp.left) + "," + exp.value + "," + toString(exp.right) + "]";
	}
	
	public boolean isEqual(Exp e){
		return this.value.equals(e.value);
	}
}
