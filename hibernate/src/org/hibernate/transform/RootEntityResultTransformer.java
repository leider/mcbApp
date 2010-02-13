//$Id: RootEntityResultTransformer.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.transform;

import java.util.List;

/**
 * @author Gavin King
 */
public class RootEntityResultTransformer implements ResultTransformer {

	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple[ tuple.length-1 ];
	}

	public List transformList(List collection) {
		return collection;
	}

}
