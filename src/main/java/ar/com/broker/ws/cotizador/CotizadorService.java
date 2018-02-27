package ar.com.broker.ws.cotizador;

import ar.com.broker.exception.EntityNotFoundException;

/**
 * CotizadorService Interface
 * 
 * @author martinvacas
 *
 */
public interface CotizadorService {

	CotizadorServiceResponse getStock(String ticker) throws EntityNotFoundException;
}
