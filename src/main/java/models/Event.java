package models;

import java.util.List;

public interface Event {
	
	public List<Seating> displaySeating();
	public void bookTicket(String section, String row, int seat);
	public int numberOfPeopleAttending();
	public void printAllTickets(); 
	

}
