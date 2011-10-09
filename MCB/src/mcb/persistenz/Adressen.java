package mcb.persistenz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mcb.model.Adresse;
import mcb.model.Besuch;
import mcb.persistenz.filter.SelectedFilter;

import com.jgoodies.binding.list.ArrayListModel;

public class Adressen {

	private List<Adresse> adressen = new ArrayListModel<Adresse>();

	public void add(Adresse adresse) {
		if (adresse.isNeu()) {
			adresse.setId(this.nextIdForAdressen());
		}
		this.adressen.add(adresse);
	}

	public List<Adresse> alle() {
		return this.adressen;
	}

	public List<Besuch> getAktuelleBesuche() {
		List<Besuch> result = new ArrayList<Besuch>();
		for (Adresse adresse : this.adressen) {
			if (adresse.getAktuellerBesuch() != null) {
				result.add(adresse.getAktuellerBesuch());
			}
		}
		return result;
	}

	public List<Adresse> getEmailAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		List<Adresse> alleAdressen = this.getFilteredAdressen();
		for (Adresse adresse : alleAdressen) {
			if (adresse.hatGueltigeEmail()) {
				result.add(adresse);
			}
		}
		return result;
	}

	public List<Adresse> getFilteredAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		for (Adresse adresse : this.adressen) {
			if (SelectedFilter.get().matches(adresse)) {
				result.add(adresse);
			}
		}
		Comparator<Adresse> adresseComparator = new Comparator<Adresse>() {

			@Override
			public int compare(Adresse adresse1, Adresse adresse2) {
				return adresse1.getName().toUpperCase().compareTo(adresse2.getName().toUpperCase());
			}

		};
		Collections.sort(result, adresseComparator);
		return result;
	}

	private Long nextIdForAdressen() {
		return IdGenerator.nextIdFor(this.adressen);
	}

	public void remove(Adresse adresse) {
		this.adressen.remove(adresse);
	}

}
