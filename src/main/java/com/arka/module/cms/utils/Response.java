/**
 * 
 */
package com.arka.module.cms.utils;

/**
 * @author Admin
 *
 */
public class Response {
	
	
	int status;
	 String message;
	 Object data;
	 
	 
	 
	 /**
		 * @param status
		 * @param message
		 * @param data
		 */
		public Response(int status,  Object data) {
			super();
			this.status = status;
			this.data = data;
		}
	 
	/**
	 * @param status
	 * @param message
	 * @param data
	 */
	public Response(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	 
	 
	
	
	

}
