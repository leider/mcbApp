// $Id: CollectionSubqueryFactory.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.hql;

import org.hibernate.engine.JoinSequence;
import org.hibernate.sql.JoinFragment;
import org.hibernate.MappingException;
import org.hibernate.QueryException;
import org.hibernate.util.StringHelper;

import java.util.Map;

/**
 * Provides the SQL for collection subqueries.
 * <br>
 * Moved here from PathExpressionParser to make it re-useable.
 * 
 * @author josh
 */
public final class CollectionSubqueryFactory {

	//TODO: refactor to .sql package

	private CollectionSubqueryFactory() {
	}

	public static String createCollectionSubquery(
			JoinSequence joinSequence,
	        Map enabledFilters,
	        String[] columns) {
		try {
			JoinFragment join = joinSequence.toJoinFragment( enabledFilters, true );
			return new StringBuffer( "select " )
					.append( StringHelper.join( ", ", columns ) )
					.append( " from " )
					.append( join.toFromFragmentString().substring( 2 ) )// remove initial ", "
					.append( " where " )
					.append( join.toWhereFragmentString().substring( 5 ) )// remove initial " and "
					.toString();
		}
		catch ( MappingException me ) {
			throw new QueryException( me );
		}
	}
}
