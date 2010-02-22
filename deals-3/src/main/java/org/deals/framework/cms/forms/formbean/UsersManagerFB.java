package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.Users;

public class UsersManagerFB {

	private List<Users> users;
	private List<Users> removedUsers;
	
	private List<UserProfile> profileSL;

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<UserProfile> getProfileSL() {
		return profileSL;
	}

	public void setProfileSL(List<UserProfile> profileSL) {
		this.profileSL = profileSL;
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
