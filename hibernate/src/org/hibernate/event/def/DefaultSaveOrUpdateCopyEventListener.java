//$Id: DefaultSaveOrUpdateCopyEventListener.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event.def;

import org.hibernate.engine.CascadingAction;

public class DefaultSaveOrUpdateCopyEventListener extends DefaultMergeEventListener {

	protected CascadingAction getCascadeAction() {
		return CascadingAction.SAVE_UPDATE_COPY;
	}

}
