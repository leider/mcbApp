//$Id: MySQL5InnoDBDialect.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.dialect;

/**
 * @author Gavin King, Scott Marlow
 */
public class MySQL5InnoDBDialect extends MySQL5Dialect {

	public boolean supportsCascadeDelete() {
		return true;
	}

	public String getTableTypeString() {
		return " ENGINE=InnoDB";
	}

	public boolean hasSelfReferentialForeignKeyBug() {
		return true;
	}

}
