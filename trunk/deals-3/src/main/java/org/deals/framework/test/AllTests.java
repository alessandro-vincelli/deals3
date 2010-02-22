package org.deals.framework.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite() {
		
		TestSuite suite = new TestSuite(
				"Test for org.deals.framework.bean.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(WebPageTest.class);
		suite.addTestSuite(WebPageDAOTest.class);
		suite.addTestSuite(PageTypeFactoryTest.class);
		suite.addTestSuite(WebPageComponentsTest.class);
		suite.addTestSuite(WebSiteTest.class);
		suite.addTestSuite(ItemDAOHibernateTest.class);
		suite.addTestSuite(CategoryDAOHibernateTest.class);
		suite.addTestSuite(SectionDAOHibernateTest.class);
		suite.addTestSuite(UserDAOHibernateTest.class);		
		//suite.addTestSuite(TestPageTypeDAOHibernate.class);
		//$JUnit-END$
		return suite;
	}


	
	
}
