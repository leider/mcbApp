//$Id: TypeMismatchException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

/**
 * Used when a user provided type does not match the expected one
 *
 * @author Emmanuel Bernard
 */
public class TypeMismatchException extends HibernateException {
	public TypeMismatchException(Throwable root) {
		super( root );
	}

	public TypeMismatchException(String s) {
		super( s );
	}

	public TypeMismatchException(String string, Throwable root) {
		super( string, root );
	}
}
