// $Id: InitializeableNode.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $

package org.hibernate.hql.ast.tree;

/**
 * An interface for initializeable AST nodes.
 */
public interface InitializeableNode {
	/**
	 * Initializes the node with the parameter.
	 *
	 * @param param the initialization parameter.
	 */
	void initialize(Object param);
}
