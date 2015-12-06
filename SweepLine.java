/**
 * An abstraction of the algorithm sweeping down the graph. Holds the current
 * vertical line segments in s balanced binary search tree.
 */
public class SweepLine {
	// Fields
	private BBT tree;

	/**
	 * Initialize a SweepLine with an empty tree.
	 */
	public SweepLine() {
		tree = new BBT();
	}

	/**
	 * Perform the necessary action based on the type of e.
	 * 
	 * @param e
	 */
	public void respondToEvent(Event e) {
		if (e.type == EventType.UPPER) {
			addSegment(e.segment);
		} else if (e.type == EventType.LOWER) {
			removeSegment(e.segment);
		} else { // horizontal line
			printRange(e);
		}
	}

	/**
	 * Insert a VLS into the tree.
	 * 
	 * @param e
	 */
	private void addSegment(LineSegment s) {
		if (s instanceof VLS) {
			VLS v = (VLS) s;
			tree.insert(v);
		} else {
			// you have tried to insert a HLS into the tree
		}
	}

	/**
	 * Remove a VLS from the tree.
	 * 
	 * @param e
	 */
	private void removeSegment(LineSegment s) {
		if (s instanceof VLS) {
			VLS v = (VLS) s;
			tree.remove(v);
		} else {
			// you have tried to remove a HLS from the tree
		}
	}

	/**
	 * Report all line segments in the tree that lie within the range of e.
	 * 
	 * @param e
	 */
	private void printRange(Event e) {
		tree.printRange(e.p1.getX(), e.p2.getX());
	}

}
