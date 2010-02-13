//$Id: TransactionManagerLookup.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.transaction;

import java.util.Properties;
import javax.transaction.TransactionManager;

import org.hibernate.HibernateException;

/**
 * Concrete implementations locate and return the JTA
 * <tt>TransactionManager</tt>.
 * @author Gavin King
 */
public interface TransactionManagerLookup {

	/**
	 * Obtain the JTA <tt>TransactionManager</tt>
	 */
	public TransactionManager getTransactionManager(Properties props) throws HibernateException;

	/**
	 * Return the JNDI name of the JTA <tt>UserTransaction</tt>
	 * or <tt>null</tt> (optional operation).
	 */
	public String getUserTransactionName();

}






