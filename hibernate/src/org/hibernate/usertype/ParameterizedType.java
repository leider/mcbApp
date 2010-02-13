//$Id: ParameterizedType.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.usertype;

import java.util.Properties;

/**
 * Support for parameterizable types. A UserType or CustomUserType may be
 * made parameterizable by implementing this interface. Parameters for a
 * type may be set by using a nested type element for the property element
 * in the mapping file, or by defining a typedef.
 *
 * @author Michael Gloegl
 */
public interface ParameterizedType {

	/**
	 * Gets called by Hibernate to pass the configured type parameters to
	 * the implementation.
	 */
	public void setParameterValues(Properties parameters);
}