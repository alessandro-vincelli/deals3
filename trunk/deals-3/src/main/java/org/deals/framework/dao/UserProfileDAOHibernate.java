package org.deals.framework.dao;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.UserProfile;

public class UserProfileDAOHibernate extends GenericHibernateDAO<UserProfile, Integer>
implements UserProfileDAO {

	public List<UserProfile> getAllUserProfile() {
		//Query q = getSession().createQuery("from UserProfile order by upId"); 
		//List<UserProfile> profiles = (List<UserProfile>)q.list();
		List<UserProfile> profiles = (List<UserProfile>)super.findAll();
		
		int i=1;
		for (Iterator<UserProfile> iter = profiles.iterator(); iter.hasNext();) {
			UserProfile element = (UserProfile)iter.next();
			element.setRowId(i);
			i++;
		}
				
		return profiles;
	}


}
