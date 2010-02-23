package it.av.ocm.web.data;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.services.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Request;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

/**
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class UsersSortableDataProvider extends SortableDataProvider<User> {
    private static final long serialVersionUID = 1L;
    private UserService usersService;
    private Collection<User> results;

    /**
     * @param usersService
     */
    public UsersSortableDataProvider(UserService usersService) {
        super();
        this.usersService = usersService;
        results = new ArrayList<User>();
        // setSort(LightVac.SortedFieldNames.dateTime.value(), true);
    }

    /**
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int,
     *      int)
     */
    @Override
    public final Iterator<User> iterator(int first, int count) {
        return Collections.synchronizedList(new ArrayList<User>(results)).subList(first, first + count).iterator();
    }

    /**
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    @Override
    public final int size() {
        return results.size();
    }

    /**
     * @param user
     * @return IModel<User>
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    @Override
    public final IModel<User> model(User user) {
        return new DetachableUserModel(user, usersService);
    }

    /**
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    @Override
    public void detach() {
    }

    /**
     * Performs the search
     * 
     * @param request
     * @throws JackWicketException 
     */
    public final void fetchResults(Request request) throws JackWicketException {
        String pattern = request.getParameter("searchData");
        if (StringUtils.isNotBlank(pattern)) {
            results = usersService.find(pattern);
        } else {
            results = usersService.getAll();
        }
    }

}