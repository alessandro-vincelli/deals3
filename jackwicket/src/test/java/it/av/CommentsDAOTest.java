package it.av;

import it.av.ocm.JackWicketException;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class CommentsDAOTest extends AbstractDependencyInjectionSpringContextTests {

//	protected CommentsDAO commentsDAO;

	@Test
	public void testUsersProfileBasic() throws JackWicketException {
	    /*		try {
			Comments a = new Comments();
			a.setPath("/testCommentsDAO");
			a.setTitle("CommentTitle");
			a.setDate(Date.valueOf("2008-19-03"));

			commentsDAO.makePersistent(a);
			a = null;
			a = commentsDAO.findById("/testCommentsDAO", false);
			assertNotNull("A is null", a);
			assertEquals("Invalid value for comment title", "CommentTitle", a.getTitle());
			assertEquals("Invalid value for commnet data", Date.valueOf("2008-19-03"), a.getDate());
			
			Collection<Comments> all = commentsDAO.findAll();
			assertNotNull(all);
			assertTrue(all.size() > 0);
			
			commentsDAO.makeTransient(a);
			
			a = new Comments();
			a.setPath("/testCommentsDAO");
			a.setTitle("CommentTitle");

			
			Comments b = new Comments();
			b.setPath("/testCommentsDAOB");
			a.setTitle("CommentTitleB");

			List<Comments> comments = new ArrayList<Comments>(2);
			comments.add(a);
			comments.add(b);
			commentsDAO.makePersistenAll(comments);
			
			commentsDAO.makeTransient(a);
			commentsDAO.makeTransient(b);		
			
		} 
		catch (VersionException e) {
			fail(e.getMessage());
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	*/}
	
    // specifies the Spring configuration to load for this test fixture
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:application-context.xml" };
    }

    public CommentsDAOTest() {
        // switch on field level injection
        setPopulateProtectedVariables(true);
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
    }
}