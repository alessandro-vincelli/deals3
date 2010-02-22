package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.UserProfile;

public interface UserProfileDAO extends GenericDAO<UserProfile, Integer> {
	List<UserProfile> getAllUserProfile();
}
