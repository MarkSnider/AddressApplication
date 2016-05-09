package com.forms;

/**
 * This form is for storing information related to a specific radio show like:
 * Title, description, archive listens, live listens, listed date
 * @author Mark Snider
 *
 */
public class RadioShowForm extends org.apache.struts.action.ActionForm {
	
	private long showId;			// primary key to the radio show 
	private String title;			// title of the show
	private String description;		// description of the show
	private String archiveListens;	// The number of people that down loaded the show
	private String liveListens;		// the number of people that listened live
	private String showDate;		// The date the show aired
	

	public long getShowId() {
		return showId;
	}
	public void setShowId(long showId) {
		this.showId = showId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArchiveListens() {
		return archiveListens;
	}
	public void setArchiveListens(String archiveListens) {
		this.archiveListens = archiveListens;
	}
	public String getLiveListens() {
		return liveListens;
	}
	public void setLiveListens(String liveListens) {
		this.liveListens = liveListens;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	
	
}
