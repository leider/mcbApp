//$Id: EqualsHelper.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

/**
 * @author Gavin King
 */
public final class EqualsHelper {

	public static boolean equals(Object x, Object y) {
		return x==y || ( x!=null && y!=null && x.equals(y) );
	}
	
	private EqualsHelper() {}

}
