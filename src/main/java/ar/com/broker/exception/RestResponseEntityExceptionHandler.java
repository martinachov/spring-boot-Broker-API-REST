package ar.com.broker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Central point for wrapping exceptions
 * 
 * @author martinvacas
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	/**
	 * 
	 */
	public RestResponseEntityExceptionHandler() {
        super();
    }
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<Object> handleBadRequest(final HttpClientErrorException ex, final WebRequest request) {
        final String bodyOfResponse = "Bad request error";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, bodyOfResponse));
    }
    
    /**
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        final String bodyOfResponse = ex.getMessage();
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, bodyOfResponse));
    }
    
    /**
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { StocksCuantityException.class})
    protected ResponseEntity<Object> handleStockCuantityException(final StocksCuantityException ex, final WebRequest request) {
        final String bodyOfResponse = ex.getMessage();
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse));
    }
    
    /**
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ HttpServerErrorException.class, NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = "Server failed";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse));
    }
    
    /**
     * 
     * @param apiError
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
	

}
