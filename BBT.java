/**
 * A self-balancing binary search tree.
 */
public class BBT {
	// Fields
	private BBN root;

	/**
	 * Initialize an empty tree.
	 */
	public BBT() {
		this.root = null;
	}

	/**
	 * Insert a VLS into the tree.
	 * 
	 * @param s
	 */
	public void insert(VLS s) {
		root = insert(s, root);
	}

	/**
	 * Insert a VLS into a subtree.
	 * 
	 * @param s
	 * @param node
	 * @return
	 */
	private BBN insert(VLS s, BBN node) {
		// TODO
		return null;
	}

	/**
	 * Remove a VLS from the tree.
	 * 
	 * @param s
	 */
	public void remove(VLS s) {
		root = remove(s, root);
	}

	/**
	 * Remove a VLS from a subtree.
	 * 
	 * @param s
	 * @param node
	 * @return
	 */
	private BBN remove(VLS s, BBN node) {
		// TODO
		return null;
	}

	/**
	 * Report all VLS's currently in the tree that lie between min and max.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public VLS[] printRange(int min, int max) {
		//TODO
		return null;
	}
}
