/**
 * A Vertical Line Segment. Holds an upper and lower endpoint.
 */
public class VLS extends LineSegment implements Comparable<VLS> {
	// Fields
	public Point upper;
	public Point lower;

	/**
	 * Initialize a VLS with coordinates.
	 * 
	 * @param xLo
	 * @param xHi
	 * @param yLo
	 * @param yHi
	 */
	public VLS(int xLo, int xHi, int yLo, int yHi) {
		Point upper = new Point(xHi, yHi);
		Point lower = new Point(xLo, yLo);

		this.upper = upper;
		this.lower = lower;
	}

	/**
	 * Initialize a VLS with endpoints.
	 * 
	 * @param upper
	 * @param lower
	 */
	public VLS(Point upper, Point lower) {
		this.upper = upper;
		this.lower = lower;
	}

	/**
	 * Get the x-coordinate of the segment.
	 * 
	 * @return the x-coordinate of the segment
	 */
	public int getX() {
		return upper.getX();
	}

	/**
	 * Compare two segments.
	 * 
	 * @param that
	 * @return less than 0 if this segment is to the left of that segment.
	 */
	@Override
	public int compareTo(VLS that) {
		if (this.getX() <= that.getX()) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Return a String representation of this VLS.
	 */
	public String toString() {
		// TODO
		return String.format("(%d, %d)-(%d, %d)", lower.getX(), lower.getY(), upper.getX(), upper.getY());
	}

}
