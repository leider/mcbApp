// $Id: QueryCacheFactory.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.cache;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Settings;

import java.util.Properties;

/**
 * Defines a factory for query cache instances.  These factories are responsible for
 * creating individual QueryCache instances.
 *
 * @author Steve Ebersole
 */
public interface QueryCacheFactory {

	public QueryCache getQueryCache(
	        String regionName,
	        UpdateTimestampsCache updateTimestampsCache,
			Settings settings,
	        Properties props) 
	throws HibernateException;

}
