package com.sorting;


import java.util.Comparator;

import com.dao.RadioShow;

/**
 * Sorts the radio based on the number of live listens
 * @author User
 *
 */
public class RadioShowLiveComparator implements Comparator{
	
	
	SortEnum sort;
	
	/**
	 * Take in the constructor an enum that tells is the kind of sort to do
	 * @param theEnum asceonding and descending sorts are supported
	 */
	public RadioShowLiveComparator(SortEnum theEnum) {
		this.sort=theEnum;
	}
	/**
	 * Compare the number of archive listens
	 * @param rShow1
	 * @param rShow2
	 * @return 1 if first show has more, -1 if second show has more or 0 if equal
	 */
	  public int compare(Object rShow1, Object rShow2){
		    
		  int retVal=0;
		  
		   // See which sort was called
	       switch (sort) {
	       
	       // Do the sort descending
           case DESC:
        	   retVal=sortDesc(rShow1, rShow2);
               break;
           // Do the sort ascending        
           case ASC:
        	   retVal=sortAsc(rShow1, rShow2);
               break;
                        
 

	       }
	      return(retVal);
	      
	    }
	    
	  /**
	   * Does the sort ascending
	   * @param rShow1 the show object
	   * @param rShow2 the second show object
	   * @return return the 1, -1 or 0 based on the outcome of the sort
	   */
	  private int sortAsc(Object rShow1, Object rShow2) {
		  int retVal=0;
		  
		    // Cast the passed in objects to radio show objects
	        RadioShow show1 = (RadioShow)rShow1;        
	        RadioShow show2 = (RadioShow)rShow2;
	        
	        // Get the number of archive listens for both
	        int listens1=show1.getLiveListens();
	        int listens2=show2.getLiveListens();
	        
	        // based on the comparison return the appropriate value 1,-1 or 0 if equal
	        if(listens1 > listens2) 
	        	retVal=1;
	        else if(listens1 < listens2)
	        	retVal= -1;

	        return(retVal);	  
	  }
	  
	  /**
	   * Does the sort descending
	   * @param rShow1 the show object
	   * @param rShow2 the second show object
	   * @return return the 1, -1 or 0 based on the outcome of the sort
	   */
	  private int sortDesc(Object rShow1, Object rShow2) {
		  int retVal=0;
		  
		    // Cast the passed in objects to radio show objects
	        RadioShow show1 = (RadioShow)rShow1;        
	        RadioShow show2 = (RadioShow)rShow2;
	        
	        // Get the number of archive listens for both
	        int listens1=show1.getLiveListens();
	        int listens2=show2.getLiveListens();
	        
	        // based on the comparison return the appropriate value 1,-1 or 0 if equal
	        if(listens1 > listens2) 
	        	retVal=-1;
	        else if(listens1 < listens2)
	        	retVal= 1;

	        return(retVal);	  
	  }  
}

