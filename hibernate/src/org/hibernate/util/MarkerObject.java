//$Id: MarkerObject.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

/**
 * @author Gavin King
 */
public class MarkerObject {
	private String name;
	
	public MarkerObject(String name) {
		this.name=name;
	}
	public String toString() {
		return name;
	}
}
