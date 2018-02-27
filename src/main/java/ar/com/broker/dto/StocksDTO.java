package ar.com.broker.dto;

import java.io.Serializable;

/**
 * 
 * @author martinvacas
 *
 */
public class StocksDTO implements Serializable {

	private static final long serialVersionUID = 1427604660258709006L;
	
	private String name;
	private String ticker;
	private Float price;
	
	public StocksDTO(){
		
	}
	
	public StocksDTO(String ticker, String name, Float price) {
		this.ticker = ticker;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
}
