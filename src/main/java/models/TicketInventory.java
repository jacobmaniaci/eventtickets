package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketInventory {

	String[] eventName = { "Fight Night: Java vs C#", "Jamming With Javascript", "TECH Talks: Journey Into Databases" };
	
	public String[] makeEvents () {
		String[] eventName = { "Fight Night: Java vs C#", "Jamming With Javascript", "TECH Talks: Journey Into Databases" };
		return eventName;
	}
	
	// loop to create an array of seats
	
	public String [] createSeatsClub() {
		String [] clubSeats = new String[40];
		for (int i = 0; i < 40; i++) {
			clubSeats[i] = "A" + (i + 1);
		}
		return clubSeats;
	}
	
	

}
