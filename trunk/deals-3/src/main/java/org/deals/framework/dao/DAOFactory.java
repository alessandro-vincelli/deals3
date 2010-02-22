package org.deals.framework.dao;

import org.deals.framework.bean.PageType;


public abstract class DAOFactory {

	public static Class HIBERNATE = HibernateDAOFactory.class;	
	
	public static DAOFactory instance(Class factory) {
		try {
			return (DAOFactory)factory.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException(
					"Could not create DAOFactory: " + factory);
		}
	}
			
	// Add your DAO interfaces here
	public abstract WebPageDAO getWebPageDAO();
	public abstract WebPageDAO getWebPageDAO(PageType type);
	public abstract WebPageDAO getSectionDAO();
	public abstract WebPageDAO getCategoryDAO();
	public abstract WebPageDAO getItemDAO();	
	
	public abstract PageTypeDAO getPageTypeDAO();
	public abstract PageStateDAO getPageStateDAO();
	public abstract TemplateDAO getTemplateDAO();
	
	public abstract UserDAO getUserDAO();
	public abstract WebComponentDAO getWebComponentDAO();
	
	public abstract UserProfileDAO getUserProfileDAO();
	public abstract APageComponentsTypeDAO getAPageComponentsTypeDAO();
	public abstract ComponentTypeDAO getComponentTypeDAO();
	public abstract AProfileSectionsDAO getAProfileSectionsDAO();
	public abstract CmsSectionDAO getCmsSectionDAO();
	
	
}
