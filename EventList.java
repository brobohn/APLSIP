import java.util.ArrayList;

/**
 * List of points sorted in descending order by y-values.
 */
public class EventList {
	// Fields
	public ArrayList<Event> events;
	private int currentEventIndex;

	public EventList() {
		events = new ArrayList<Event>();
		currentEventIndex = 0;
	}

	/**
	 * Use quicksort to order the list in descending order. Always call this
	 * function after all events have been added.
	 */
	public void sort() {
		// TODO
	}

	/**
	 * Add an event to the list.
	 * 
	 * @param e
	 */
	public void addEvent(Event e) {
		events.add(e);
	}

	/**
	 * Returns the next event to be processed.
	 * 
	 * @return the next event in the series
	 */
	public Event getNextEvent() {
		return events.get(currentEventIndex++);
	}
}
