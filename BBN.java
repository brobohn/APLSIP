/**
 * Balanced Binary Node - a node for a balanced binary tree. Each node holds a
 * Vertical Line Segment.
 */
public class BBN {
	// Fields
	VLS seg;
	BBN left;
	BBN right;

	int value;
	int height = 0;
    int size = 1;
	
	/**
	 * Initialize a childless node.
	 * 
	 * @param segment
	 *            the segment to store in this node
	 */
	public BBN(VLS segment) {
		this.seg = segment;
		this.left = null;
		this.right = null;
	}

	/**
	 * Compare two nodes.
	 * 
	 * @param other
	 * @return less than 0 if this segment is to the left of the argument's
	 *         segment.
	 */
	public int compareTo(BBN node) {
		return seg.compareTo(node.seg);
	}
}
