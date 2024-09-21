/**
 * 
 */
package com.yetaidev.vendor.exception;

/**
 * This class handles invalid vendor api key
 */
public class InvalidVendorAPIKeyException extends Exception{
	public InvalidVendorAPIKeyException(String val) {
		super(val);
	}
}
