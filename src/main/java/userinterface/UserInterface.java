package userinterface;

import java.math.BigDecimal;
import java.util.Scanner;

import models.ClubSeating;
import models.FirstClassSeating;
import models.GeneralAdmissionSeating;
import models.Menu;
import models.MidVenueSeating;
import models.Seating;
import models.TicketInventory;

public class UserInterface {

	private Menu menu;
	TicketInventory ticketInv = new TicketInventory();

	private static final String MAIN_MENU_MAIN = "Go to events page";
	private static final String MAIN_MENU_PURCHASE = "Go to purchasing page";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_MAIN, MAIN_MENU_PURCHASE };
	private static final String[] CHOOSE_EVENT = { "Fight Night: Java vs C#", "Jamming With Javascript",
			"TECH Talks: Journey Into Databases", "Back" };
	private static final String[] CHOOSE_SECTION = { "Club", "First Class", "Mid Venue", "General Admission", "Back" };
	private static final String[] CHOOSE_ROW_FIRST_CLASS = { "A", "B", "C", "Back" };
	private static final String[] CHOOSE_ROW_MID_VENUE = { "A", "B", "C", "D", "E", "Back" };
	private static final String[] CHOOSE_SEAT = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
			"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31","32",
			"33", "34", "35", "36", "37", "38", "39", "40", "Back" };

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

		// user confirms purchase

		// remove tickets from available inventory

		// log the transaction
	}

	private void displayMainMenuOptions() {

		// user chooses event
		String eventMenuOption = "";

		while (!eventMenuOption.equals("Back")) {
			eventMenuOption = (String) menu.getChoiceFromOptions(CHOOSE_EVENT);

			System.out.println("Your going to " + eventMenuOption);
			chooseSeat(eventMenuOption);

		}
	}

	
	public void chooseSeat(String eventMenuOption) {
		// user chooses section
		String chooseSect = "";
		String eventPass = eventMenuOption;

		while (!chooseSect.equals("Back")) {
			chooseSect = (String) menu.getChoiceFromOptions(CHOOSE_SECTION);
			System.out.println("You are sitting in " + chooseSect);
			chooseRow(chooseSect, eventPass);
		} 
		displayMainMenuOptions();
		
	}

	// user chooses row
	
	public void chooseRow(String chooseSection, String eventPass) {
		String chooseR = "";
		String chooseSectionPass = chooseSection;
		String eventPassPass = eventPass;
		
		while (!chooseR.equals("Back")) {
			if (chooseSection.equals("Club")) {
				chooseR = "A";
				System.out.println("You will be in row A!");
				chooseSeat(chooseSectionPass, chooseR, eventPassPass);
			} else if (chooseSection.equals("First Class")) {
				chooseR = (String) menu.getChoiceFromOptions(CHOOSE_ROW_FIRST_CLASS);
				System.out.println("You will be in row " + chooseR);
				chooseSeat(chooseSectionPass, chooseR, eventPassPass);
			} else if (chooseSection.equals("Mid Venue")) {
				chooseR = (String) menu.getChoiceFromOptions(CHOOSE_ROW_MID_VENUE);
				System.out.println("You will be in row " + chooseR);
				chooseSeat(chooseSectionPass, chooseR, eventPassPass);
			} else if (chooseSection.equals("General Admission")) {
				chooseR = "None";
				chooseSeat(chooseSectionPass, chooseR, eventPassPass);
				System.out.println("General Admission seating is first come first serve.");
			} else{
				System.out.println("Invalid selection, please try again.");
			}

		}
		displayMainMenuOptions();
	}

	// user chooses seat number
	
	public void chooseSeat(String chooseSectionPass, String chooseR, String eventPassPass) {
		String chooseSt = "";
		String chooseSectionPassPass = chooseSectionPass;
		String chooseRow = chooseR;
		String eventPass = eventPassPass;
		if (chooseSectionPass.equals("Club") || chooseSectionPass.equals("First Class") || chooseSectionPass.equals("Mid Venue")) {
			chooseSt = (String) menu.getChoiceFromOptions(CHOOSE_SEAT);
			System.out.println("Your seat number is " + chooseSt);
			createTicket(chooseSectionPassPass, chooseRow, chooseSt, eventPass);
		} else if (chooseSectionPass.equals("General Admission")) {
			chooseSt = "0";
			System.out.println("Seats are first come serve in General Admission.");
			createTicket(chooseSectionPassPass, chooseRow, chooseSt, eventPass);
		} else {
			System.out.println("Invalid Selection.");
		}
		displayMainMenuOptions();
	}

	
	// create ticket object based on selection
	private void createTicket(String chooseSectionPassPass, String chooseRow, String chooseSt, String eventPass) {
		System.out.println("\n\n\n");
		System.out.println("Your ticket for \'" + eventPass + "\' is: ");
		int makeSeatInt = Integer.parseInt(chooseSt);
		if (chooseSectionPassPass.equals("Club")) {
			Seating userClubTicket = new ClubSeating(chooseSectionPassPass, chooseRow, makeSeatInt, new BigDecimal(300.00));
			System.out.println(userClubTicket.toString());
			System.out.println("Your price for this ticket is: $" + userClubTicket.getPrice());
		}
		if (chooseSectionPassPass.equals("First Class")) {
			Seating userFirstClassTicket = new FirstClassSeating(chooseSectionPassPass, chooseRow, makeSeatInt, new BigDecimal(200.00));
			System.out.println(userFirstClassTicket.toString());
			System.out.println("Your price for this ticket is: $" + userFirstClassTicket.getPrice());
		}
		if (chooseSectionPassPass.equals("Mid Venue")) {
			Seating userMidVenueTicket = new MidVenueSeating(chooseSectionPassPass, chooseRow, makeSeatInt, new BigDecimal(100.00));
			System.out.println(userMidVenueTicket.toString());
			System.out.println("Your price for this ticket is: $" + userMidVenueTicket.getPrice());
		}
		if (chooseSectionPassPass.equals("General Admission")) {
			Seating userGeneralAdmissionTicket = new GeneralAdmissionSeating(chooseSectionPassPass, chooseRow, makeSeatInt, new BigDecimal(50.00));
			System.out.println(userGeneralAdmissionTicket.toString());
			System.out.println("Your price for this ticket is: $" + userGeneralAdmissionTicket.getPrice());
		}
		System.out.println("\n\n\n");
		
	}

	
	
	// check to see if seat is available

	// check if user wants more tickets

	// user finishes selection

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		UserInterface ui = new UserInterface(menu);
		ui.run();
	}

}
