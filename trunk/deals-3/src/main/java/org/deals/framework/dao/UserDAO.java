package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.Users;

public interface UserDAO extends GenericDAO<Users, Integer> {

	public Users getUser(String username, String password);

	public List<Users> getAllUsers();

}
