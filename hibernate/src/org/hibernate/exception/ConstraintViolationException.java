// $Id: ConstraintViolationException.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.exception;

import org.hibernate.JDBCException;

import java.sql.SQLException;

/**
 * Implementation of JDBCException indicating that the requested DML operation
 * resulted in a violation of a defined integrity constraint.
 *
 * @author Steve Ebersole
 */
public class ConstraintViolationException extends JDBCException {

	private String constraintName;

	public ConstraintViolationException(String message, SQLException root, String constraintName) {
		super( message, root );
		this.constraintName = constraintName;
	}

	public ConstraintViolationException(String message, SQLException root, String sql, String constraintName) {
		super( message, root, sql );
		this.constraintName = constraintName;
	}

	/**
	 * Returns the name of the violated constraint, if known.
	 *
	 * @return The name of the violated constraint, or null if not known.
	 */
	public String getConstraintName() {
		return constraintName;
	}
}
