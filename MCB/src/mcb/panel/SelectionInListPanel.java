package mcb.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import mcb.persistenz.ApplicationData;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.SelectionInList;

public abstract class SelectionInListPanel<T extends Model> extends JPanel {

	private static final long serialVersionUID = -3683947066010395220L;
	private SelectionInList<T> modelliste;
	private JList list;
	private PresentationModel<T> detailModel;

	public SelectionInListPanel() {
		super();
		initialize();
	}

	private void createList() {
		list = BasicComponentFactory.createList(modelliste);
		Dimension preferredSize = list.getPreferredSize();
		preferredSize.width = preferredSize.width + 40;
		setMinimumSize(preferredSize);
	}

	public void createNewAndAdd() {
		ApplicationData.setFilter(ApplicationData.ALLE_FILTER);
		T neu = createNewModel();
		updateModelliste();
		list.setSelectedValue(neu, true);
	}

	protected abstract T createNewModel();

	protected abstract List<T> getContents();

	public PresentationModel<T> getDetailModel() {
		if (detailModel == null) {
			detailModel = new PresentationModel<T>(modelliste);
		}
		return detailModel;
	}

	public boolean hasSelection() {
		return modelliste.getSelection() != null;
	}

	private void initialize() {
		modelliste = new SelectionInList<T>(getContents());
		setLayout(new BorderLayout());
		createList();
		JScrollPane scrollpane = new JScrollPane(list);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollpane, BorderLayout.CENTER);
		if (!modelliste.getList().isEmpty()) {
			modelliste.setSelectionIndex(0);
		}
	}

	protected abstract void loescheObjekt(T objekt);

	public void loescheSelection() {
		loescheObjekt(modelliste.getSelection());
		updateModelliste();
	}

	public void refreshList() {
		list.repaint();
	}

	public void setListEnabled(boolean b) {
		list.setEnabled(b);
	}

	public void updateModelliste() {
		modelliste.setList(getContents());
	}
}
