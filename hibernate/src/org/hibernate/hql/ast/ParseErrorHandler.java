// $Id: ParseErrorHandler.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $

package org.hibernate.hql.ast;

import org.hibernate.QueryException;


/**
 * Defines the behavior of an error handler for the HQL parsers.
 * User: josh
 * Date: Dec 6, 2003
 * Time: 12:20:43 PM
 */
public interface ParseErrorHandler extends ErrorReporter {

	int getErrorCount();

	// --Commented out by Inspection (12/11/04 10:56 AM): int getWarningCount();

	void throwQueryException() throws QueryException;
}
