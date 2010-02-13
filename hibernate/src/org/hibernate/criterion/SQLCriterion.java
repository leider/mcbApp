//$Id: SQLCriterion.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.criterion;


import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;
import org.hibernate.type.Type;
import org.hibernate.util.StringHelper;

/**
 * A SQL fragment. The string {alias} will be replaced by the
 * alias of the root entity.
 */
public class SQLCriterion implements Criterion {

	private final String sql;
	private final TypedValue[] typedValues;

	public String toSqlString(
		Criteria criteria,
		CriteriaQuery criteriaQuery)
	throws HibernateException {
		return StringHelper.replace( sql, "{alias}", criteriaQuery.getSQLAlias(criteria) );
	}

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		return typedValues;
	}

	public String toString() {
		return sql;
	}

	protected SQLCriterion(String sql, Object[] values, Type[] types) {
		this.sql = sql;
		typedValues = new TypedValue[values.length];
		for ( int i=0; i<typedValues.length; i++ ) {
			typedValues[i] = new TypedValue( types[i], values[i], EntityMode.POJO );
		}
	}

}
