//$Id: SQLLoadable.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.persister.entity;

import org.hibernate.type.Type;

/**
 * A class persister that supports queries expressed in the
 * platform native SQL dialect
 *
 * @author Gavin King, Max Andersen
 */
public interface SQLLoadable extends Loadable {

	/**
	 * Return the column alias names used to persist/query the named property of the class or a subclass (optional operation).
	 */
	public String[] getSubclassPropertyColumnAliases(String propertyName, String suffix);

	/**
	 * Return the column names used to persist/query the named property of the class or a subclass (optional operation).
	 */
	public String[] getSubclassPropertyColumnNames(String propertyName);
	
	/**
	 * All columns to select, when loading.
	 */
	public String selectFragment(String alias, String suffix);

	/**
	 * Get the type
	 */
	public Type getType();

	

}
