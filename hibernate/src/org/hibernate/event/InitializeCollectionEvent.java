//$Id: InitializeCollectionEvent.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import org.hibernate.collection.PersistentCollection;

/**
 * An event that occurs when a collection wants to be
 * initialized
 * 
 * @author Gavin King
 */
public class InitializeCollectionEvent extends AbstractEvent {
	
	private final PersistentCollection collection;

	public InitializeCollectionEvent(PersistentCollection collection, EventSource source) {
		super(source);
		this.collection = collection;
	}
	
	public PersistentCollection getCollection() {
		return collection;
	}
}
