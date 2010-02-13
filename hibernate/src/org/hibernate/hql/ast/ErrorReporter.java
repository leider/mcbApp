// $Id: ErrorReporter.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.hql.ast;

import antlr.RecognitionException;

/**
 * Implementations will report or handle errors invoked by an ANTLR base parser.
 *
 * @author josh Jun 27, 2004 9:49:55 PM
 */
public interface ErrorReporter {
	void reportError(RecognitionException e);

	void reportError(String s);

	void reportWarning(String s);
}
