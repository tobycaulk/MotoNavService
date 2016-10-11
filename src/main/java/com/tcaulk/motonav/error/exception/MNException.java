package com.tcaulk.motonav.error.exception;

import com.tcaulk.motonav.error.MNError;

public class MNException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MNError error;
	
	public MNException(MNError error) {
		super(error.getDescription());
		
		this.error = error;
	}

	public MNError getError() {
		return error;
	}

	public void setError(MNError error) {
		this.error = error;
	}
	
	public String toString() {
		return "ErrorCode [" + error.getErrorCode().getErrorCode() + "] Description [" + error.getDescription() + "]";
	}
}
