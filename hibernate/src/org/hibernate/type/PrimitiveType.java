//$Id: PrimitiveType.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.type;

import java.io.Serializable;

/**
 * Superclass of primitive / primitive wrapper types.
 * @author Gavin King
 */
public abstract class PrimitiveType extends ImmutableType implements LiteralType {

	public abstract Class getPrimitiveClass();

	public String toString(Object value) {
		return value.toString();
	}
	
	public abstract Serializable getDefaultValue();

}





