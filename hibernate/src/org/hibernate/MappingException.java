//$Id: MappingException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

/**
 * An exception that usually occurs at configuration time, rather
 * than runtime, as a result of something screwy in the O-R mappings.
 *
 * @author Gavin King
 */

public class MappingException extends HibernateException {

	public MappingException(String msg, Throwable root) {
		super( msg, root );
	}

	public MappingException(Throwable root) {
		super(root);
	}

	public MappingException(String s) {
		super(s);
	}

}






