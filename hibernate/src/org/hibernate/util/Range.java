//$Id: Range.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

public final class Range {

	public static int[] range(int begin, int length) {
		int[] result = new int[length];
		for ( int i=0; i<length; i++ ) {
			result[i]=begin + i;
		}
		return result;
	}

	private Range() {}
}







