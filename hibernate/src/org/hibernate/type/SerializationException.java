//$Id: SerializationException.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.type;

import org.hibernate.HibernateException;

/**
 * Thrown when a property cannot be serializaed/deserialized
 * @author Gavin King
 */
public class SerializationException extends HibernateException {

	public SerializationException(String message, Exception root) {
		super(message, root);
	}

}






