//$Id: BatcherFactory.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.jdbc;

import org.hibernate.Interceptor;


/**
 * Factory for <tt>Batcher</tt> instances.
 * @author Gavin King
 */
public interface BatcherFactory {
	public Batcher createBatcher(ConnectionManager connectionManager, Interceptor interceptor);
}
