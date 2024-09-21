/**
 * 
 */
package com.yetaidev.vendor.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.NoArgsConstructor;


/**
 * this class will manage global vendor API error
 */

//@Slf4j
//@RestControllerAdvice
@NoArgsConstructor
public class AlphaVantageException extends RuntimeException {
	
	/**
	 * final variables
	 */
	private static final long serialVersionUID = 1L;
	static final String INVALID_API_KEY = "Error! Invalid API KEY.";
	static final String MISSING_API_KEY = "Error! API KEY might be missing.";
	
	public AlphaVantageException(String message, Exception e) {
		super(message, e);
	}

	public AlphaVantageException(String message) { 
		super(message);
	}
		  
	//implementing the Exception
	public static void vendorAPIKeyException(String val, String params) throws 
		MissingVendorAPIKeyException, 
		InvalidVendorAPIKeyException, 
		InvalidVendorAPIKeyParamsException{
		
		String invalidParameter = "the parameter("+ params +") apikey is invalid or missing";
		
		//check the API Key
		if (val.equals(MISSING_API_KEY)) {
			throw new MissingVendorAPIKeyException(MISSING_API_KEY);
		} else if (val.equals(INVALID_API_KEY)) {
			throw new InvalidVendorAPIKeyException(INVALID_API_KEY);
		} else if (val.equals(invalidParameter)) {
			throw new InvalidVendorAPIKeyParamsException(params);
		}
	}
	
	//maping it to the JSON object using a Map entry - key
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public static void handleException(
			Map.Entry<String, JsonNode> mapEntry, String params) throws 
	
		    InvalidVendorAPIKeyException,
			MissingVendorAPIKeyException, 
			InvalidVendorAPIKeyParamsException {
			String val = mapEntry.getValue().toString();
			vendorAPIKeyException(val, params);
		}
}

