//$Id: DefaultPersistOnFlushEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event.def;

import org.hibernate.engine.CascadingAction;

/**
 * When persist is used as the cascade action, persistOnFlush should be used
 * @author Emmanuel Bernard
 */
public class DefaultPersistOnFlushEventListener extends DefaultPersistEventListener {
	protected CascadingAction getCascadeAction() {
		return CascadingAction.PERSIST_ON_FLUSH;
	}
}
