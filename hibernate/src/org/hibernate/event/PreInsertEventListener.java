//$Id: PreInsertEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called before inserting an item in the datastore
 * 
 * @author Gavin King
 */
public interface PreInsertEventListener extends Serializable {
	/**
	 * Return true if the operation should be vetoed
	 */
	public boolean onPreInsert(PreInsertEvent event);
}
