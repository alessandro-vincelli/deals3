package it.av.ocm.services.impl;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.ocm.query.Filter;
import org.apache.jackrabbit.ocm.query.Query;
import org.apache.jackrabbit.ocm.query.QueryManager;

/**
 * User Services class, Use this class to manage the {@link User}
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class UsersServicesImpl extends JcrServiceImpl<User> implements UserService {

    /* (non-Javadoc)
     * @see it.av.ocm.services.UserService#save(it.av.ocm.bean.User)
     */
    @Override
    public User save(User user) throws JackWicketException {
        if (StringUtils.isEmpty(user.getPath())) {
            user.setPath("/" + StringUtils.deleteWhitespace(user.getUsername()));
        }
        if(getJcrMappingtemplate().itemExists(user.getPath())){
            update(user);
        }
        else{
            insert(user);
        }
        getJcrMappingtemplate().save();
        return user;
    }

    @Override
    public List<User> getByProfile(UserProfile profile){
        QueryManager queryManager = getJcrMappingtemplate().createQueryManager();
        Filter filter = queryManager.createFilter(getPersistentClass());
        // scope ends with double slash // to search in all sub nodes and fields 
        filter.setScope(getBasePath() + "//");
        filter.addEqualTo("userProfile", profile);
        Query query = queryManager.createQuery(filter);
        return new ArrayList<User>(getJcrMappingtemplate().getObjects(query));
    }

}
