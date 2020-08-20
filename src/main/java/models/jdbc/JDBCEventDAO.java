package models.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import models.Event;
import models.Seating;

public class JDBCEventDAO implements Event {
	
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setUrl("jdbc:postgresql://localhost:5432/tickets");
	dataSource.setUsername("postgres");
	dataSource.setPassword("postgres1");
	
	dao = new JDBCDepartmentDAO(dataSource);
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCEventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void bookTicket(String section, String row, int seat) {
		
	}

	@Override
	public int numberOfPeopleAttending() {
		return 0;
	}

	@Override
	public void printAllTickets() {
		
	}

	@Override
	public List<Seating> displaySeating() {
		return null;
	}

}
