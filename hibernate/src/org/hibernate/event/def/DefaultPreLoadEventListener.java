//$Id: DefaultPreLoadEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event.def;

import org.hibernate.event.PreLoadEvent;
import org.hibernate.event.PreLoadEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * Called before injecting property values into a newly 
 * loaded entity instance.
 *
 * @author Gavin King
 */
public class DefaultPreLoadEventListener implements PreLoadEventListener {
	
	public void onPreLoad(PreLoadEvent event) {
		EntityPersister persister = event.getPersister();
		event.getSession()
			.getInterceptor()
			.onLoad( 
					event.getEntity(), 
					event.getId(), 
					event.getState(), 
					persister.getPropertyNames(), 
					persister.getPropertyTypes() 
				);
	}
	
}
