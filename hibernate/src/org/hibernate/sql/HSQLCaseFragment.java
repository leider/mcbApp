//$Id: HSQLCaseFragment.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.sql;

import java.util.Iterator;
import java.util.Map;

/**
 * The HSQL CASEWHEN function.
 * <br>
 * <code>casewhen(..., ..., ...) as ...</code>
 * <br>
 * @author Wolfgang Jung
 */
public class HSQLCaseFragment extends CaseFragment {

	public String toFragmentString() {
		StringBuffer buf = new StringBuffer( cases.size() * 15 + 10 );
		StringBuffer buf2 = new StringBuffer( cases.size() );

		Iterator iter = cases.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = (Map.Entry) iter.next();
			buf.append(" casewhen(")
				.append( me.getKey() )
				.append(" is not null")
				.append(", ")
				.append( me.getValue() )
				.append(", ");
			buf2.append(")");
		}

		buf.append("-1"); //null caused some problems
		buf.append( buf2.toString() );
		if ( returnColumnName!=null ) {
			buf.append(" as ")
				.append(returnColumnName);
		}
		return buf.toString();
	}
}

