package ar.com.broker.ws.cotizador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response from Cotizador
 * 
 * @author martinvacas
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotizadorServiceResponse {

	private String ticker;
	private Float value;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CotizadorServiceResponse [ticker=" + ticker + ", value=" + value + "]";
	}
	
}
