package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketInventory {

	String[] eventName = { "Fight Night: Java vs C#", "Jamming With Javascript", "TECH Talks: Journey Into Databases" };

	public String[] makeEvents() {
		String[] eventName = { "Fight Night: Java vs C#", "Jamming With Javascript",
				"TECH Talks: Journey Into Databases" };
		return eventName;
	}

	// loop to search database for available tickets
	// remove seat if purchased
	// purchased seats go into separate table
	
	
}	
