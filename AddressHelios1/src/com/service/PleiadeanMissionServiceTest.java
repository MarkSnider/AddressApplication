package com.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Contact251;
import com.dao.PleiadeanMission;


public class PleiadeanMissionServiceTest {
	
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
	public void testSelectAll() {
		// Get all of the data
		List<PleiadeanMission> theList = PleiadeanMissionService.selectAll();
		assert(theList != null);
		assert(theList.size() > 0);				
	}
	@Test
	public void testSelectLike() {
		// Get all of the data
		List<PleiadeanMission> theList = PleiadeanMissionService.selectInfoLike("Pelegon");
		assert(theList != null);
		assert(theList.size() > 0);				
	}
	
	@Test
	public void testSelectByLineNumber() {
		String sql="select linenumber, info from pleiadean_mission where linenumber < 175 and linenumber > 165";
		List<PleiadeanMission> theList = PleiadeanMissionService.selectByLineNumber(sql);	
		assert(theList != null);
		assert(theList.size() > 0);				
	}
}
