/*
 * Created on Nov 7, 2006
 *
 */
package org.deals.framework.site.apples;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.cocoon.components.flow.apples.AppleRequest;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.components.flow.apples.StatelessAppleController;
import org.apache.cocoon.environment.Request;
import org.apache.log4j.Logger;
import org.deals.framework.util.SafeUtil;

public class Go2Page implements StatelessAppleController, Serviceable {
	private ServiceManager serviceManager;
	Logger log = Logger.getLogger(getClass());


	public void service(ServiceManager serviceManager) throws ServiceException {
		this.serviceManager = serviceManager;
	}

	public void process(AppleRequest appleRequest, AppleResponse appleResponse)
			throws Exception {
		Request request = appleRequest.getCocoonRequest();
		
		if (request.getMethod().equals("POST")) {
			
			String href = request.getParameter("href");
			
			log.error("__________href"+ href);
					
			if (!SafeUtil.isNullOrEmpty(href)){
				appleResponse.redirectTo(href);
			}
			else{
				appleResponse.redirectTo(request.getContextPath());
			}
		} else {
			throw new Exception("Unexpected HTTP method: "
					+ request.getMethod());
		}
	}

}
