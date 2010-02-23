package it.av.ocm.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Provides some navigation {@link AjaxLink}s
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class NavigationPanel extends Panel {
    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     */
    public NavigationPanel(String id) {
        super(id);
        add(new AjaxLink<String>("goUsersPage") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(UsersPage.class);
                setRedirect(true);
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (getPage().getClass().equals(UsersPage.class)) {
                    tag.getAttributes().put("class", "act");
                }
            }
        });
        add(new AjaxLink<String>("goUsersProfilePage") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(UsersProfilePage.class);
                setRedirect(true);
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (getPage().getClass().equals(UsersProfilePage.class)) {
                    tag.getAttributes().put("class", "act");
                }
            }

        });
    }
}