package models;

import java.math.BigDecimal;

public class ClubSeating extends Seating {
	
	private BigDecimal price = new BigDecimal(300.00);

	public ClubSeating(String section, String row, int seatNum, BigDecimal price) {
		super(section, row, seatNum);
		this.price = price;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	

}
