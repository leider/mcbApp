//$Id: PreUpdateEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called before updating the datastore
 * 
 * @author Gavin King
 */
public interface PreUpdateEventListener extends Serializable {
	/**
	 * Return true if the operation should be vetoed
	 */
	public boolean onPreUpdate(PreUpdateEvent event);
}
