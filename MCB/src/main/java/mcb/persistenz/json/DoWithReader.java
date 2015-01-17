package mcb.persistenz.json;

import java.io.BufferedReader;
import java.io.IOException;

public interface DoWithReader {

  void statement(BufferedReader reader) throws IOException;

}
