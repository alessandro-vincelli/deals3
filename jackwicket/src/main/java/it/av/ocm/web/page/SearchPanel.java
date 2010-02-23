package it.av.ocm.web.page;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.web.data.UsersSortableDataProvider;

import org.apache.wicket.IClusterable;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.time.Duration;

/**
 * The panel provides the search form for the {@link User}
 * 
 * @author Alessandro Vincelli
 * 
 */
public class SearchPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private SearchBean searchBean = new SearchBean();

    /**
     * Constructor
     * 
     * @param dataProvider
     * @param dataTable
     * @param id
     * @param feedbackPanel
     */
    public SearchPanel(final UsersSortableDataProvider dataProvider, final AjaxFallbackDefaultDataTable dataTable, String id, final FeedbackPanel feedbackPanel) {
        super(id);
        Form<String> form = new Form<String>("searchForm", new CompoundPropertyModel(searchBean));
        add(form);
        form.setOutputMarkupId(true);
        FormComponent<String> fc;
        fc = new TextField<String>("searchData");
        form.add(fc);
        // event and throttle it down to once per second
        AjaxFormValidatingBehavior.addToAllFormComponents(form, "onkeyup", Duration.ONE_SECOND);

        form.add(new AjaxButton("ajax-button", form) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                try {
                    dataProvider.fetchResults(this.getRequest());
                }catch (JackWicketException e) {
                    feedbackPanel.error(e.getMessage());
                }
                target.addComponent(dataTable);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form form) {
                target.addComponent(dataTable);
            }
        });
    }

    /**
     * 
     * Simple Bean to store the Form data
     * 
     * @author Alessandro Vincelli
     * 
     */
    public static class SearchBean implements IClusterable {
        private static final long serialVersionUID = 1L;
        private String searchData;

        /**
         * @return the searchData
         */
        public final String getSearchData() {
            return searchData;
        }

        /**
         * @param searchData
         *            the searchData to set
         */
        public final void setSearchData(String searchData) {
            this.searchData = searchData;
        }
    }
}
