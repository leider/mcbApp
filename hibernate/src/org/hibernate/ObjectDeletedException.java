//$Id: ObjectDeletedException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

import java.io.Serializable;

/**
 * Thrown when the user tries to do something illegal with a deleted
 * object.
 *
 * @author Gavin King
 */
public class ObjectDeletedException extends UnresolvableObjectException {

	public ObjectDeletedException(String message, Serializable identifier, String clazz) {
		super(message, identifier, clazz);
	}

}







