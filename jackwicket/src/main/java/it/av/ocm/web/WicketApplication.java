package it.av.ocm.web;

import it.av.ocm.web.page.UsersPage;
import it.av.ocm.web.page.UsersProfilePage;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Application object for JackWickte web application.
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class WicketApplication extends WebApplication {
    @SpringBean
    private String configurationType; 

    /**
     * Constructor
     */
    public WicketApplication() {
    }

    @Override
    protected final void init() {
        super.init();
        // THIS LINE IS IMPORTANT - IT INSTALLS THE COMPONENT INJECTOR THAT WILL
        // INJECT NEWLY CREATED COMPONENTS WITH THEIR SPRING DEPENDENCIES
        if(getSpringContext() != null){
            addComponentInstantiationListener(new SpringComponentInjector(this, getSpringContext(), true));
        }
        mount(new HybridUrlCodingStrategy("/users", UsersPage.class));
        mount(new HybridUrlCodingStrategy("/userProfiles", UsersProfilePage.class));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public final Class<UsersPage> getHomePage() {
        return UsersPage.class;
    }

    /**
     * @return WebApplicationContext
     */
    public final WebApplicationContext getSpringContext() {
    	return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    public String getConfigurationType() {
        return this.configurationType;
    }

    public final void setConfigurationType(String configurationType) {
        this.configurationType = configurationType;
    }

}
