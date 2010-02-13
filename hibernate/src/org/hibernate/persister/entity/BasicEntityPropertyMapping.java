//$Id: BasicEntityPropertyMapping.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.persister.entity;

import org.hibernate.QueryException;
import org.hibernate.type.Type;

/**
 * @author Gavin King
 */
public class BasicEntityPropertyMapping extends AbstractPropertyMapping {

	private final AbstractEntityPersister persister;

	public BasicEntityPropertyMapping(AbstractEntityPersister persister) {
		this.persister = persister;
	}
	
	public String[] getIdentifierColumnNames() {
		return persister.getIdentifierColumnNames();
	}

	protected String getEntityName() {
		return persister.getEntityName();
	}

	public Type getType() {
		return persister.getType();
	}

	public String[] toColumns(final String alias, final String propertyName) throws QueryException {
		return super.toColumns( 
				persister.generateTableAlias( alias, persister.getSubclassPropertyTableNumber(propertyName) ), 
				propertyName 
			);
	}
	
	
}
