//$Id: HavingParser.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.hql.classic;


/**
 * Parses the having clause of a hibernate query and translates it to an
 * SQL having clause.
 */
public class HavingParser extends WhereParser {

	void appendToken(QueryTranslatorImpl q, String token) {
		q.appendHavingToken( token );
	}

}
