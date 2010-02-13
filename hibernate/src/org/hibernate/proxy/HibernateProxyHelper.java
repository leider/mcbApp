//$Id: HibernateProxyHelper.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.proxy;


/**
 * Utility methods for working with proxies. (this class is being phased out)
 * @author Gavin King
 */
public final class HibernateProxyHelper {

	/**
	 * Get the class of an instance or the underlying class
	 * of a proxy (without initializing the proxy!). It is
	 * almost always better to use the entity name!
	 */
	public static Class getClassWithoutInitializingProxy(Object object) {
		if (object instanceof HibernateProxy) {
			HibernateProxy proxy = (HibernateProxy) object;
			LazyInitializer li = proxy.getHibernateLazyInitializer();
			return li.getPersistentClass();
		}
		else {
			return object.getClass();
		}
	}

	private HibernateProxyHelper() {
		//cant instantiate
	}

}






