
package com.yetaidev.vendor.exception;

/**
 * This class handles missing vendor api key
 */
public class MissingVendorAPIKeyException extends Exception{
	public MissingVendorAPIKeyException(String val) {
		super(val);
	}
}
