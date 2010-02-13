//$Id: TransactionException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

/**
 * Indicates that a transaction could not be begun, committed
 * or rolled back.
 *
 * @see Transaction
 * @author Anton van Straaten
 */

public class TransactionException extends HibernateException {

	public TransactionException(String message, Exception root) {
		super(message,root);
	}

	public TransactionException(String message) {
		super(message);
	}

}






