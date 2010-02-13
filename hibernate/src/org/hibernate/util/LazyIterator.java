//$Id: LazyIterator.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

import java.util.Iterator;
import java.util.Map;

public final class LazyIterator implements Iterator {
	
	private final Map map;
	private Iterator iterator;
	
	private Iterator getIterator() {
		if (iterator==null) {
			iterator = map.values().iterator();
		}
		return iterator;
	}

	public LazyIterator(Map map) {
		this.map = map;
	}
	
	public boolean hasNext() {
		return getIterator().hasNext();
	}

	public Object next() {
		return getIterator().next();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
