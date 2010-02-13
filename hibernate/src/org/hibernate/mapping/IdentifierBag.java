//$Id: IdentifierBag.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.mapping;

import org.hibernate.type.CollectionType;
import org.hibernate.type.TypeFactory;

/**
 * An <tt>IdentifierBag</tt> has a primary key consisting of
 * just the identifier column
 */
public class IdentifierBag extends IdentifierCollection {

	public IdentifierBag(PersistentClass owner) {
		super(owner);
	}

	public CollectionType getDefaultCollectionType() {
		return TypeFactory.idbag( getRole(), getReferencedPropertyName(), isEmbedded() );
	}

	public Object accept(ValueVisitor visitor) {
		return visitor.accept(this);
	}	
}
