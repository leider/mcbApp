package mcb.persistenz.json;

import java.io.IOException;
import java.io.OutputStreamWriter;

public interface DoWithWriter {

	void statement(OutputStreamWriter writer) throws IOException;

}