package models;

import java.math.BigDecimal;

public class FirstClassSeating extends Seating {
	
	private BigDecimal price = new BigDecimal(200.00);

	public FirstClassSeating(String section, String row, int seatNum, BigDecimal price) {
		super(section, row, seatNum);
		this.price = price;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
}
