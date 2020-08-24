package models;

import java.util.List;

public class TEArena implements Event {
	
	
	
	
	
	// Runs SQL statement to check seat availability
	public boolean checkSeatAvailability() {
		
		return false;
	}

	// Once ticket is confirmed as available it can be purchased
	// then it will be added to the purchased table and deleted
	// from the available table in sql
	public void bookTicket(String section, String row, int seat) {
		
		
	}

	// runs a simple query to show the number of people attending when
	// a ticket is purchased
	public int numberOfPeopleAttending() {
		
		return 0;
	}

	// runs a query to show all the tickets purchased by a customer
	// on a particular visit
	public void printAllTickets() {
		
		
	}

	@Override
	public List<Seating> displaySeating() {
		// TODO Auto-generated method stub
		return null;
	}


}
