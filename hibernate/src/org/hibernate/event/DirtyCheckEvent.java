//$Id: DirtyCheckEvent.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;


/** Defines an event class for the dirty-checking of a session.
 *
 * @author Steve Ebersole
 */
public class DirtyCheckEvent extends FlushEvent {
	
	private boolean dirty;

	public DirtyCheckEvent(EventSource source) {
		super(source);
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

}
