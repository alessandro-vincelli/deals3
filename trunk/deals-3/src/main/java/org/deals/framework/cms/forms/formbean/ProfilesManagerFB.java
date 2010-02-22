package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.UserProfile;

public class ProfilesManagerFB {

	private List<UserProfile> profiles;	

	public List<UserProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}

	
	public void addProfile(UserProfile up) {
		profiles.add(up);
	}
	
}
