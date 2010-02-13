//$Id: IdentifierType.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.type;

/**
 * A <tt>Type</tt> that may be used as an identifier.
 * @author Gavin King
 */
public interface IdentifierType extends Type {

	/**
	 * Convert the value from the mapping file to a Java object.
	 * @param xml the value of <tt>discriminator-value</tt> or <tt>unsaved-value</tt> attribute
	 * @return Object
	 * @throws Exception
	 */
	public Object stringToObject(String xml) throws Exception;

}






