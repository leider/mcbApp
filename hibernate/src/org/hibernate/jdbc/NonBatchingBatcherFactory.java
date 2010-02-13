//$Id: NonBatchingBatcherFactory.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.jdbc;

import org.hibernate.Interceptor;


/**
 * A BatcherFactory implementation which constructs Batcher instances
 * that do not perform batch operations.
 *
 * @author Gavin King
 */
public class NonBatchingBatcherFactory implements BatcherFactory {

	public Batcher createBatcher(ConnectionManager connectionManager, Interceptor interceptor) {
		return new NonBatchingBatcher( connectionManager, interceptor );
	}

}
