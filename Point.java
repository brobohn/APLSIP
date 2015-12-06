/**
 * A two-dimensional point.
 */
public class Point {
	// Fields
	private int x;
	private int y;

	/**
	 * Initialize a Point with coordinates.
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x-value.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y-value.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
}
