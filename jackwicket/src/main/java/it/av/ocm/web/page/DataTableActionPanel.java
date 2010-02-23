package it.av.ocm.web.page;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Provides some {@link AjaxLink} to perform operations on the {@link User}
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class DataTableActionPanel extends Panel {
    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     * @param model
     *            model for contact
     */
    public DataTableActionPanel(String id, IModel<User> model) {
        super(id, model);
        add(new AjaxLink<User>("select", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                UsersPage page = ((UsersPage) getPage());
                page.setUser(getModelObject());
                page.refreshUsersVersionsList();
                target.addComponent(page.getUsersVersionsListFrag());
            }
        });
        add(new AjaxLink<User>("edit", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                Form<User> form = ((UsersPage) getPage()).getForm();
                form.setModelObject(getModelObject());
                target.addComponent(form);
            }
        });
        add(new AjaxLink<User>("remove", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                UsersPage page = ((UsersPage) getPage());
                String userName = getModelObject().getFirstname() + " " + getModelObject().getLastname();
                try {
                    page.getUsersServices().remove(getModelObject());
                    page.refreshDataTable();
                    target.addComponent(page.getUsersDataTable());
                    page.getFeedbackPanel().info("User \"" + userName + "\" removed");
                } catch (JackWicketException e) {
                    page.getFeedbackPanel().error(e.getMessage());
                }
                target.addComponent(page.getFeedbackPanel());
            }
        });
    }
}