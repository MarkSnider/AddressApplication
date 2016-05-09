package com.service;


import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Contact251;



public class Contact251ServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testselectInfoLike() {
		// Look for some data
		List<Contact251> theList = Contact251Service.selectInfoLike("Sirius");
		assert(theList != null);
		assert(theList.size() > 0);		
	}
	
	@Test
	public void testSelectAll() {
		// Get all of the data
		List<Contact251> theList = Contact251Service.selectAll();
		assert(theList != null);
		assert(theList.size() > 0);				
	}
	@Test
	public void testSelectByLineNumber() {
		String sql="select linenumber, info from Contact251 where linenumber < 175 and linenumber > 165";
		List<Contact251> theList = Contact251Service.selectByLineNumber(sql);
		assert(theList != null);
		assert(theList.size() > 0);				
	}
}
