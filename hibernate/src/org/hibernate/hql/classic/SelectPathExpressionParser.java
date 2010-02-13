//$Id: SelectPathExpressionParser.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.hql.classic;

import org.hibernate.QueryException;

public class SelectPathExpressionParser extends PathExpressionParser {

	public void end(QueryTranslatorImpl q) throws QueryException {
		if ( getCurrentProperty() != null && !q.isShallowQuery() ) {
			// "finish off" the join
			token( ".", q );
			token( null, q );
		}
		super.end( q );
	}

	protected void setExpectingCollectionIndex() throws QueryException {
		throw new QueryException( "illegal syntax near collection-valued path expression in select: "  + getCollectionName() );
	}

	public String getSelectName() {
		return getCurrentName();
	}
}







