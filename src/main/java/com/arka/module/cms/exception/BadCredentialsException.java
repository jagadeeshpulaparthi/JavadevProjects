/**
 * 
 */
package com.arka.module.cms.exception;

/**
 * @author Madhu
 *
 */
public class BadCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BadCredentialsException(String msg) {
		super(msg);
	}
	
	public BadCredentialsException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
