package models;

import java.math.BigDecimal;

public class GeneralAdmissionSeating extends Seating {
	
	private BigDecimal price = new BigDecimal(50.00);

	public GeneralAdmissionSeating(String section, String row, int seatNum, BigDecimal price) {
		super(section, row, seatNum);
		this.price = price;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}

}
