/**
 * A Horizontal Line Segment.
 */
public class HLS extends LineSegment {
	//Fields
	public Point left;
	public Point right;
	
	public HLS(int xLe, int xRi, int yLe, int yRi) {
		Point left = new Point(xLe, yRi);
		Point right = new Point(xLe, yRi);

		this.left = left;
		this.right = right;
	}
	
	
	/**
	 * Return a String representation of this HLS.
	 */
	public String toString() {
		//TODO
		return null;
	}
}
