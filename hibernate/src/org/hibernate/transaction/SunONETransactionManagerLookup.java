//$Id: SunONETransactionManagerLookup.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.transaction;

/**
 * TransactionManager lookup strategy for Sun ONE Application Server 7 and above
 * 
 * @author Robert Davidson, Sanjeev Krishnan
 * @author Emmanuel Bernard
 */
public class SunONETransactionManagerLookup extends JNDITransactionManagerLookup {

	protected String getName() {
		return "java:appserver/TransactionManager";
	}

	public String getUserTransactionName() {
		return "java:comp/UserTransaction";
	}
	
}
