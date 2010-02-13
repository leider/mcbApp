// $Id: InvalidPathException.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.hql.ast;

import antlr.SemanticException;

/**
 * Exception thrown when an invalid path is found in a query.
 *
 * @author josh Dec 5, 2004 7:05:34 PM
 */
public class InvalidPathException extends SemanticException {
	public InvalidPathException(String s) {
		super( s );
	}
}
