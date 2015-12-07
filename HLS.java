/**
 * A Horizontal Line Segment.
 */
public class HLS extends LineSegment {
	//Fields
	public Point left;
	public Point right;
	
	public HLS(int xLe, int xRi, int yLe, int yRi) {
		Point left = new Point(xLe, yLe);
		Point right = new Point(xRi, yRi);

		this.left = left;
		this.right = right;
	}
	
	/**
	 * Get the y-coordinate of the segment.
	 * 
	 * @return the y-coordinate of the segment
	 */
	public int getY() {
		return left.getY();
	}
	
	/**
	 * Return a String representation of this HLS.
	 */
	public String toString() {
		return String.format("(%d, %d)-(%d, %d)", left.getX(), left.getY(),
				right.getX(), right.getY());
	}
}
