package org.deals.framework.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestDocumentati {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.deals.framework.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(SectionTest.class);
		suite.addTestSuite(WebPageTest.class);
		suite.addTestSuite(CategoryTest.class);
		suite.addTestSuite(ItemTest.class);
		//$JUnit-END$
		return suite;
	}

}
