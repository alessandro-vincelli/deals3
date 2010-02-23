package it.av.ocm.web.data;

import it.av.ocm.JackWicketException;
import it.av.ocm.JackWicketRunTimeException;
import it.av.ocm.bean.User;
import it.av.ocm.services.UserService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 *
 */
public class DetachableUserModel extends LoadableDetachableModel<User> {

    private static final long serialVersionUID = 1L;
    private final String id;
    private UserService userService;

    /**
     * 
     * @param c
     * @param userService
     */
    public DetachableUserModel(User c, UserService userService) {
        this(c.getPath());
        this.userService = userService;
    }

    /**
     * @param id
     */
    public DetachableUserModel(String id) {
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
        } else if (obj instanceof DetachableUserModel) {
            DetachableUserModel other = (DetachableUserModel) obj;
            return other.id == id;
        }
        return false;
    }

    /**
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected final User load() {
        try {
            return userService.getByPath(id);
        } catch (JackWicketException e) {
            return null;
        }
    }
}