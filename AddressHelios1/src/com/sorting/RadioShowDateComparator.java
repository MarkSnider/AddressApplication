package com.sorting;

import java.util.Comparator;

import com.dao.RadioShow;

/**
 * Does the sort of radio show information by date
 * @author User
 *
 */
public class RadioShowDateComparator implements Comparator{
	
	SortEnum sort;
	
	/**
	 * Take in the constructor an enum that tells is the kind of sort to do
	 * @param theEnum ascending and descending sorts are supported
	 */
	public RadioShowDateComparator(SortEnum theEnum) {
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
	        
	        // Get the dates for both
	        long date1=show1.getListedDate().getTime();
	        long date2=show2.getListedDate().getTime();
	        
	        // based on the comparison return the appropriate value 1,-1 or 0 if equal
	        if(date1 > date2) 
	        	retVal=1;
	        else if(date1 < date2)
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
	        long date1=show1.getListedDate().getTime();
	        long date2=show2.getListedDate().getTime();
	        
	        // based on the comparison return the appropriate value 1,-1 or 0 if equal
	        if(date1 > date2) 
	        	retVal=-1;
	        else if(date1 < date2)
	        	retVal= 1;

	        return(retVal);	  
	  }  	

}
