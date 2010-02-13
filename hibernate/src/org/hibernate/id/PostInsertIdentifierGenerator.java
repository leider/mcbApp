//$Id: PostInsertIdentifierGenerator.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.id;

import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;

/**
 * @author Gavin King
 */
public interface PostInsertIdentifierGenerator extends IdentifierGenerator {
	public InsertGeneratedIdentifierDelegate getInsertGeneratedIdentifierDelegate(
			PostInsertIdentityPersister persister,
	        Dialect dialect,
	        boolean isGetGeneratedKeysEnabled) throws HibernateException;
}
