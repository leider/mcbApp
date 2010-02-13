//$Id: Initializable.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.event;

import org.hibernate.cfg.Configuration;

/**
 * An event listener that requires access to mappings to
 * initialize state at initialization time.
 * @author Gavin King
 */
public interface Initializable {
	public void initialize(Configuration cfg);
}
