package com.dao;

/**
 * This object holds the web site information for a student
 * @author Mark Snider
 *
 */
public class Web implements java.io.Serializable{

	private long webid;
	private String url;
	
	public Web() {
		
	}
	public Web(String webAddr) {
		this.url=webAddr;
	}	
	public Web(String webAddr, long webid) {
		this.url=webAddr;
		this.webid=webid;
	}		
	/**
	 * This is the key to the table
	 * @return
	 */
	public long getWebid() {
		return webid;
	}
	public void setWebid(long webid) {
		this.webid = webid;
	}
	
	/**
	 * This is the actual address to the web site
	 * @return a URL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * This is the actual address to the web site
	 * @param a URL
	 */	
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * overridden so that in the JSP we can display this information easily
	 */
	public String toString() {
		String Information=getUrl();
		
		if (Information == null || Information.equals("?")) {
			Information="";
		}
		

		return(Information);
	}		
}
