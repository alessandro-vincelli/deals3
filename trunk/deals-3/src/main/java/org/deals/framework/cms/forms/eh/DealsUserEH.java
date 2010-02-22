/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.eh.abstracts.AbstractDealsUserEH;
import org.deals.framework.cms.forms.formbean.AbstractDealsUserBinder;
import org.deals.framework.cms.forms.formbean.DealsUserFB;
import org.deals.framework.cms.forms.wrapper.DealsUserFW;

public class DealsUserEH extends AbstractDealsUserEH{

	private AbstractDealsUserBinder as = null;
	private Logger log = Logger.getLogger(getClass());
	private DealsUserFB fb = null;
	

	@Override
	public void formOnCreate(DealsUserFW f) {
		super.formOnCreate(f);
		as = AbstractDealsUserBinder.getBinder(session);
		try {			
			int userid = -1 ;
			
			if (request.getSitemapParameter("userid") != null){
				if (!request.getSitemapParameter("userid").equals("")){
					userid = Integer.parseInt(request.getSitemapParameter("userid"));
				}
			}
			// 
			if (userid != -1){ 
				fb = as.getFormBean(userid);
			}
			else{
				fb = as.getFormBean(1);
				//fb = as.newFormBean();
			}
			log.debug("________ nome utente" + fb.getBean().getUsUsername());
			f.load(fb);
			// e sti cosi ???
			//f.getSectionTemplate().setSelectionList(hashMapToSelectionList(fb.getTemplateSL(), f.getSectionTemplate()));
			//f.getSectionState().setSelectionList(hashMapToSelectionList(fb.getPageStateSL(), f.getSectionState()));
			
		}/* catch (BindingException e) {
			log.error("ERROR on binding " + e.getMessage());
			e.printStackTrace();
		} catch (ComponentTypeNotFoundException e) {
			log.error("ERROR on binding " + e.getMessage());
			e.printStackTrace();
		}*/
		catch (Exception e) {
			log.error("________ Errore sul form on create" + e.getMessage(), e);
		}
	}


	@Override
	public void usersubmitOnAction(DealsUserFW f, Submit source) {
		// TODO Auto-generated method stub
		super.usersubmitOnAction(f, source);
		
		try {
			log.debug("________SALVO L'UTENTE");
			f.save(fb);
		} catch (BindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}
