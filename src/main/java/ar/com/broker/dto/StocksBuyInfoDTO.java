package ar.com.broker.dto;

import java.io.Serializable;

/**
 * 
 * @author martinvacas
 *
 */
public class StocksBuyInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1234333454732564488L;
	
	private String ticker;
	private Float amount;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
}
