//$Id: QueryExecutionRequestException.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.hql;

import org.hibernate.QueryException;

/**
 * Expecting to execute an illegal operation regarding the query type
 *
 * @author Emmanuel Bernard
 */
public class QueryExecutionRequestException extends QueryException {

	public QueryExecutionRequestException(String message, String queryString) {
		super( message, queryString );
	}
}
