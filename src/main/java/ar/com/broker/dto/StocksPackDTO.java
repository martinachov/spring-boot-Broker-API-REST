package ar.com.broker.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author martinvacas
 *
 */
public class StocksPackDTO implements Serializable {
	
	private static final long serialVersionUID = 1518333454709564499L;
	
	private List<StocksBuyInfoDTO> listStocks;

	public List<StocksBuyInfoDTO> getListStocks() {
		return listStocks;
	}

	public void setListStocks(List<StocksBuyInfoDTO> listStocks) {
		this.listStocks = listStocks;
	}
	
}
