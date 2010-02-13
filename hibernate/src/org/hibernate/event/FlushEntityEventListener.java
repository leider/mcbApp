//$Id: FlushEntityEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import java.io.Serializable;

import org.hibernate.HibernateException;

/**
 * @author Gavin King
 */
public interface FlushEntityEventListener extends Serializable {
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException;
}
