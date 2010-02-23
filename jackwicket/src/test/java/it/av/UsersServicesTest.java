package it.av;

import it.av.ocm.bean.User;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;
import it.av.ocm.services.UserService;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UsersServicesTest extends AbstractDependencyInjectionSpringContextTests {

	protected UserService userService;
	protected UserProfileService userProfileService;
	
	@Test
	public void testOperations() {
        User a = new User();
	    try {
			a.setUsername("Alessandro");
			userService.save(a);
			Collection<User> all = userService.getAll();
			assertTrue(all.size() > 0);
			a = userService.getByPath("/Alessandro");
			assertNotNull(a);
			List<User> revs = userService.getAllRevisions(a.getPath());
			assertTrue(revs.size() == 1);
			userService.remove(a);	
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occurs during the unit test : " + e);
		}
	}
	
	@Test
    public void testOperationsWithProfile() {
        User a = new User();
        UserProfile profile = new UserProfile();
        try {
            profile.setName("USER");
            profile = userProfileService.save(profile);
            
            a.setUsername("Alessandro");
            a.setUserProfile(profile);
            userService.save(a);
            a = userService.getByPath("/Alessandro");
            assertNotNull(a);
            assertNotNull(a.getUserProfile());
            
            Collection<User> all = userService.getByProfile(profile);
            
            userService.remove(a);
            userProfileService.remove(profile);
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

	public UsersServicesTest() {
		// switch on field level injection
		setPopulateProtectedVariables(true);
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
	}

}