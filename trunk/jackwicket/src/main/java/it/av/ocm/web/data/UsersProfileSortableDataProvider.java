package it.av.ocm.web.data;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;

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
public class UsersProfileSortableDataProvider extends SortableDataProvider<UserProfile> {
    private static final long serialVersionUID = 1L;
    private UserProfileService userProfileService;
    private Collection<UserProfile> results;

    /**
     * 
     * @param userProfileService
     */
    public UsersProfileSortableDataProvider(UserProfileService userProfileService) {
        super();
        this.userProfileService = userProfileService;
        results = new ArrayList<UserProfile>();
        // setSort(LightVac.SortedFieldNames.dateTime.value(), true);
    }

    /**
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int,
     *      int)
     */
    @Override
    public final Iterator<UserProfile> iterator(int first, int count) {
        return Collections.synchronizedList(new ArrayList<UserProfile>(results)).subList(first, first + count).iterator();
    }

    /**
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    @Override
    public final int size() {
        return results.size();
    }

    @Override
    public final IModel<UserProfile> model(UserProfile userProfile) {
        return new DetachableUserProfileModel(userProfile, userProfileService);
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
            results = userProfileService.find(pattern);
        } else {
            results = userProfileService.getAll();
        }
    }

}