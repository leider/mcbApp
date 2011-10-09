package mcb.persistenz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mcb.model.Treffen;

import com.jgoodies.binding.list.ArrayListModel;

public class Treffens {

	private List<Treffen> treffen = new ArrayListModel<Treffen>();

	public void add(Treffen treffen) {
		if (treffen.isNeu()) {
			treffen.setId(this.nextIdForTreffen());
		}
		this.treffen.add(treffen);
	}

	public Treffen getAktuellesTreffen() {
		for (Treffen theTreffen : this.getAlleTreffen()) {
			if (theTreffen.isAktuell()) {
				return theTreffen;
			}
		}
		return null;
	}

	public List<Treffen> getAlleTreffen() {
		return this.treffen;
	}

	public Treffen getNeuestesTreffen() {
		if (this.getAlleTreffen().isEmpty()) {
			return null;
		}
		List<Treffen> treffenCopy = new ArrayList<Treffen>(this.getAlleTreffen());
		Collections.sort(treffenCopy, new Comparator<Treffen>() {

			public int compare(Treffen t1, Treffen t2) {
				return -1 * t1.getErsterTag().compareTo(t2.getErsterTag());
			}
		});
		return treffenCopy.get(0);
	}

	private Long nextIdForTreffen() {
		return IdGenerator.nextIdFor(this.treffen);
	}

	public void remove(Treffen treffen) {
		this.treffen.remove(treffen);
	}

}
