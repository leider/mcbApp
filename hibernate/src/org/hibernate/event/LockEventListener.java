//$Id: LockEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import org.hibernate.HibernateException;

import java.io.Serializable;

/**
 * Defines the contract for handling of lock events generated from a session.
 *
 * @author Steve Ebersole
 */
public interface LockEventListener extends Serializable {

    /** Handle the given lock event.
     *
     * @param event The lock event to be handled.
     * @throws HibernateException
     */
	public void onLock(LockEvent event) throws HibernateException;
}

