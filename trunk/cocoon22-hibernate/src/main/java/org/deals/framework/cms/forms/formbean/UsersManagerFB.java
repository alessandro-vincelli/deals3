package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.Users;

/**
 * 
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 14, 2008
 */
public class UsersManagerFB {

	private List<Users> users;
	private List<Users> removedUsers;

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public void addUser(Users us) {
		users.add(us);
	}

	public List<Users> getRemovedUsers() {
		return removedUsers;
	}

	public void setRemovedUsers(List<Users> removedUsers) {
		this.removedUsers = removedUsers;
	}

}
