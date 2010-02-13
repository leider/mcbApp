//$Id: Bag.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.mapping;

import org.hibernate.type.CollectionType;
import org.hibernate.type.TypeFactory;

/**
 * A bag permits duplicates, so it has no primary key
 * @author Gavin King
 */
public class Bag extends Collection {

	public Bag(PersistentClass owner) {
		super(owner);
	}

	public CollectionType getDefaultCollectionType() {
		return TypeFactory.bag( getRole(), getReferencedPropertyName(), isEmbedded() );
	}

	void createPrimaryKey() {
		//create an index on the key columns??
	}

	public Object accept(ValueVisitor visitor) {
		return visitor.accept(this);
	}
}
