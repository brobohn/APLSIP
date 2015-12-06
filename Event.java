/**
 * An element in the event list.
 *
 */
public class Event implements Comparable<Event> {
	//Fields
	Point p1;
	Point p2;
	LineSegment segment;
	EventType type;
	
	
	
	public Event(Point p1, Point p2, LineSegment segment) {
		this.p1 = p1;
		this.p2 = p2;
		this.segment = segment;
		
		if (p1 != null && p2 == null) {
			this.type = EventType.UPPER;
		} else if (p1 != null && p2 != null) {
			this.type = EventType.HORIZ;
		} else if (p1 == null && p2 != null) {
			this.type = EventType.LOWER;
		}
	}
	
	public int getY() {
		if (type == EventType.LOWER) {
			return p2.getY();
		} else {
			return p1.getY();
		}
	}
	
	public int compareTo(Event that) {
		// implement better logic to differentiate between event types
		if (this.type == EventType.UPPER) {
			if (this.getY() <= that.getY()) {
				return -1;
			} else {
				return 1;
			}
		}
		else if (this.type == EventType.HORIZ) {
			if (this.getY() < that.getY()) {
				return -1;
			} 
			else if (this.getY() > that.getY()) {
				return 1;
			}
			else {
				// Y-values are equal.
				// We need to check if that is an UPPER. If so, it comes before
				// this and thus we need to return 1 to denote that this comes
				// later in the list than that.
				if (that.type == EventType.UPPER) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		else {
			// Event Type is LOWER. It comes after other events with equivalent
			// y-values.
			if (this.getY() < that.getY()) { //note the change to strict ineq.
				return -1;
			} else {
				return 1;
			}
		}
	}
}
