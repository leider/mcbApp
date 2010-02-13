//$Id: MySQLMyISAMDialect.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.dialect;

/**
 * @author Gavin King
 */
public class MySQLMyISAMDialect extends MySQLDialect {

	public String getTableTypeString() {
		return " type=MyISAM";
	}

	public boolean dropConstraints() {
		return false;
	}

}
