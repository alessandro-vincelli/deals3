package org.deals.framework.test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TestConfig {
	private static final String BUNDLE_NAME = "org.deals.framework.test.deals_test"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private TestConfig() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
