package it.av.ocm.web.page;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.bean.UserProfile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Provides some {@link AjaxLink} to perform operations on {@link User}
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class DataTableActionPanelUserProfiles extends Panel {
    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     * @param model
     *            model for contact
     */
    public DataTableActionPanelUserProfiles(String id, IModel<UserProfile> model) {
        super(id, model);
        add(new AjaxLink<UserProfile>("select", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                UsersProfilePage page = ((UsersProfilePage) getPage());
                page.setUserProfile(getModelObject());
                page.getFeedbackPanel().info("Profile \"" + getModelObject().getName() + "\" selected");
                target.addComponent(page.getFeedbackPanel());
            }
        });
        add(new AjaxLink<UserProfile>("edit", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                UsersProfilePage page = ((UsersProfilePage) getPage());
                Form<UserProfile> form = page.getForm();
                form.setModelObject(getModelObject());
                target.addComponent(form);
                page.getFeedbackPanel().info("Profile \"" + getModelObject().getName() + "\" loaded and ready to be modified");
                target.addComponent(page.getFeedbackPanel());
            }
        });
        add(new AjaxLink<UserProfile>("remove", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                UsersProfilePage page = ((UsersProfilePage) getPage());
                String profileName = getModelObject().getName();
                page.add(page.getFeedbackPanel());
                try {
                    ((UsersProfilePage) getPage()).getUsersProfileServices().remove(getModelObject());
                    page.refreshDataTable();
                    target.addComponent(page.getUsersProfileDataTable());
                    page.getFeedbackPanel().info("Profile \"" + profileName + "\" removed");
                } catch (JackWicketException e) {
                    page.getFeedbackPanel().error(e.getMessage());
                }
                target.addComponent(page.getFeedbackPanel());
            }
        });
    }
}