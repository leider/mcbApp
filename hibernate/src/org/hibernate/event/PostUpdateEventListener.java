//$Id: PostUpdateEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called after updating the datastore
 * 
 * @author Gavin King
 */
public interface PostUpdateEventListener extends Serializable {
	public void onPostUpdate(PostUpdateEvent event);
}
