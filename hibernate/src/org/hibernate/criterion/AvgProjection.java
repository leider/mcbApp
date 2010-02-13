//$Id: AvgProjection.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;

/**
 * @author Gavin King
 */
public class AvgProjection extends AggregateProjection {

	public AvgProjection(String propertyName) {
		super("avg", propertyName);
	}
	
	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		return new Type[] { Hibernate.DOUBLE };
	}
}
