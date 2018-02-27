package ar.com.broker.dto;

import java.io.Serializable;

/**
 * 
 * @author martinvacas
 *
 */
public class StatusDTO implements Serializable {

	private static final long serialVersionUID = 982376160258700006L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
