/**
 * An element in the event list.
 *
 */
public class Event {
	//Fields
	Point p1;
	Point p2;
	LineSegment segment;
	EventType type;
	
	// TODO create constructors for each type of event
	
	
	public Event(Point p1, Point p2, LineSegment segment) {
		this.p1 = p1;
		this.p2 = p2;
		this.segment = segment;
		
		// TODO this could be more efficient, laid out this way for clarity
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
		if (this.getY() <= that.getY()) {
			return -1;
		} else {
			return 1;
		}
	}
}
