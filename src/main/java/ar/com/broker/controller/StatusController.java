package ar.com.broker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.broker.dto.StatusDTO;

/**
 * 
 * @author martinvacas
 *
 */
@RestController
@RequestMapping(value="/api/status")
public class StatusController {

	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusDTO> getStatus() {
		StatusDTO status = new StatusDTO();
		status.setStatus("API is Running!!");
		return new ResponseEntity<StatusDTO>(status, HttpStatus.OK);
	}
}
