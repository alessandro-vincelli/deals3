package org.deals.framework.factory;

import org.deals.framework.bean.Template;

public class TemplateFactory {
	
	public static Template createTestTemplate() {
		Template t = new Template();
		t.setTeId(Template.TEMPLATE_TEST_ID);
		t.setTeName(Template.TEMPLATE_TEST_NAME);
		return t;
	}
	
}
