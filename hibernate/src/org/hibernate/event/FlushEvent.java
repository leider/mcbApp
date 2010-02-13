//$Id: FlushEvent.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;


/** 
 * Defines an event class for the flushing of a session.
 *
 * @author Steve Ebersole
 */
public class FlushEvent extends AbstractEvent {
	
	public FlushEvent(EventSource source) {
		super(source);
	}

}
