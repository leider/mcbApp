//$Id: EmptyIterator.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.util;

import java.util.Iterator;

/**
 * @author Gavin King
 */
public final class EmptyIterator implements Iterator {

	public static final Iterator INSTANCE = new EmptyIterator();

	public boolean hasNext() {
		return false;
	}

	public Object next() {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	private EmptyIterator() {}

}
