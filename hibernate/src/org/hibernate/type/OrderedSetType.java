//$Id: OrderedSetType.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.type;

import org.hibernate.util.LinkedHashCollectionHelper;

public class OrderedSetType extends SetType {

	public OrderedSetType(String role, String propertyRef, boolean isEmbeddedInXML) {
		super( role, propertyRef, isEmbeddedInXML );
	}

	public Object instantiate(int anticipatedSize) {
		return LinkedHashCollectionHelper.createLinkedHashSet( anticipatedSize );
	}

}
