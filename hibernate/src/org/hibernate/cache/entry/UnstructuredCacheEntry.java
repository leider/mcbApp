//$Id: UnstructuredCacheEntry.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.cache.entry;

import org.hibernate.engine.SessionFactoryImplementor;


/**
 * @author Gavin King
 */
public class UnstructuredCacheEntry implements CacheEntryStructure {

	public Object structure(Object item) {
		return item;
	}

	public Object destructure(Object map, SessionFactoryImplementor factory) {
		return map;
	}

}
