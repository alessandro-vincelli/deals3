/*
 * Created on Mar 6, 2007
 *
 */
package org.deals.framework.hb;

import org.apache.avalon.framework.component.Component;
import org.hibernate.Session;

public interface PersistenceFactory extends Component {

	 String ROLE = PersistenceFactory.class.getName();

     public Session createSession();
}
