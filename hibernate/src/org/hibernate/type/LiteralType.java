//$Id: LiteralType.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.type;

import org.hibernate.dialect.Dialect;

/**
 * A type that may appear as an SQL literal
 * @author Gavin King
 */
public interface LiteralType {
	/**
	 * String representation of the value, suitable for embedding in
	 * an SQL statement.
	 * @param value
	 * @param dialect
	 * @return String the value, as it appears in a SQL query
	 * @throws Exception
	 */
	public String objectToSQLString(Object value, Dialect dialect) throws Exception;

}






