package it.av;

import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UsersProfileServicesTest extends AbstractDependencyInjectionSpringContextTests {

	protected UserProfileService userProfileService;

	@Test
	public void testOperations() {
        UserProfile a = new UserProfile();
	    try {
			a.setName("profileName");
			userProfileService.save(a);
			Collection<UserProfile> all = userProfileService.getAll();
			assertTrue(all.size() > 0);
			a = userProfileService.getByPath(a.getPath());
			assertNotNull(a);
			List<UserProfile> revs = userProfileService.getAllRevisions(a.getPath());
			assertTrue(revs.size() == 1);
			userProfileService.remove(a);	
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

	public UsersProfileServicesTest() {
		// switch on field level injection
		setPopulateProtectedVariables(true);
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
	}

}