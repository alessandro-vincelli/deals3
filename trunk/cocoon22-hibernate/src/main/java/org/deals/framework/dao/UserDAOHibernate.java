package org.deals.framework.dao;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.Users;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com
 * Created on Aug 14, 2008
 */
public class UserDAOHibernate extends GenericHibernateDAO<Users, Integer> implements UserDAO   {

	public Users getUser(String username, String password) {
		Criteria crit = getSession().createCriteria(Users.class);
		crit.add(Restrictions.eq("usUsername", username));
		crit.add(Restrictions.eq("usPassword", password));
		List<Users> users = (List<Users>)crit.list();
		if (users != null && users.size() > 0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
	public List<Users> findAll() {
		List<Users> users = (List<Users>)super.findAll();
		int i = 1;
		for (Iterator<Users> iter = users.iterator(); iter.hasNext();) {
			Users element = (Users) iter.next();
			element.setRowId(i);
			i++;
		}
		return users;
	}
}