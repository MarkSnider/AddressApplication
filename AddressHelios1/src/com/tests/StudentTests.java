package com.tests;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.hibernate.SQLQuery;
import org.junit.Assert;

import com.dao.Address;
import com.dao.CurrentSort;
import com.dao.Email;
import com.dao.Event;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.Student;
import com.dao.StudentType;
import com.dao.StudentRadioShow;
import com.dao.Web;
import com.constants.Constants;
import com.dao.StudentEmail;
import com.dao.StudentPhone;
import com.constants.SqlQuery;
import com.example.SomeExampleClass;
import com.service.HibernateUtil;

public class StudentTests {

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


}
