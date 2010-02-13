//$Id: MapLazyInitializer.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.proxy.map;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.proxy.AbstractLazyInitializer;

/**
 * Lazy initializer for "dynamic-map" entity representations.
 *
 * @author Gavin King
 */
public class MapLazyInitializer extends AbstractLazyInitializer implements Serializable {

	MapLazyInitializer(String entityName, Serializable id, SessionImplementor session) {
		super(entityName, id, session);
	}

	public Map getMap() {
		return (Map) getImplementation();
	}

	public Class getPersistentClass() {
		throw new UnsupportedOperationException("dynamic-map entity representation");
	}

}
