package mcb.persistenz.filter;

import mcb.model.Adresse;

public interface AdresseFilter {

	int getKeyMask();

	String getLabel();

	boolean matches(Adresse adresse);
}
