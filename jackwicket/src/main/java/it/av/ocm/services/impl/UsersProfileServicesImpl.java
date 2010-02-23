package it.av.ocm.services.impl;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;

import org.apache.commons.lang.StringUtils;

/**
 * UserProfile services class. Use this class to manage the {@link UserProfile}
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class UsersProfileServicesImpl extends JcrServiceImpl<UserProfile> implements UserProfileService {

    @Override
    public UserProfile save(UserProfile object) throws JackWicketException {
        if (StringUtils.isEmpty(object.getPath())) {
            object.setPath("/" + StringUtils.deleteWhitespace(object.getName()));
        }
        if(getJcrMappingtemplate().itemExists(object.getPath())){
            update(object);
        }
        else{
            insert(object);
        }
        getJcrMappingtemplate().save();
        return object;
    }

}
