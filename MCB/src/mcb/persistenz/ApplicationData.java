package mcb.persistenz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mcb.model.Treffen;
import mcb.persistenz.filter.SucheFilter;

import com.jgoodies.binding.list.ArrayListModel;

public class ApplicationData {

	public static final SucheFilter SUCHE_FILTER = new SucheFilter();

	public static List<Treffen> treffen = new ArrayListModel<Treffen>();

	public static void add(Treffen treffen) {
		if (treffen.isNeu()) {
			treffen.setId(ApplicationData.nextIdForTreffen());
		}
		ApplicationData.treffen.add(treffen);
	}

	public static Treffen getAktuellesTreffen() {
		for (Treffen theTreffen : ApplicationData.getAlleTreffen()) {
			if (theTreffen.isAktuell()) {
				return theTreffen;
			}
		}
		return null;
	}

	public static List<Treffen> getAlleTreffen() {
		return ApplicationData.treffen;
	}

	public static Treffen getNeuestesTreffen() {
		if (ApplicationData.getAlleTreffen().isEmpty()) {
			return null;
		}
		List<Treffen> treffenCopy = new ArrayList<Treffen>(ApplicationData.getAlleTreffen());
		Collections.sort(treffenCopy, new Comparator<Treffen>() {

			public int compare(Treffen t1, Treffen t2) {
				return -1 * t1.getErsterTag().compareTo(t2.getErsterTag());
			}
		});
		return treffenCopy.get(0);
	}

	private static Long nextIdForTreffen() {
		return IdGenerator.nextIdFor(ApplicationData.treffen);
	}

}
