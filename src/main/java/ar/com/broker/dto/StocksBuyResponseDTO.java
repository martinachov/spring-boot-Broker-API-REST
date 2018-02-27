package ar.com.broker.dto;

import java.io.Serializable;

/**
 * 
 * @author martinvacas
 *
 */
public class StocksBuyResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 3378523454798765488L;

	private String ticker;
	private Float cant;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Float getCant() {
		return cant;
	}

	public void setCant(Float cant) {
		this.cant = cant;
	}
}
