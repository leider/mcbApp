//$Id: ExistsSubqueryExpression.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;

import org.hibernate.Criteria;

/**
 * @author Gavin King
 */
public class ExistsSubqueryExpression extends SubqueryExpression {

	protected String toLeftSqlString(Criteria criteria, CriteriaQuery outerQuery) {
		return "";
	}
	
	protected ExistsSubqueryExpression(String quantifier, DetachedCriteria dc) {
		super(null, quantifier, dc);
	}
}
