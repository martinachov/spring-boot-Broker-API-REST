package ar.com.broker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception
 * 
 * @author martinvacas
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StocksCuantityException extends Exception {

	private static final long serialVersionUID = 7392861424112912348L;
	
	/**
	 * 
	 */
	public StocksCuantityException(){
		
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public StocksCuantityException(String message, Throwable cause) {
        super(message, cause);
    }

	/**
	 * 
	 * @param message
	 */
    public StocksCuantityException(String message) {
        super(message);
    }

    /**
     * 
     * @param cause
     */
    public StocksCuantityException(Throwable cause) {
        super(cause);
    }

}
