package com.service;


import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.StudentRadioShow;

public class StudentRadioShowServiceTest {

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
	public void testSelectStudentRadioShow() {
		// Hard coded revisit later
		List<StudentRadioShow> theList = StudentRadioShowService.selectStudentRadioShowByStudentIdHQL(210);
		assert(theList != null);
		assert(theList.size() > 0);
	}
}
