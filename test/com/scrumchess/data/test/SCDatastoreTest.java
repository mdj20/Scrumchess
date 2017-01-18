package com.scrumchess.data.test;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.data.ScrumchessDatastoreFacade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.Test;

public class SCDatastoreTest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	private ScrumchessDatastoreFacade sdf;
	
	 @Before
	  public void setUp() {
	    helper.setUp();
	  
	  }

	 @After
	  public void tearDown() {
	    helper.tearDown();
	  }

	  // Run this test twice to prove we're not leaking any state across tests.
	  private void doTest() {
		  sdf = ScrumchessDatastoreFacade.getInstance();
	  }

	  @Test
	  public void testInsert1() {
	    doTest();
	  }

	  @Test
	  public void testInsert2() {
	    doTest();
	  }
}
