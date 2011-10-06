package mcb.model;

import java.util.Arrays;
import java.util.List;

public class Fehlergruende {

	public static List<String> alleGruende() {
		String[] fehlergruende = { "", //
				"Empfänger existiert nicht", //
				"Mailbox voll", //
				"Email aus Spamgründen nicht akzeptiert" //
		};
		return Arrays.asList(fehlergruende);
	}
}
