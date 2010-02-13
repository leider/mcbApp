// $Id: NativeSQLQueryJoinReturn.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.engine.query.sql;

import java.util.Map;

import org.hibernate.LockMode;

/**
 * Represents a return defined as part of a native sql query which
 * names a fetched role.
 *
 * @author Steve Ebersole
 */
public class NativeSQLQueryJoinReturn extends NativeSQLQueryNonScalarReturn {
	private String ownerAlias;
	private String ownerProperty;

	/**
	 * Construct a return descriptor representing some form of fetch.
	 *
	 * @param alias The result alias
	 * @param ownerAlias The owner's result alias
	 * @param ownerProperty The owner's property representing the thing to be fetched
	 * @param propertyResults Any user-supplied column->property mappings
	 * @param lockMode The lock mode to apply
	 */
	public NativeSQLQueryJoinReturn(
			String alias,
			String ownerAlias,
			String ownerProperty,
			Map propertyResults,
			LockMode lockMode) {
		super( alias, propertyResults, lockMode );
		this.ownerAlias = ownerAlias;
		this.ownerProperty = ownerProperty;
	}

	/**
	 * Retrieve the alias of the owner of this fetched association.
	 *
	 * @return The owner's alias.
	 */
	public String getOwnerAlias() {
		return ownerAlias;
	}

	/**
	 * Retrieve the property name (relative to the owner) which maps to
	 * the association to be fetched.
	 *
	 * @return The property name.
	 */
	public String getOwnerProperty() {
		return ownerProperty;
	}
}
