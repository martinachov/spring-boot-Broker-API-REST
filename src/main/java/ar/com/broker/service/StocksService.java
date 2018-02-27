package ar.com.broker.service;

import ar.com.broker.dto.StocksDTO;
import ar.com.broker.dto.StocksPackDTO;
import ar.com.broker.dto.StocksPackResponseDTO;
import ar.com.broker.exception.EntityNotFoundException;
import ar.com.broker.exception.StocksCuantityException;

/**
 * Interface StocksService
 * 
 * @author martinvacas
 *
 */
public interface StocksService {
	
	/**
	 * Return information about Stocks
	 * 
	 * @param ticker
	 * @return
	 * @throws EntityNotFoundException 
	 */
	StocksDTO getStocksInfo(String ticker) throws EntityNotFoundException;
	
	/**
	 * Return result operation
	 * 
	 * @param listStocks
	 * @return
	 * @throws EntityNotFoundException
	 * @throws StocksCuantityException 
	 */
	StocksPackResponseDTO buyStoksPack(StocksPackDTO listStocks) throws EntityNotFoundException, StocksCuantityException;
	
}
