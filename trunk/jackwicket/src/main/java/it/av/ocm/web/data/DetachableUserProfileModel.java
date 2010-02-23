package it.av.ocm.web.data;

import it.av.ocm.JackWicketException;
import it.av.ocm.JackWicketRunTimeException;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class DetachableUserProfileModel extends LoadableDetachableModel<UserProfile> {

    private static final long serialVersionUID = 1L;
    private final String id;
    private UserProfileService userProfileService;

    /**
     * 
     * @param c
     * @param userProfileService
     */
    public DetachableUserProfileModel(UserProfile c, UserProfileService userProfileService) {
        this(c.getPath());
        this.userProfileService = userProfileService;
    }

    /**
     * @param id
     */
    public DetachableUserProfileModel(String id) {
        if (id.equals("")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    /**
     * used for dataview with ReuseIfModelsEqualStrategy item reuse strategy
     * 
     * @see org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof DetachableUserProfileModel) {
            DetachableUserProfileModel other = (DetachableUserProfileModel) obj;
            return other.id == id;
        }
        return false;
    }

    /**
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected final UserProfile load() {
        try {
            return userProfileService.getByPath(id);
        } catch (JackWicketException e) {
            throw new JackWicketRunTimeException(e);
        }
    }
}