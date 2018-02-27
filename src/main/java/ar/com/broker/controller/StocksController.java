package ar.com.broker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.broker.dto.StocksDTO;
import ar.com.broker.dto.StocksPackDTO;
import ar.com.broker.dto.StocksPackResponseDTO;
import ar.com.broker.exception.EntityNotFoundException;
import ar.com.broker.exception.StocksCuantityException;
import ar.com.broker.service.StocksService;

/**
 * Stocks Controller
 * 
 * @author martinvacas
 *
 */
@RestController
@RequestMapping(value="/api/stocks")
public class StocksController {

	private static final Logger logger = LoggerFactory.getLogger(StocksController.class);
	
	@Autowired
	private StocksService stockService;
	
	
	/**
	 * Get stocks information
	 *  
	 * @param ticker
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="/{ticker}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StocksDTO> getStocks(@PathVariable String ticker) throws EntityNotFoundException {
		logger.info("GET - /api/stocks/" + ticker);
		StocksDTO res = stockService.getStocksInfo(ticker);
		return new ResponseEntity<StocksDTO>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StocksPackResponseDTO> buyStoksPack(@RequestBody StocksPackDTO pack) throws EntityNotFoundException, StocksCuantityException {
		logger.info("POST - /api/stocks/");
		StocksPackResponseDTO res = stockService.buyStoksPack(pack);
		return new ResponseEntity<StocksPackResponseDTO>(res, HttpStatus.OK);
	}
	
	
	
}
