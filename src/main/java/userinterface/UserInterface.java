package userinterface;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import administration.EventCreate;
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
	private JDBCEventDAO eventDAO;

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

		System.out.println("Enter the administrator password: ");
		String password = adminUser.nextLine();

		if (password.equals("yourmother")) {

			System.out.println("Press 1 to create an event. Press 2 to destroy an event. ");
			String adminUserChoice = adminUser.nextLine();
			if (adminUserChoice.equals("1")) {
				System.out.println("What is the name of your event? ");
				adminUserChoice = adminUser.nextLine();
				eventDAO.createEvent(adminUserChoice);

			} else if (adminUserChoice.equals("2")) {
				System.out.println("What event do you want to kill? ");
				adminUserChoice = adminUser.nextLine();
				eventDAO.destroyEvent(adminUserChoice);
			}

		} else {
			System.out.println("Invalid Password");
			run();
		}
	}

	// the following method will allow user to choose the event the want to attend
	// or return to the main menu
	private void displayMainMenuOptions() {

		// user chooses event
		System.out.println("\nWhich event would you like to attend? ");
		String eventMenuOption = "";
		String[] eventsOptions = eventDAO.getAllEvents();
		while (!eventMenuOption.equals("Back")) {
			eventMenuOption = (String) menu.getChoiceFromOptions(eventsOptions);
			
			System.out.println("\nYour going to " + eventMenuOption);
			
			while(!eventMenuOption.equals("Back")) {
				chooseSection(eventMenuOption);
			}
			run();

		}
	}

	// the following method will begin the process of choosing a seat
	// edits to be made are accessing an sql table with available
	// seats according to the section that a user wants and the row
	// the user wants
	public void chooseSection(String eventMenuOption) {
		System.out.println("Choose your seating section.");
		// user chooses section
		String chooseSect = "";
		String eventPass = eventMenuOption;

		String[] sectionsOptions = eventDAO.getAllSections(eventPass);
		chooseSect = (String) menu.getChoiceFromOptions(sectionsOptions);
		while (!chooseSect.equals("Back")) {
			
			chooseRow(chooseSect, eventPass);
		}
		displayMainMenuOptions();

	}

	// user chooses row

	public void chooseRow(String chooseSection, String eventPass) {
		System.out.println("\nChoose your section row.");
		String chooseR = "";
		String chooseSectionPass = chooseSection;
		String eventPassPass = eventPass;
		
		String[] rowOptions = eventDAO.getAllRows(chooseSection, eventPass);
		
		chooseR = (String) menu.getChoiceFromOptions(rowOptions);
		
		while (!chooseR.equals("Back")) {
			
				chooseSeat(chooseSectionPass, chooseR, eventPassPass);
			
			}

		
		displayMainMenuOptions();
	}

	// user chooses seat number
	// need to link the method to an sql table so that it only displays
	// available seating and take out the guess work that a certain seat
	// is available
	// this will make the UX a lot more seamless and friendly
	public void chooseSeat(String chooseSectionPass, String chooseR, String eventPassPass) {
		System.out.println("Enter the seat number you want to reserve.");
		
		List<EventCreate> chooseSeat = eventDAO.showSeatingOptions(chooseSectionPass, chooseR, eventPassPass);

		System.out.printf("\n %-40s %-15s \t%-15s \t%-15s \t%-15s \n", "Event Name", "Section", "Row", "SeatNumber",
				"Price");
		for (EventCreate ticket : chooseSeat) {
			System.out.printf("\n%-40s %-15s \t%-15s \t%-15s \t%-15s", ticket.getEventName(), ticket.getSection(),
					ticket.getRow(), ticket.getSeatNumber(), "$" + ticket.getPrice());
		}

		Scanner userInput = new Scanner(System.in);
		System.out.println("\n\nEnter the seat you wish to book: (Enter 0 to cancel) ");
		int seatChoice = Integer.parseInt(userInput.nextLine());
		BigDecimal priceTag = eventDAO.getTicketPrice(chooseSectionPass);
		
		if (seatChoice != 0) {
		eventDAO.purchaseTicket(chooseSectionPass, chooseR, eventPassPass, seatChoice, priceTag);
		} else {
			displayMainMenuOptions();
		}
		System.out.println("\nYour Ticket is: ");
		System.out.println(eventPassPass + "\t\t\t      Ticket Value: $" + priceTag);
		System.out.println("Section: " + chooseSectionPass + " \t--\t Row: " + chooseR + " \t--\t Seat: " + seatChoice);

		System.out.println("\nWould you like to purchase another ticket in the " + chooseSectionPass
				+ " section?  Choose 'Back' to exit booking.");
	}

	

	// user finishes selection

	// main method initiates the UI program
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		UserInterface ui = new UserInterface(menu);
		ui.run();
	}

}
