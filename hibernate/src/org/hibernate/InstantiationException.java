//$Id: InstantiationException.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate;

/**
 * Thrown if Hibernate can't instantiate an entity or component
 * class at runtime.
 *
 * @author Gavin King
 */

public class InstantiationException extends HibernateException {

	private final Class clazz;

	public InstantiationException(String s, Class clazz, Throwable root) {
		super(s, root);
		this.clazz = clazz;
	}

	public InstantiationException(String s, Class clazz) {
		super(s);
		this.clazz = clazz;
	}

	public InstantiationException(String s, Class clazz, Exception e) {
		super(s, e);
		this.clazz = clazz;
	}

	public Class getPersistentClass() {
		return clazz;
	}

	public String getMessage() {
		return super.getMessage() + clazz.getName();
	}

}






