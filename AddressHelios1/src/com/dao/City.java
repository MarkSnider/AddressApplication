package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;





/**
 * The city object associated to the look up table
 * @author User
 *
 */
public class City {

	private Integer cityid;
	private String name;
	private Integer  customerid;
	
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}	
	
	public String toString() {
		return(name);
	}
	
	public static void main(String[] args) {
	
		
	    // open the session 
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();	
	    
		sessionFactory = new Configuration().configure().buildSessionFactory();	

	    Session session = sessionFactory.getCurrentSession();
		session.beginTransaction().begin();
		Criteria theCriteria = session.createCriteria(City.class);
		List<City> theList = theCriteria.list();
		session.getTransaction().commit();	// Object is actually saved to the database here...
		
		for (City theCity: theList) {
			System.err.println(" theCity.getCityid() " + theCity.getCityid() + " theCity.getName() " + theCity.getName());
		}
		
				
	}
}

