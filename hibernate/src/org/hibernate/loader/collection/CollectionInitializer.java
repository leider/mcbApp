//$Id: CollectionInitializer.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.loader.collection;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;

/**
 * An interface for collection loaders
 * @see BasicCollectionLoader
 * @see OneToManyLoader
 * @author Gavin King
 */
public interface CollectionInitializer {
	/**
	 * Initialize the given collection
	 */
	public void initialize(Serializable id, SessionImplementor session) throws HibernateException;
}






