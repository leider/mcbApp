//$Id: DisjunctionFragment.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.sql;

/**
 * A disjunctive string of conditions
 * @author Gavin King
 */
public class DisjunctionFragment {

	private StringBuffer buffer = new StringBuffer();

	public DisjunctionFragment addCondition(ConditionFragment fragment) {
		if ( buffer.length()>0 ) buffer.append(" or ");
		buffer.append("(")
			.append( fragment.toFragmentString() )
			.append(")");
		return this;
	}

	public String toFragmentString() {
		return buffer.toString();
	}
}
