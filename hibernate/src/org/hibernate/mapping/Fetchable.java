//$Id: Fetchable.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.mapping;

import org.hibernate.FetchMode;

/**
 * Any mapping with an outer-join attribute
 * @author Gavin King
 */
public interface Fetchable {
	public FetchMode getFetchMode();
	public void setFetchMode(FetchMode joinedFetch);
	public boolean isLazy();
	public void setLazy(boolean lazy);
}
