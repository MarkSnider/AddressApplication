package com.dao;

/**
 * Very small class to keep track of the current sort
 * @author Mark Snider
 *
 */
public class CurrentSort {
	private String currentSort;			// Can be ascending or descending		
	private String sortImage;

	/**
	 * Can be ascending or descending	
	 * @return see the constants for the exact constant returned
	 */
	public String getCurrentSort() {
		return currentSort;
	}
	/**
	 * Can be ascending or descending	
	 * @param see the constants for the exact constant returned
	 */
	public void setCurrentSort(String currentSort) {
		this.currentSort = currentSort;
	}
	
	/**
	 * Sort ascending or descending image
	 * @return look in constants for actual image name
	 */
	public String getSortImage() {
		return sortImage;
	}
	
	/**
	 * Sort ascending or descending image
	 * @param look in constants for actual image name
	 */	
	public void setSortImage(String sortImage) {
		this.sortImage = sortImage;
	}

}
