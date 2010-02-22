package org.deals.framework.dao;

import org.deals.framework.bean.PageType;


public class HibernateDAOFactory extends DAOFactory {

	@Override
	public WebPageDAO getWebPageDAO() {
		return getWebPageDAO(null);
	}

	@Override
	public WebPageDAO getWebPageDAO(PageType type) {		
		if (type==null)
			return (WebPageDAO)instantiateDAO(WebPageDAOHibernate.class);
		if (type.equals(PageType.SECTION()))
				return (WebPageDAO)instantiateDAO(SectionDAOHibernate.class);
		if (type.equals(PageType.CATEGORY()))
			return (WebPageDAO)instantiateDAO(CategoryDAOHibernate.class);
		if (type.equals(PageType.ITEM()))
			return (WebPageDAO)instantiateDAO(ItemDAOHibernate.class);
		
		// qui ci vorrebbe un'eccezione se il tipo non lo conosco
		// per ora restituisco la WebPageDAO
		return (WebPageDAO)instantiateDAO(WebPageDAOHibernate.class);
	}
		
	
	
	@Override
	public WebPageDAO getSectionDAO() {
		return getWebPageDAO(PageType.SECTION());
	}	
	
	@Override
	public WebPageDAO getCategoryDAO() {
		return getWebPageDAO(PageType.CATEGORY());
	}

	@Override
	public WebPageDAO getItemDAO() {
		return getWebPageDAO(PageType.ITEM());
	}


	@Override
	public PageTypeDAO getPageTypeDAO() {
		return (PageTypeDAO)instantiateDAO(PageTypeDAOHibernate.class);
	}

	
	@Override
	public UserDAO getUserDAO() {
		return (UserDAO)instantiateDAO(UserDAOHibernate.class);
	}
	
	@Override
	public TemplateDAO getTemplateDAO() {
		return (TemplateDAO)instantiateDAO(TemplateDAOHibernate.class);
	}

	@Override
	public PageStateDAO getPageStateDAO() {
		return (PageStateDAO)instantiateDAO(PageStateDAOHibernate.class);
	}

	@Override
	public WebComponentDAO getWebComponentDAO() {
		return (WebComponentDAO)instantiateDAO(WebComponentDAOHibernate.class);
	}
	
	@Override
	public UserProfileDAO getUserProfileDAO() {
		return (UserProfileDAO)instantiateDAO(UserProfileDAOHibernate.class);
	}

	private GenericHibernateDAO instantiateDAO(Class daoClass) {
		try {
			GenericHibernateDAO dao = (GenericHibernateDAO)
											daoClass.newInstance();
			return dao;
		} catch (Exception e) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass,e);
		}
	}

	@Override
	public APageComponentsTypeDAO getAPageComponentsTypeDAO() {
		return (APageComponentsTypeDAO)instantiateDAO(APageComponentsTypeDAOHibernate.class);
	}

	@Override
	public ComponentTypeDAO getComponentTypeDAO() {
		return (ComponentTypeDAO)instantiateDAO(ComponentTypeDAOHibernate.class);
	}

	@Override
	public AProfileSectionsDAO getAProfileSectionsDAO() {
		return (AProfileSectionsDAO)instantiateDAO(AProfileSectionsDAOHibernate.class);
		
	}

	@Override
	public CmsSectionDAO getCmsSectionDAO() {
		return (CmsSectionDAO)instantiateDAO(CmsSectionDAOHibernate.class);
	}
	
	
}
