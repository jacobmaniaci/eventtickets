package models;

public interface Event {
	
	
	public boolean checkSeatAvailability();
	public void bookTicket(String section, String row, int seat);
	public int numberOfPeopleAttending();
	public void printAllTickets(); 

}
