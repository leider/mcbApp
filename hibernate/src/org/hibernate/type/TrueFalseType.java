//$Id: TrueFalseType.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.type;

/**
 * <tt>true_false</tt>: A type that maps an SQL CHAR(1) to a Java Boolean.
 * @author Gavin King
 */
public class TrueFalseType extends CharBooleanType {

	protected final String getTrueString() {
		return "T";
	}
	protected final String getFalseString() {
		return "F";
	}
	public String getName() { return "true_false"; }

}







