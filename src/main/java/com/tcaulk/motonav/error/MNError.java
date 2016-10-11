package com.tcaulk.motonav.error;

public class MNError {
	
	public static final MNError NO_ERROR = new MNError(ErrorCode.NONE, "");
	
	public static final MNError INVALID_GMS_CONTEXT = new MNError(ErrorCode.INVALID_GMS_CONTEXT, "Invalid GMS context");
	public static final MNError COULD_NOT_RETRIEVE_DIRECTIONS_FROM_GOOGLE = new MNError(ErrorCode.COULD_NOT_RETRIEVE_DIRECTIONS_FROM_GOOGLE, "Could not retrieve directions");
	public static final MNError INVALID_GMS_DIRECTIONS = new MNError(ErrorCode.INVALID_GMS_DIRECTIONS, "Invalid/Incomplete directions");
	
	public enum ErrorCode {
		NONE(0),
		INVALID_GMS_CONTEXT(1000),
		COULD_NOT_RETRIEVE_DIRECTIONS_FROM_GOOGLE(1001),
		INVALID_GMS_DIRECTIONS(1002);
		
		private int errorCode;
		
		ErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
		
		public int getErrorCode() {
			return errorCode;
		}
	}
	
	private String description;
	private ErrorCode errorCode;
	
	public MNError(ErrorCode errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
