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
		events.sort(null);
	}

	/**
	 * Add an event to the list.
	 * 
	 * @param e
	 */
	public void addEvent(Event e) {
		events.add(e);
	}
	
	private void quickSort(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        Event pivot = events.get(lowerIndex+(higherIndex-lowerIndex)/2);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (events.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (events.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        Event temp = events.get(i);
        events.set(i, events.get(j));
        events.set(j, temp);
    }

	/**
	 * Returns the next event to be processed.
	 * 
	 * @return the next event in the series
	 */
	public Event getNextEvent() {
		if (currentEventIndex < events.size()) {
			return events.get(currentEventIndex++);
		} else {
			return null;
		}
	}
}
