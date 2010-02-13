//$Id: PostLoadEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Occurs after an an entity instance is fully loaded.
 *
 * @author <a href="mailto:kabir.khan@jboss.org">Kabir Khan</a>
 */
public interface PostLoadEventListener extends Serializable {
	public void onPostLoad(PostLoadEvent event);
}
