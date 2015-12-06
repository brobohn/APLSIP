import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



/**
 * Runs both the brute-force algorithm and the optimized algorithm.
 *
 */
public class Algorithm {
	
	public RandomAccessFile output_file;

	public Algorithm(RandomAccessFile output_file) {
		this.output_file = output_file;
	}

	/**
	 * Runs a brute force algorithm on an array of line segments. This function
	 * should write to a file at the time each intersection is detected.
	 * 
	 * @param lines
	 *            an array of both vertical and horizontal line segments
	 */
	public void bruteForceAlgorithm(LineSegment[] lines) throws IOException {
		int num_lines = lines.length;
		this.output_file.writeBytes("Running the brute force algorithm. \n");
		int num_intersections = 0;
		//Looping through the lines array.
		
		for (int i = 0; i < num_lines; i++) {
			LineSegment line = lines[i];
			boolean horiz = line instanceof HLS; 
			// Will only be comparing with lines of perpendicular direction.
			// Looping through again.
			for (int j = i; j < num_lines; j++) {
				LineSegment other_line = lines[j];
				
				if (horiz && other_line instanceof VLS) {
					// current line is horizontal and other_line is vertical.
					// Check for intersection.
					HLS horiz_line = (HLS) line;
					VLS vert_line = (VLS) other_line;
					int horz_y = horiz_line.left.getY();
					int horz_left = horiz_line.left.getX();
					int horz_right = horiz_line.right.getX();
					int vert_low = vert_line.lower.getY();
					int vert_high = vert_line.upper.getY();
					int vert_x = vert_line.upper.getX();
					
					// checking to see if the horizontal bar is at least in 
					// between the vertical bar's endpoints, vertically.
					if (vert_low <= horz_y && horz_y <= vert_high) {
						// If so, we need to check if they actually intersect.
						if (horz_left <= vert_x && vert_x <= horz_right) {
							// They intersect! Now we report that.
							num_intersections++;
							String line_index = Integer.toString(i);
							String other_line_index = Integer.toString(j);
							// point of intersection = POI
							String POI = "(" + Integer.toString(vert_x) + ", " +
										Integer.toString(horz_y) + ")";
							this.output_file.writeBytes("Intersection at "
									+ POI + " between the segment "
									+ line_index + " and the segment " + 
									other_line_index + ". \n");
						}
					}
					// If no intersection, don't do anything.
				}
				else if (!horiz && other_line instanceof HLS) {
					// In this case, the current line is vertical and the other
					// is horizontal.
					// Check for intersection.
					HLS horiz_line = (HLS) other_line;
					VLS vert_line = (VLS) line;
					int horz_y = horiz_line.left.getY();
					int horz_left = horiz_line.left.getX();
					int horz_right = horiz_line.right.getX();
					int vert_low = vert_line.lower.getY();
					int vert_high = vert_line.upper.getY();
					int vert_x = vert_line.upper.getX();
					
					// checking to see if the vertical bar is at least in 
					// between the horizontal bar's endpoints, horizontally.
					if (vert_low <= horz_y && horz_y <= vert_high) {
						// If so, we need to check if they actually intersect.
						if (horz_left <= vert_x && vert_x <= horz_right) {
							// They intersect! Now we report that.
							num_intersections++;
							String line_index = Integer.toString(i);
							String other_line_index = Integer.toString(j);
							// point of intersection = POI
							String POI = "(" + Integer.toString(vert_x) + ", " +
										Integer.toString(horz_y) + ")";
							this.output_file.writeBytes("Intersection at "
									+ POI + " between the segment "
									+ line_index + " and the segment " + 
									other_line_index + ". \n");
						}
					}
					// If no intersection, don't do anything.
				}
				else { 
					// In this case, the line segments at index i and j of the
					// array are of the same direction and thus will not be
					// checked for intersection.
				}
			}
			
		}
		
		if (num_intersections == 0) {
			this.output_file.writeBytes("No intersections. \n\n");
		}
		this.output_file.close();
	}

	/**
	 * Runs the optimized algorithm on an array of line segments. The Event List
	 * and the Sweep Line are both initialized but empty when passed to this
	 * function. This function should write to a file at the time each
	 * intersection is detected.
	 * 
	 * @param lines
	 *            an array of both vertical and horizontal line segments
	 * @param E
	 *            the EventList
	 * @param sweeper
	 *            the SweepLine
	 */
	public void algorithm(LineSegment[] lines, EventList E, SweepLine sweeper) throws IOException {
		// insert lines into E
		for (LineSegment line : lines) {
			Event e = null;
			
			if (line instanceof HLS) {	//HLS
				HLS hls = (HLS) line;
				e = new Event(hls.left, hls.right, hls);
				E.addEvent(e);
			} else {					// VLS
				VLS vls = (VLS) line;
				
				//add upper
				e = new Event(vls.upper, null, vls);
				E.addEvent(e);
				
				//add lower
				e = new Event(null, vls.lower, vls);
				E.addEvent(e);
			}
			
		}
		
		// sort E
		E.sort();
		
		Event event;
		
		// for each event in E
		while ((event = E.getNextEvent()) != null) {
			
			if (event.type == EventType.UPPER) {
				// Upper endpoint: insert VLS into tree
				sweeper.addSegment(event.segment);
						
			} else if (event.type == EventType.HORIZ) {
				// Horizontal line: check for intersections
				HLS hls = (HLS) event.segment;
				
				ArrayList<VLS> vlss = sweeper.printRange(event);
				
				//print vlss
				for (VLS vls : vlss) {
					int x = vls.getX();
					int y = hls.getY();
					
					String POI = String.format("(%d, %d)", x, y);
					
					this.output_file.writeBytes("Intersection at " + POI + ". \n");
				}
				
			} else { // LOWER
				// Lower endpoint: remove VLS from tree
				sweeper.removeSegment(event.segment);
			}
		}	
	}

}
