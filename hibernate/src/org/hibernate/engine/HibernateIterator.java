//$Id: HibernateIterator.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.engine;

import org.hibernate.JDBCException;

import java.util.Iterator;

/**
 * An iterator that may be "closed"
 * @see org.hibernate.Hibernate#close(java.util.Iterator)
 * @author Gavin King
 */
public interface HibernateIterator extends Iterator {
	public void close() throws JDBCException;
}
