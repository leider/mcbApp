//$Id: CacheJoinFragment.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.sql;

import org.hibernate.AssertionFailure;

/**
 * A Cach&eacute; dialect join.  Differs from ANSI only in that full outer join
 * is not supported.
 *
 * @author Jeff Miller
 * @author Jonathan Levinson
 */
public class CacheJoinFragment extends ANSIJoinFragment {

	public void addJoin(String tableName, String alias, String[] fkColumns, String[] pkColumns, int joinType, String on) {
		if ( joinType == FULL_JOIN ) {
			throw new AssertionFailure( "Cache does not support full outer joins" );
		}
		super.addJoin( tableName, alias, fkColumns, pkColumns, joinType, on );
	}

}
