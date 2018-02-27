package ar.com.broker.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom error response structure
 * 
 * @author martinvacas
 *
 */
public class ApiError {

	private HttpStatus statusCode;
	private String message;
	
	/**
	 * 
	 */
	private ApiError(){
	}
	
	/**
	 * 
	 * @param status
	 */
    public ApiError(HttpStatus status) {
        this();
        this.setStatusCode(status);
    }
    
    /**
     * 
     * @param status
     * @param message
     */
    public ApiError(HttpStatus status, String message) {
        this();
        this.setStatusCode(status);
        this.setMessage(message);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
