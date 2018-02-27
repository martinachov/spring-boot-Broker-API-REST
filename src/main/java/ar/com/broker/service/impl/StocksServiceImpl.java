package ar.com.broker.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.broker.dto.StocksBuyInfoDTO;
import ar.com.broker.dto.StocksDTO;
import ar.com.broker.dto.StocksPackDTO;
import ar.com.broker.dto.StocksPackResponseDTO;
import ar.com.broker.exception.EntityNotFoundException;
import ar.com.broker.exception.StocksCuantityException;
import ar.com.broker.repository.StocksRepository;
import ar.com.broker.service.StocksService;
import ar.com.broker.ws.cotizador.CotizadorService;
import ar.com.broker.ws.cotizador.CotizadorServiceResponse;

/**
 * StockService implementation
 * 
 * @author martinvacas
 *
 */
@Service
public class StocksServiceImpl implements StocksService {

	private static final Logger logger = LoggerFactory.getLogger(StocksServiceImpl.class);

	@Autowired
	private CotizadorService cotizadorService;

	@Autowired
	private StocksRepository stocksRepo;

	@Override
	public StocksDTO getStocksInfo(String ticker) throws EntityNotFoundException {

		logger.info("Getting Stocks Information ...");

		// Check ticker exist
		String stockName = stocksRepo.getStocksName(ticker);
		if (stockName == null) {
			throw new EntityNotFoundException("Ticker don't Exist");
		}

		// Actual price from COTIZADOR
		CotizadorServiceResponse res = cotizadorService.getStock(ticker);

		// Response
		StocksDTO response = new StocksDTO();
		response.setName(stockName);
		response.setTicker(res.getTicker());
		response.setPrice(res.getValue());

		return response;
	}

	@Override
	public StocksPackResponseDTO buyStoksPack(StocksPackDTO packStocks)
			throws EntityNotFoundException, StocksCuantityException {
		Map<String, Integer> stocksToBuy = new HashMap<>();

		logger.info("Buying Stocks ...");
		logger.info("Calculating current price of Stocks ...");

		// Obtain how many Stocks can be purchased
		stocksToBuy = this.stocksToBuyForEachCompany(packStocks.getListStocks());

		logger.info("Performing Stocks Purchase ...");
		Boolean okResult = stocksRepo.performPurchase(stocksToBuy);

		// Response
		StocksPackResponseDTO response = new StocksPackResponseDTO();
		if (okResult) {
			response.setStocksBuyed(stocksToBuy);
		}

		return response;
	}

	/**
	 * Calculates to the current price (from COTIZADOR) how many stocks can be
	 * purchased
	 * 
	 * @param listStocks
	 * @return
	 */
	private Map<String, Integer> stocksToBuyForEachCompany(List<StocksBuyInfoDTO> listStocks)
			throws EntityNotFoundException {
		Map<String, Integer> stocksToBuy = new HashMap<String, Integer>();
		Boolean result = true;

		Iterator<StocksBuyInfoDTO> itStoks = listStocks.iterator();
		while (itStoks.hasNext()) {
			StocksBuyInfoDTO stockBuyInfo = itStoks.next();
			String ticker = stockBuyInfo.getTicker();
			Float amount = stockBuyInfo.getAmount();

			// Actual price from COTIZADOR
			CotizadorServiceResponse actualPrice;
			try {
				actualPrice = cotizadorService.getStock(ticker);
				logger.info("Price for: " + ticker + " = " + actualPrice.getValue());
				int cant = (int) (amount / actualPrice.getValue());
				stocksToBuy.put(ticker, cant);
			} catch (EntityNotFoundException e) {
				logger.error("Error obtain Stocks price for: " + ticker);
				result = false;
				break;
			}
		}
		
		if(result) {
			return stocksToBuy;
		} else {
			throw new EntityNotFoundException("Error obtain Stocks price");
		}
	}

}
