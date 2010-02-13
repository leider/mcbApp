// $Id: DisplayableNode.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.hql.ast.tree;

/**
 * Implementors will return additional display text, which will be used
 * by the ASTPrinter to display information (besides the node type and node
 * text).
 */
public interface DisplayableNode {
	/**
	 * Returns additional display text for the AST node.
	 *
	 * @return String - The additional display text.
	 */
	String getDisplayText();
}
