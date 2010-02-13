//$Id: PostInsertEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called after insterting an item in the datastore
 * 
 * @author Gavin King
 */
public interface PostInsertEventListener extends Serializable {
	public void onPostInsert(PostInsertEvent event);
}
