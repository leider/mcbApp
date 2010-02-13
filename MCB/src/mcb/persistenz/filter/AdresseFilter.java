package mcb.persistenz.filter;

import mcb.persistenz.Adresse;

public interface AdresseFilter {

	int getKeyMask();

	String getLabel();

	boolean matches(Adresse adresse);
}
