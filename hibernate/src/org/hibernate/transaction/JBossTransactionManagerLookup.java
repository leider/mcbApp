//$Id: JBossTransactionManagerLookup.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.transaction;

/**
 * A <tt>TransactionManager</tt> lookup strategy for JBoss
 * @author Gavin King
 */
public final class JBossTransactionManagerLookup extends JNDITransactionManagerLookup {

	protected String getName() {
		return "java:/TransactionManager";
	}

	public String getUserTransactionName() {
		return "UserTransaction";
	}

}
