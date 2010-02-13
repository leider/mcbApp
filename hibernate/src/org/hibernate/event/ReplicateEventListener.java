//$Id: ReplicateEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import org.hibernate.HibernateException;

import java.io.Serializable;

/**
 * Defines the contract for handling of replicate events generated from a session.
 *
 * @author Steve Ebersole
 */
public interface ReplicateEventListener extends Serializable {

    /** Handle the given replicate event.
     *
     * @param event The replicate event to be handled.
     * @throws HibernateException
     */
	public void onReplicate(ReplicateEvent event) throws HibernateException;

}
