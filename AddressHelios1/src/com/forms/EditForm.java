package com.forms;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.dao.RadioShow;

public class EditForm extends org.apache.struts.action.ActionForm {
	
	private String name;
	private String street;
	private String city;
	private String country;
	private String studentType;


	private String state;
	private String zip;
	private String homePhone;
	private String cellPhone;
	private String homeEmail;
	private String workEmail;
	private long studentId;
	private String information;		
	private String urlOne;
	private String urlTwo;
	private String urlThree;
	

	
	// For the multiple RadioShows for students with shows
	private Set<RadioShow> studentRadioShows = new HashSet<RadioShow>(0);	
	
	/**
	 * For displaying the radio show information for students
	 * @return a set of student radio shows
	 */
	public Set<RadioShow> getStudentRadioShows() {
		return studentRadioShows;
	}
	/**
	 * For displaying the radio show information for students
	 * @param a set of student radio shows
	 */
	public void setStudentRadioShows(Set<RadioShow> studentRadioShows) {
		this.studentRadioShows = studentRadioShows;
	}	
	public String getUrlOne() {
		return urlOne;
	}
	public void setUrlOne(String urlOne) {
		this.urlOne = urlOne;
	}
	public String getUrlTwo() {
		return urlTwo;
	}
	public void setUrlTwo(String urlTwo) {
		this.urlTwo = urlTwo;
	}
	public String getUrlThree() {
		return urlThree;
	}
	public void setUrlThree(String urlThree) {
		this.urlThree = urlThree;
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getHomeEmail() {
		return homeEmail;
	}
	public void setHomeEmail(String homeEmail) {
		this.homeEmail = homeEmail;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}	
	
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (name == null || name.length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));

        }
        if (name != null && name.length() > 100) {
            errors.add("name", new ActionMessage("error.name.toolarge"));
        }
        
        if (homePhone == null || homePhone.length() < 1) {
            errors.add("homePhone", new ActionMessage("error.homePhone.required"));
            
        }  
        if (homePhone != null && homePhone.length() > 15) {
            errors.add("homePhone", new ActionMessage("error.homePhone.toolarge"));
            
        }  
        
        if (cellPhone == null || cellPhone.length() < 1) {
            errors.add("cellPhone", new ActionMessage("error.cellPhone.required"));            
        }   
        
        if (cellPhone != null && cellPhone.length() > 15) {
            errors.add("cellPhone", new ActionMessage("error.cellPhone.toolarge"));            
        }      
        
        
        if (homeEmail == null || homeEmail.length() < 1) {
            errors.add("homeEmail", new ActionMessage("error.homeEmail.required"));
            
        }  
        
        if (homeEmail != null && homeEmail.length() > 100) {
            errors.add("homeEmail", new ActionMessage("error.homeEmail.toolarge"));
            
        }  
        
        if (workEmail == null || workEmail.length() < 1) {
            errors.add("workEmail", new ActionMessage("error.workEmail.required"));
            
        }  

        if (workEmail != null && workEmail.length() > 100) {
            errors.add("workEmail", new ActionMessage("error.workEmail.toolarge"));
            
        }     	
        return errors;
    }



	
}
