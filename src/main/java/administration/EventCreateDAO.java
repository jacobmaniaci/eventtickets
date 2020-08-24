package administration;

import java.util.List;

public interface EventCreateDAO {
	
	public String createEvent(String eventName);
	public void destroyEvent(String eventName);
	public int nextTicketId();
	List<EventCreate> showSeatingOptions(String section, String row, String event);

}
