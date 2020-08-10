package models;

import java.math.BigDecimal;

public abstract class Seating {
	
	private String section;
	private String row;
	private int seatNum;
	private BigDecimal price;
	
	public Seating (String section, String row, int seatNum) {
		this.section = section;
		this.row = row;
		this.seatNum = seatNum;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Section: " + getSection() + " -- Row: " + getRow() + " -- Seat: " + getSeatNum();
	}
	

}
