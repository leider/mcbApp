//$Id: EmptyExpression.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;

/**
 * @author Gavin King
 */
public class EmptyExpression extends AbstractEmptinessExpression implements Criterion {

	protected EmptyExpression(String propertyName) {
		super( propertyName );
	}

	protected boolean excludeEmpty() {
		return false;
	}

}
