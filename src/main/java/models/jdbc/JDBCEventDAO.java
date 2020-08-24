package models.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import administration.EventCreate;
import administration.EventCreateDAO;

public class JDBCEventDAO implements EventCreateDAO {

private JdbcTemplate jdbcTemplate;
	
	public JDBCEventDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public String createEvent(String eventName) {
		//String newEvent = eventName;
		String[] sections = {"Club", "First Class", "Mid Venue", "General Admission"};
		String[] rows = {"A", "B", "C", "D", "E"};
		String section = "";
		String row = "";
		int seatNumber = 0;
		BigDecimal price = new BigDecimal(0.00);
		
		
//		EventCreate newEvent = new EventCreate();
		
		for (int i = 0; i < sections.length; i++) {
			if (sections[i].equals("Club")) {
				section = sections[i];
				row = "A";
				for (int k = 1; k <= 40; k++) {
					seatNumber = k;
					price = new BigDecimal(300.00);
					
//					newEvent.setTicketId(nextTicketId());
//					newEvent.setEventName(eventName);
//					newEvent.setSection(section);
//					newEvent.setRow(row);
//					newEvent.setSeatNumber(seatNumber);
//					newEvent.setPrice(price);
					
					String makeEvent = "INSERT INTO available_tickets "
							+ "VALUES(?, ?, ?, ?, ?, ?)";
					jdbcTemplate.update(makeEvent, nextTicketId(), eventName, section, row, seatNumber, price);
					
				}
			} else if (sections[i].equals("First Class")) {
				section = sections[i];
				for (int j = 0; j < 3; j++) {
					row = rows[j];
					for (int k = 1; k <= 40; k++) {
						seatNumber = k;
						price = new BigDecimal(200.00);
						
						String makeEvent = "INSERT INTO available_tickets "
								+ "VALUES(?, ?, ?, ?, ?, ?)";
						jdbcTemplate.update(makeEvent, nextTicketId(), eventName, section, row, seatNumber, price);
						
					}
				}
			} else if (sections[i].equals("Mid Venue")) {
				section = sections[i];
				for (int j = 0; j < rows.length; j++) {
					row = rows[j];
					for (int k = 1; k <= 40; k++) {
						seatNumber = k;
						price = new BigDecimal(100.00);
						
						String makeEvent = "INSERT INTO available_tickets "
								+ "VALUES(?, ?, ?, ?, ?, ?)";
						jdbcTemplate.update(makeEvent, nextTicketId(), eventName, section, row, seatNumber, price);
						
					}
				}
			} else {
				section = "General Admission";
				row = "NA";
				for (int k = 1; k <= 440; k++) {
					seatNumber = k;
					price = new BigDecimal(50.00);
					
					String makeEvent = "INSERT INTO available_tickets "
							+ "VALUES(?, ?, ?, ?, ?, ?)";
					jdbcTemplate.update(makeEvent, nextTicketId(), eventName, section, row, seatNumber, price);
					
				}
			}
			
			
			
		}
		
		return eventName;
	}

	@Override
	public void destroyEvent(String eventName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int nextTicketId() {
		int nextIdValue = 0;
		SqlRowSet nextId = jdbcTemplate.queryForRowSet("SELECT nextval('available_tickets_ticket_id_seq')");
		if(nextId.next()) {
			nextIdValue =  nextId.getInt(1);
		} else {
			throw new RuntimeException("Error getting the next Id.");
		}
		
		return nextIdValue;
	}
	
	@Override
	public String[] showSeatingOptions(String section, String row, String event) {
		String makeEvent = "SELECT * FROM available_tickets " + 
				 "WHERE event_name = ? AND section = ? AND row = ?";
		SqlRowSet sql = jdbcTemplate.queryForRowSet(makeEvent, event, section, row);
				
		List<EventCreate> ticketAv = new ArrayList<>();
		while(sql.next()) {
			EventCreate ticket = new EventCreate();
			
			ticket.setTicketId(sql.getInt("ticket_id"));
			ticket.setEventName(sql.getString("event_name"));
			ticket.setSection(sql.getString("section"));
			ticket.setRow(sql.getString("row"));
			ticket.setSeatNumber(sql.getInt("seat_number"));
			ticket.setPrice(sql.getBigDecimal("price"));
			
			ticketAv.add(ticket);
		}
		
		String[] newArray = new String[ticketAv.size() + 1];
		for (int i = 0; i < ticketAv.size(); i++ ) {
			newArray[i] = ticketAv.get(i).getEventName().toString() + " " + 
					ticketAv.get(i).getSection().toString() + " " +
					ticketAv.get(i).getRow().toString() + " " + 
					ticketAv.get(i).getSeatNumber() + " " +
					ticketAv.get(i).getPrice().toString();
		}
		
		return newArray;
		
	}
	
	

}
