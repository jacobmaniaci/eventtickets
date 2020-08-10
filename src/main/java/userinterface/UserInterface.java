package userinterface;

import java.util.Scanner;

import models.Menu;
import models.Seating;
import models.TicketInventory;

public class UserInterface {
	
	private Menu menu;
	TicketInventory ticketInv = new TicketInventory();
	
	private static final String MAIN_MENU_MAIN = "Go to events page";
	private static final String MAIN_MENU_PURCHASE = "Go to purchasing page";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_MAIN, MAIN_MENU_PURCHASE};
	private static final String[] CHOOSE_EVENT = { "Fight Night: Java vs C#", "Jamming With Javascript", "TECH Talks: Journey Into Databases" };
	//private static final String[] CHOOSE_SEATS = {"Club", "First Class", "Mid Venue", "General Admission");
	
	
	public UserInterface(Menu menu) {
		this.menu = menu;
	}

	private void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_MAIN)) {
				displayMainMenuOptions();
			} else if (choice.equals(MAIN_MENU_PURCHASE)) {
				processPurchaseMenuOptions();
			} else if (choice.equals("Exit")) {
				
				System.exit(0);
			}
		}
	}

			
	private void processPurchaseMenuOptions() {
		// TODO Auto-generated method stub
		// user confirms purchase

	// remove tickets from available inventory

	// log the transaction
	}

	

	// check to see if seat is available

	// check if user wants more tickets

	// user finishes selection

	

	

	private void displayMainMenuOptions() {
		
	// user chooses event
		String eventMenuOption = "";
		
		while (!eventMenuOption.equals("Back")) {
			eventMenuOption = (String) menu.getChoiceFromOptions(CHOOSE_EVENT);
		}
	// user chooses section

	// user chooses row

	// user chooses seat number
	}




	

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		UserInterface ui = new UserInterface(menu);
		ui.run();
	}
}
