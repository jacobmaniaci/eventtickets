package administration;

public interface EventCreateDAO {
	
	public String createEvent(String eventName);
	public void destroyEvent(String eventName);
	public int nextTicketId();
	String[] showSeatingOptions(String section, String row, String event);

}
