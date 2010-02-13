//$Id: BytesHelper.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

public final class BytesHelper {

	private BytesHelper() {}

	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
}






