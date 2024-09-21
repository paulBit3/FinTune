/**
 * 
 */
package com.yetaidev.vendor.exception;

/**
 * This class handles invalid vendor api key parameter
 */
public class InvalidVendorAPIKeyParamsException extends Exception{
	public InvalidVendorAPIKeyParamsException(String params) {
		super("This API parameter("+ params +") does not exist.");
	}
}
