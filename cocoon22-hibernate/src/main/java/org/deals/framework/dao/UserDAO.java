package org.deals.framework.dao;

import org.deals.framework.bean.Users;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com
 * Created on Aug 14, 2008
 */
public interface UserDAO extends GenericDAO<Users, Integer> {
	public Users getUser(String username, String password);
}
