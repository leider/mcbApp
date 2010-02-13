//$Id: BatchingBatcherFactory.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.jdbc;

import org.hibernate.Interceptor;


/**
 * A BatcherFactory implementation which constructs Batcher instances
 * capable of actually performing batch operations.
 * 
 * @author Gavin King
 */
public class BatchingBatcherFactory implements BatcherFactory {

	public Batcher createBatcher(ConnectionManager connectionManager, Interceptor interceptor) {
		return new BatchingBatcher( connectionManager, interceptor );
	}

}
