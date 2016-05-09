package com.dao;

import java.util.Date;

public class Event {
    private Long id;

    private String title;
    private Date date;

    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
/*    CREATE TABLE  `employees`.`EVENTS` (
    	      `EVENT_ID` int(32) NOT NULL auto_increment,
    	      PRIMARY KEY  (`EVENT_ID`), title varchar(255), EVENT_DATE Date
    	    ) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;*/
    
 
}

