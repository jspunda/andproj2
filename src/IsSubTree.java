
public class IsSubTree {
	
	private boolean isSub;
	
	public IsSubTree() {}
	
	public boolean areIdentical(Exp e1, Exp e2) {
	    if (e1 == null && e2 == null) {
	        return true;
	    }
	    if (e1 == null || e2 == null) {
	        return false;
	    }
	    return e1.value.equals(e2.value) && areIdentical(e1.left, e2.left) && areIdentical(e1.right, e2.right);
	}
	 
	/* This function returns true if e1 is a subtree of e2, otherwise false */
	public boolean subTree(Exp e1, Exp e2) {
	    /* base cases */
	    if (e2 == null) {
	        return true;
	    }
	    if (e1 == null) {
	    	return false;
	    }
	    if (areIdentical(e1, e2)) {
	        return true;
	    }
	    return subTree(e1.left, e2) || subTree(e1.right, e2);
	}
	 
	
	public boolean getisSub() {
		return isSub;
	}
}
