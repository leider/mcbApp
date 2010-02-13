//$Id: ReaderInputStream.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.lob;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Exposes a <tt>Reader</tt> as an <tt>InputStream</tt>
 * @author Gavin King
 */
public class ReaderInputStream extends InputStream {
	
	private Reader reader;
	
	public ReaderInputStream(Reader reader) {
		this.reader = reader;
	}
	
	public int read() throws IOException {
		return reader.read();
	}
	
}
