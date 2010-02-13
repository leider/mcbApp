//$Id: LazyInitializationException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

import org.apache.commons.logging.LogFactory;

/**
 * Indicates access to unfetched data outside of a session context.
 * For example, when an uninitialized proxy or collection is accessed 
 * after the session was closed.
 *
 * @see Hibernate#initialize(java.lang.Object)
 * @see Hibernate#isInitialized(java.lang.Object)
 * @author Gavin King
 */
public class LazyInitializationException extends HibernateException {

	public LazyInitializationException(String msg) {
		super(msg);
		LogFactory.getLog(LazyInitializationException.class).error(msg, this);
	}

}






