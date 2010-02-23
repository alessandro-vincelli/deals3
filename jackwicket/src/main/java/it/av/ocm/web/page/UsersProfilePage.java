package it.av.ocm.web.page;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;
import it.av.ocm.web.data.UsersProfileSortableDataProvider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * The page provides some basic operation on the {@link UserProfile} bean.
 * 
 * <ul>
 * <li>{@link Form} to edit and add {@link UserProfile} with ajax buttons</li>
 * <li>{@link AjaxFallbackDefaultDataTable} to show and enable operations on the
 * {@link UserProfile}</li>
 * </ul>
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class UsersProfilePage extends WebPage {

    private static final long serialVersionUID = 1L;
    @SpringBean(name="userProfileService")
    private UserProfileService userProfileService;

    private UserProfile userProfile;
    private FeedbackPanel feedbackPanel;
    private AjaxFallbackDefaultDataTable<UserProfile> usersProfileDataTable;
    private UsersProfileSortableDataProvider dataProvider;
    private Form<UserProfile> form;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @throws JackWicketException
     */
    public UsersProfilePage() throws JackWicketException {
        userProfile = new UserProfile();
        feedbackPanel = new FeedbackPanel("feedBackPanel");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        add(new NavigationPanel("navigationPanel"));

        form = new Form<UserProfile>("userProfileForm", new CompoundPropertyModel<UserProfile>(userProfile));
        form.setOutputMarkupId(true);
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));

        form.add(new AjaxLink<UserProfile>("buttonClearForm", new Model<UserProfile>(userProfile)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                form.setModelObject(new UserProfile());
                target.addComponent(form);
            }
        });
        form.add(new SubmitButton("ajax-button", form));
        add(form);

        List<IColumn<UserProfile>> columns = new ArrayList<IColumn<UserProfile>>();
        columns.add(new AbstractColumn<UserProfile>(new Model<String>(new StringResourceModel("datatableactionpanel.actions", this, null).getString())) {
            public void populateItem(Item<ICellPopulator<UserProfile>> cellItem, String componentId, IModel<UserProfile> model) {
                cellItem.add(new DataTableActionPanelUserProfiles(componentId, model));
            }
        });
        columns.add(new PropertyColumn<UserProfile>(new Model<String>(new StringResourceModel("name", this, null).getString()), "name"));
        columns.add(new PropertyColumn<UserProfile>(new Model<String>(new StringResourceModel("description", this, null).getString()), "description"));
        columns.add(new PropertyColumn<UserProfile>(new Model<String>(new StringResourceModel("version", this, null).getString()), "version"));
        dataProvider = new UsersProfileSortableDataProvider(userProfileService);
        refreshDataTable();
        usersProfileDataTable = new AjaxFallbackDefaultDataTable<UserProfile>("usersDataTable", columns, dataProvider, 10);
        add(usersProfileDataTable);

    }

    private class SubmitButton extends AjaxButton {
        private static final long serialVersionUID = 1L;

        public SubmitButton(String id, Form<UserProfile> form) {
            super(id, form);
        }

        @Override
        protected void onComponentTag(ComponentTag tag) {
            super.onComponentTag(tag);
            if (StringUtils.isEmpty(form.getModelObject().getPath())) {
                tag.getAttributes().put("value", new StringResourceModel("button.create", this, null).getString());
            } else {
                tag.getAttributes().put("value", new StringResourceModel("button.update", this, null).getString());
            }
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form form) {
            try {
                userProfileService.save((UserProfile) form.getModelObject());
                feedbackPanel.info("Profile Saved");
                refreshDataTable();
                target.addComponent(usersProfileDataTable);
                target.addComponent(form);
            } catch (JackWicketException e) {
                feedbackPanel.error("ERROR" + e.getMessage());
            }
            target.addComponent(feedbackPanel);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form form) {
            feedbackPanel.anyErrorMessage();
            target.addComponent(feedbackPanel);
        }
    }

    /**
     * Fill with fresh data the repetear
     * @throws JackWicketException 
     */
    public final void refreshDataTable() throws JackWicketException {
        dataProvider.fetchResults(this.getRequest());
    }

    /**
     * @return the feedbackPanel
     */
    public final FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    public final Form<UserProfile> getForm() {
        return form;
    }

    public final UserProfile getUserProfile() {
        return userProfile;
    }

    public final void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public final UserProfileService getUsersProfileServices() {
        return userProfileService;
    }

    public final AjaxFallbackDefaultDataTable<UserProfile> getUsersProfileDataTable() {
        return usersProfileDataTable;
    }

}
