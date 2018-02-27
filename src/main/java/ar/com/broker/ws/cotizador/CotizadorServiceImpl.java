package ar.com.broker.ws.cotizador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.com.broker.exception.EntityNotFoundException;

/**
 * CotizadorService implementation
 * Get Stocks current value from a ticker
 *  
 * @author martinvacas
 *
 */
@Service
public class CotizadorServiceImpl implements CotizadorService {

	private static final Logger logger = LoggerFactory.getLogger(CotizadorServiceImpl.class);
	
	private final RestTemplate restTemplate;
	
	@Value("${cotizador.service.api.url}")
	private String cotizadorUrl;
	
	@Value("${cotizador.service.api.port}")
	private String cotizadorPort;
	
	@Value("${cotizador.service.api.op}")
	private String cotizadorOp;
	
	
	
	/**
	 * 
	 * @param restTemplateBuilder
	 */
	public CotizadorServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public CotizadorServiceResponse getStock(String ticker) throws EntityNotFoundException {
		try {
			logger.info("Obtain actual price for: " + ticker);
			String param = "{ticker}";
			String cotizadorURL = cotizadorUrl + ":" +  cotizadorPort + "/" + cotizadorOp +"/" + param;
			CotizadorServiceResponse res = this.restTemplate.getForObject(cotizadorURL, CotizadorServiceResponse.class, ticker);
			
			logger.info(res.toString());
			
			return res;
		} catch(Exception ex) {
			throw new EntityNotFoundException("Error trying to obtain price from COTIZADOR");
		}

	}

}
