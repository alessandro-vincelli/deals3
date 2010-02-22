package org.deals.framework.factory;

import org.deals.framework.bean.APageComponentsType;
import org.deals.framework.dao.APageComponentsTypeDAO;

public class APageComponentsTypeFactory {
	
	public static APageComponentsTypeDAO apctDao;
	
	public static APageComponentsType getAPageComponentsType(Integer id){
		return apctDao.findById(id, false);
	}
  
	// a private constructor
    private APageComponentsTypeFactory() {
      
    }
    
    // a static factory method; the arguments to this method can be
    // considered the dependencies of the bean that is returned,
    // regardless of how those arguments are actually used.
    public static APageComponentsTypeFactory createInstance (
    		APageComponentsTypeDAO apctDao) {

    	APageComponentsTypeFactory eb = new APageComponentsTypeFactory ();
    	eb.apctDao = apctDao;
        return eb;
    }

	
}

