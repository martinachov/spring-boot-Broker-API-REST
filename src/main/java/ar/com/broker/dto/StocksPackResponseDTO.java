package ar.com.broker.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author martinvacas
 *
 */
public class StocksPackResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 999233454709564499L;

	private Map<String, Integer> stocksBuyed;

	public Map<String, Integer> getStocksBuyed() {
		return stocksBuyed;
	}

	public void setStocksBuyed(Map<String, Integer> stocksBuyed) {
		this.stocksBuyed = stocksBuyed;
	}

}
