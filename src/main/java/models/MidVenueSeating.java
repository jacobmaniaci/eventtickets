package models;

import java.math.BigDecimal;

public class MidVenueSeating extends Seating {
	
	private BigDecimal price = new BigDecimal(100.00);

	public MidVenueSeating(String section, String row, int seatNum, BigDecimal price) {
		super(section, row, seatNum);
		this.price = price;
		
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	

}
