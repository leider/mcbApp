//$Id: Criterion.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;


import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;

/**
 * An object-oriented representation of a query criterion that may be used 
 * as a restriction in a <tt>Criteria</tt> query.
 * Built-in criterion types are provided by the <tt>Restrictions</tt> factory 
 * class. This interface might be implemented by application classes that 
 * define custom restriction criteria.
 *
 * @see Restrictions
 * @see org.hibernate.Criteria
 * @author Gavin King
 */
public interface Criterion extends Serializable {

	/**
	 * Render the SQL fragment
	 * @param criteriaQuery
	 * @param alias
	 * @return String
	 * @throws HibernateException
	 */
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException;
	
	/**
	 * Return typed values for all parameters in the rendered SQL fragment
	 * @param criteria TODO
	 * @param criteriaQuery 
	 * @return TypedValue[]
	 * @throws HibernateException
	 */
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException;

}
