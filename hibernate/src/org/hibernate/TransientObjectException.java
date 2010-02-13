//$Id: TransientObjectException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

/**
 * Thrown when the user passes a transient instance to a <tt>Session</tt>
 * method that expects a persistent instance.
 *
 * @author Gavin King
 */

public class TransientObjectException extends HibernateException {

	public TransientObjectException(String s) {
		super(s);
	}

}






