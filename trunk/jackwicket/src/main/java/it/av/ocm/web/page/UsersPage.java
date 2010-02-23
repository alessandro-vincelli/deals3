package it.av.ocm.web.page;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.bean.UserProfile;
import it.av.ocm.services.UserProfileService;
import it.av.ocm.services.UserService;
import it.av.ocm.web.data.UsersSortableDataProvider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.MarkupContainer;
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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * The page provides some basic operation on the {@link User} bean.
 * 
 * <ul>
 * <li>{@link Form} to edit and add {@link User} with ajax buttons</li>
 * <li>{@link AjaxFallbackDefaultDataTable} to show and enable operations on the
 * {@link User}</li>
 * <li>{@link PropertyListView} to show a simple list with the previous versions
 * of the {@link User}</li>
 * </ul>
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class UsersPage extends WebPage {

    private static final long serialVersionUID = 1L;
    @SpringBean(name="userService")
    private UserService userService;
    @SpringBean(name="userProfileService")
    private UserProfileService userProfileService;

    private User user;
    private FeedbackPanel feedbackPanel;
    private AjaxFallbackDefaultDataTable<User> usersDataTable;
    private UsersSortableDataProvider dataProvider;
    private Form<User> form;
    private PropertyListView<User> usersVersionsList;
    private UsersVersionsListFrag usersVersionsListFrag;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @throws JackWicketException
     */
    public UsersPage() throws JackWicketException {
        user = new User();
        feedbackPanel = new FeedbackPanel("feedBackPanel");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        add(new NavigationPanel("navigationPanel"));

        form = new Form<User>("userForm", new CompoundPropertyModel<User>(user));
        form.setOutputMarkupId(true);
        form.add(new RequiredTextField<String>("username"));
        form.add(new TextField<String>("password"));
        form.add(new TextField<String>("lastname"));
        form.add(new TextField<String>("firstname"));
        form.add(new TextField<String>("email"));
        form.add(new DropDownChoice<UserProfile>("userProfile", new ArrayList<UserProfile>(userProfileService.getAll()), new UserProfilesList()).setOutputMarkupId(true));
        form.add(new Label("version"));

        form.add(new AjaxLink<User>("buttonClearForm", new Model<User>(user)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                form.setModelObject(new User());
                target.addComponent(form);
            }
        });
        form.add(new SubmitButton("ajax-button", form));
        add(form);

        List<IColumn<User>> columns = new ArrayList<IColumn<User>>();
        columns.add(new AbstractColumn<User>(new Model<String>(new StringResourceModel("datatableactionpanel.actions", this, null).getString())) {
            public void populateItem(Item<ICellPopulator<User>> cellItem, String componentId, IModel<User> model) {
                cellItem.add(new DataTableActionPanel(componentId, model));
            }
        });

        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("username", this, null).getString()), "username"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("password", this, null).getString()), "password"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("firstname", this, null).getString()), "firstname"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("lastname", this, null).getString()), "lastname"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("email", this, null).getString()), "email"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("userProfile", this, null).getString()), "userProfile.name"));
        columns.add(new PropertyColumn<User>(new Model<String>(new StringResourceModel("version", this, null).getString()), "version"));
        dataProvider = new UsersSortableDataProvider(userService);
        refreshDataTable();
        usersDataTable = new AjaxFallbackDefaultDataTable<User>("usersDataTable", columns, dataProvider, 10);
        add(usersDataTable);

        add(new SearchPanel(dataProvider, usersDataTable, "searchPanel", feedbackPanel));

        usersVersionsListFrag = new UsersVersionsListFrag("usersVersionsList", "usersVersionsListId", this);
        add(usersVersionsListFrag);
    }

    private class SubmitButton extends AjaxButton {
        private static final long serialVersionUID = 1L;

        public SubmitButton(String id, Form<User> form) {
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
                User savedUser = userService.save((User) form.getModelObject());
                form.setModelObject(savedUser);
                feedbackPanel.info("User Saved");
                refreshDataTable();
                target.addComponent(usersDataTable);
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

    private class UsersVersionsListFrag extends Fragment {
        private static final long serialVersionUID = 1L;

        public UsersVersionsListFrag(String id, String markupId, MarkupContainer container) throws JackWicketException {
            super(id, markupId, container);
            setOutputMarkupId(true);
            usersVersionsList = new PropertyListView<User>("versions", new ArrayList<User>()) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void populateItem(ListItem<User> item) {
                    item.add(new Label("username"));
                    item.add(new Label("password"));
                    item.add(new Label("firstname"));
                    item.add(new Label("lastname"));
                    item.add(new Label("email"));
                    item.add(new Label("userProfile.name"));
                    item.add(new Label("version"));
                }
            };
            add(usersVersionsList.setOutputMarkupId(true));
        }
    }

    /**
     * Fill with fresh data the repetear
     * 
     * @throws JackWicketException
     */
    public final void refreshUsersVersionsList() {
        try {
            usersVersionsList.setModelObject(userService.getAllRevisions(user.getPath()));
        } catch (JackWicketException e) {
            feedbackPanel.error(e.getMessage());
            getPage().add(feedbackPanel);
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

    public final  Form<User> getForm() {
        return form;
    }

    public final User getUser() {
        return user;
    }

    public final void setUser(User user) {
        this.user = user;
    }

    public final UsersVersionsListFrag getUsersVersionsListFrag() {
        return usersVersionsListFrag;
    }

    public final UserService getUsersServices() {
        return userService;
    }

    public final AjaxFallbackDefaultDataTable<User> getUsersDataTable() {
        return usersDataTable;
    }

    private static class UserProfilesList implements IChoiceRenderer<UserProfile> {
        private static final long serialVersionUID = 1L;
        @Override
        public Object getDisplayValue(UserProfile object) {
            return object.getName();
        }

        @Override
        public String getIdValue(UserProfile object, int index) {
            return object.getUuid();
        }

    }
}
