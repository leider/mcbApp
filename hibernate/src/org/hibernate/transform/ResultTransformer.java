//$Id: ResultTransformer.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.transform;

import java.io.Serializable;
import java.util.List;

/**
 * Implementors define a strategy for transforming criteria query
 * results into the actual application-visible query result list.
 * @see org.hibernate.Criteria#setResultTransformer(ResultTransformer)
 * @author Gavin King
 */
public interface ResultTransformer extends Serializable {
	public Object transformTuple(Object[] tuple, String[] aliases);
	public List transformList(List collection);
}
