//$Id: PreLoadEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called before injecting property values into a newly 
 * loaded entity instance.
 *
 * @author Gavin King
 */
public interface PreLoadEventListener extends Serializable {
	public void onPreLoad(PreLoadEvent event);
}
