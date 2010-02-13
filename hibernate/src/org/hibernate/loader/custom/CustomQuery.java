//$Id: CustomQuery.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.loader.custom;

import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Extension point allowing any SQL query with named and positional parameters
 * to be executed by Hibernate, returning managed entities, collections and
 * simple scalar values.
 * 
 * @author Gavin King
 * @author Steve Ebersole
 */
public interface CustomQuery {
	/**
	 * The SQL query string to be performed.
	 *
	 * @return The SQL statement string.
	 */
	public String getSQL();

	/**
	 * Any query spaces to apply to the query execution.  Query spaces are
	 * used in Hibernate's auto-flushing mechanism to determine which
	 * entities need to be checked for pending changes.
	 *
	 * @return The query spaces
	 */
	public Set getQuerySpaces();

	/**
	 * A map representing positions within the supplied {@link #getSQL query} to
	 * which we need to bind named parameters.
	 * <p/>
	 * Optional, may return null if no named parameters.
	 * <p/>
	 * The structure of the returned map (if one) as follows:<ol>
	 * <li>The keys into the map are the named parameter names</li>
	 * <li>The corresponding value is either an {@link Integer} if the
	 * parameter occurs only once in the query; or a List of Integers if the
	 * parameter occurs more than once</li>
	 * </ol>
	 */
	public Map getNamedParameterBindPoints();

	/**
	 * A collection of {@link Return descriptors} describing the
	 * JDBC result set to be expected and how to map this result set.
	 *
	 * @return List of return descriptors.
	 */
	public List getCustomQueryReturns();
}
