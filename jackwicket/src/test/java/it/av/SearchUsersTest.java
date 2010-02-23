package it.av;

import it.av.ocm.bean.User;
import it.av.ocm.services.UserService;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SearchUsersTest extends AbstractDependencyInjectionSpringContextTests {

	protected UserService userService;

	@Test
	public void testSimpleSearch() {
		try {
			User a = new User();
			a.setPath("/testUserDao");
			a.setUsername("AlessandroTest");

			userService.save(a);
			a = null;
			a = userService.getByPath("/testUserDao");
			assertNotNull("A is null", a);
			assertEquals("Invalid value for test", "AlessandroTest", a.getUsername());
			
			Collection<User> results =  userService.find("*AlessandroTest*");
			
			assertTrue(results.size() > 0);
			for (Iterator<User> iterator = results.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				userService.remove(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occurs during the unit test : " + e);
		}
	}

	// specifies the Spring configuration to load for this test fixture
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:application-context.xml" };
	}

	public SearchUsersTest() {
		// switch on field level injection
		setPopulateProtectedVariables(true);
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
	}	

}