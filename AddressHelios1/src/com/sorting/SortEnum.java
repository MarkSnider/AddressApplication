package com.sorting;

public enum SortEnum {
	
	// Handles the sorting by archive, by date, and by live listens
	DESC(1), ASC(2), DESC_DATE(3), ASC_DATE(4), DESC_LIVE(5), ASC_LIVE(6);

	private int code;
	
	private SortEnum(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	
}
