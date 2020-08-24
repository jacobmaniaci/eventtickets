package userinterface;

import java.math.BigDecimal;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import models.ClubSeating;
import models.FirstClassSeating;
import models.GeneralAdmissionSeating;
import models.Menu;
import models.MidVenueSeating;
import models.Seating;
import models.TicketInventory;
import models.jdbc.JDBCEventDAO;

public class UserInterface {

	private Menu menu;
	TicketInventory ticketInv = new TicketInventory();

	// find a way to replace these menus by creating tables in sql 
	// to hold this information
	private static final String MAIN_MENU_MAIN = "Go to events page";
	private static final String MAIN_MENU_ADMIN = "Go to Administration";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_MAIN, MAIN_MENU_ADMIN };
	private static final String[] CHOOSE_EVENT = { "Fight Night: Java vs C#", "Jamming With Javascript",
			"TECH Talks: Journey Into Databases", "Back" };
	private static final String[] CHOOSE_SECTION = { "Club", "First Class", "Mid Venue", "General Admission", "Back" };
	private static final String[] CHOOSE_ROW_FIRST_CLASS = { "A", "B", "C", "Back" };
	private static final String[] CHOOSE_ROW_MID_VENUE = { "A", "B", "C", "D", "E", "Back" };
	private JDBCEventDAO eventDAO;
	// testing github pushes
	// constructor
	public UserInterface(Menu menu) {
		this.menu = menu;
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tickets");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		eventDAO = new JDBCEventDAO(dataSource);
	}

	// the run method initiates the menu from which the user will
	// begin the process of purchasing tickets
	private void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_MAIN)) {
				displayMainMenuOptions();
			} else if (choice.equals(MAIN_MENU_ADMIN)) {
				processAdminMenuOptions();
			} else if (choice.equals("Exit")) {

				System.exit(0);
			}
		}
	}

	// the following method is yet to be done
	private void processAdminMenuOptions() {

		Scanner adminUser = new Scanner(System.in);
		System.out.println("Press 1 to create an event. Press 2 to destroy an event. ");
		String adminUserChoice = adminUser.nextLine();
		if (adminUserChoice.equals("1")) {
			System.out.println("What is the name of your event? ");
			adminUserChoice = adminUser.nextLine();
			eventDAO.createEvent(adminUserChoice);
			
		}
	
	}

	// the following method will allow user to choose the event the want to attend
	// or return to the main menu
	private void displayMainMenuOptions() {

		// user chooses event
		String eventMenuOption = "";

		while (!eventMenuOption.equals("Back")) {
			eventMenuOption = (String) menu.getChoiceFromOptions(CHOOSE_EVENT);

			System.out.println("Your going to " + eventMenuOption);
			chooseSeat(eventMenuOption);

		}
	}

	// the following method will begin the process of choosing a seat
	// edits to be made are accessing an sql table with available 
	// seats according to the section that a user wants and the row
	// the user wants
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
	// need to link the method to an sql table so that it only displays
	// available seating and take out the guess work that a certain seat
	// is available
	// this will make the UX a lot more seamless and friendly
	public void chooseSeat(String chooseSectionPass, String chooseR, String eventPassPass) {
		String chooseSt = "";
		String chooseSectionPassPass = chooseSectionPass;
		String chooseRow = chooseR;
		String eventPass = eventPassPass;
		
		String[] chooseSeat = eventDAO.showSeatingOptions(chooseSectionPass, chooseR, eventPassPass);
		
		if (chooseSectionPass.equals("Club") || chooseSectionPass.equals("First Class") || chooseSectionPass.equals("Mid Venue")) {
			chooseSt = (String) menu.getChoiceFromOptions(chooseSeat);
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

	
	// main method initiates the UI program
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		UserInterface ui = new UserInterface(menu);
		ui.run();
	}

}
