// $Id: Filterable.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.mapping;

/**
 * Defines mapping elements to which filters may be applied.
 *
 * @author Steve Ebersole
 */
public interface Filterable {
	public void addFilter(String name, String condition);

	public java.util.Map getFilterMap();
}
