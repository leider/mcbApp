//$Id: NotEmptyExpression.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;

/**
 * @author Gavin King
 */
public class NotEmptyExpression extends AbstractEmptinessExpression implements Criterion {

	protected NotEmptyExpression(String propertyName) {
		super( propertyName );
	}

	protected boolean excludeEmpty() {
		return true;
	}

}
