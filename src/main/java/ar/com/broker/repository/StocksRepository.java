package ar.com.broker.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.broker.exception.StocksCuantityException;


/**
 * Stock Repository
 * 
 * @author martinvacas
 *
 */

@Component
public class StocksRepository {

	private static final Logger logger = LoggerFactory.getLogger(StocksRepository.class);
	
	//To manage stocks names
	private Map<String, String> stocksNames = new HashMap<>();
	
	//To manage each stocks cuantity
	private Map<String, Integer> stocksCuantity = new HashMap<>();

	public StocksRepository(){
		try {
			logger.info("Init the StockRepository from properties ...");
			
			//Load Stocks names
			Properties stocksNamesProp = new Properties();
			stocksNamesProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("nombres.properties"));
			//Load Stocks cuantity
			Properties stocksCuantityProp = new Properties();
			stocksCuantityProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("stock-inicial.properties"));
			
			//Fill names map			
			stocksNames.putAll(stocksNamesProp.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));

			//Fill stocks cuantity	
			stocksCuantity.putAll(stocksCuantityProp.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> Integer.parseInt(e.getValue().toString()))));
			
			logger.info("StockRepository initialized ...");
			logger.info("Names: " + stocksNames);
			logger.info("Cuantity" + stocksCuantity);
			
		} catch (IOException e) {
			logger.error("Fail to load properties");
		}

	}
	
	/**
	 * 
	 * @param ticker
	 * @return
	 */
	public Integer getCurrentStock(String ticker){
		return stocksCuantity.get(ticker);
	}
	
	/**
	 * 
	 * @param ticker
	 * @return
	 */
	public Boolean existStocks(String ticker){
		return stocksNames.containsKey(ticker);
	}
	
	/**
	 * 
	 * @param ticker
	 * @return
	 */
	public String getStocksName(String ticker){
		return stocksNames.get(ticker);
	}
	
	/**
	 * 
	 * @param stocksToBuy
	 * @return
	 * @throws StocksCuantityException 
	 */
	public Boolean performPurchase(Map<String, Integer> stocksToBuy) throws StocksCuantityException {
		Map<String, Integer> stocks = new HashMap<>();
		Boolean purchaseOk = true;
		
		logger.info("Stocks before purchase = -" + stocksCuantity);
		
		for (String key: stocksCuantity.keySet()){
			if (stocksToBuy.keySet().contains(key)) {
				Integer c1 = stocksCuantity.get(key);
				Integer c2 = stocksToBuy.get(key);
				if(c1 >= c2){
					stocks.put(key, c1 - c2);
				} else {
					purchaseOk = false;
					throw new StocksCuantityException("Stock insuficiente");
				}
			} else {
				stocks.put(key, stocksCuantity.get(key));
			}
		}
		
		if(purchaseOk){
			stocksCuantity = stocks;
			logger.info("Stocks after purchase = -" + stocksCuantity);
		}

		return purchaseOk;
	}
	
	
	//Getters and Setters
	public Map<String, String> getStocksNames() {
		return stocksNames;
	}

	public void setStocksNames(Map<String, String> stocksNames) {
		this.stocksNames = stocksNames;
	}

	public Map<String, Integer> getStocksCuantity() {
		return stocksCuantity;
	}

	public void setStocksCuantity(Map<String, Integer> stocksCuantity) {
		this.stocksCuantity = stocksCuantity;
	}	
	
}
