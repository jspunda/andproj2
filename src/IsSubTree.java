
public class IsSubTree {
	
	private boolean isSub;
	
	public IsSubTree(Exp e1, Exp e2) {
		isSub = subTree(e1,e2);
	}
	
	
	public boolean getisSub() {
		return isSub;
	}
}
