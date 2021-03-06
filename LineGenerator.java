import java.util.Random;

/**
 * Generates a specified number of line segments.
 * 
 * These segments will each be of length 25, and no endpoint shall have an X- or
 * Y-coordinate less than 0 or greater than 1,000,000.
 *
 */
public class LineGenerator {
	final int LENGTH = 100;
	final int BOUNDS = 100000;
	final int MAX = BOUNDS-LENGTH;
	
	public LineGenerator() 
	{
		// empty
	}
	
	/**
	 * Generates and returns an array of randomly generated line segments.
	 * @param n	number of line segments
	 * @return an array of randomly generated line segments
	 */
	public LineSegment[] generateLines(int n) 
	{
		LineSegment[] line_segments = new LineSegment[2*n];
		
		Random rand_cord = new Random();	// for generating coordinates
		
		int x, y; 
		
		// generate the segments 
		for (int i = 0; i < 2*n; i++)
		{
			x 	 = rand_cord.nextInt(MAX + 1);  	// between 0 and MAX
			y 	 = rand_cord.nextInt(MAX + 1);  	// between 0 and MAX
			
			if (i % 2 == 0)	// even = vertical segment
			{
				// randomly generate the Y-coordinate of its bottom endpoint
				VLS v_seg = new VLS(x, x, y, y + 25);
				line_segments[i] = v_seg;
			}
			else	// odd = horizontal segment
			{
				// randomly generate the X-coordinate of its left endpoint
				HLS h_seg = new HLS(x, x + 25, y, y);
				line_segments[i] = h_seg; 
			}
		}
		return line_segments;
	}
	
}
