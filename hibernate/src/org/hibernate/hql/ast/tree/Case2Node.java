// $Id: Case2Node.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.ast.util.ColumnHelper;
import org.hibernate.type.Type;

import antlr.SemanticException;

/**
 * Represents a case ... when .. then ... else ... end expression in a select.
 *
 * @author Gavin King
 */
public class Case2Node extends AbstractSelectExpression implements SelectExpression {
	
	public Type getDataType() {
		return getFirstThenNode().getDataType();
	}

	private SelectExpression getFirstThenNode() {
		return (SelectExpression) getFirstChild().getNextSibling().getFirstChild().getNextSibling();
	}

	public void setScalarColumnText(int i) throws SemanticException {
		ColumnHelper.generateSingleScalarColumn( this, i );
	}

}
