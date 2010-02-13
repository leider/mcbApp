package mcb.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import mcb.panel.ModelMitListePanel;
import mcb.persistenz.filter.AdresseFilter;

import com.jgoodies.binding.beans.Model;

public abstract class SimpleFrame<T extends Model> extends JFrame {

	private static final long serialVersionUID = -3301128307489915655L;

	protected ModelMitListePanel<T> panel;

	private ButtonGroup group = new ButtonGroup();

	public SimpleFrame(String string) {
		super(string);
		initialize();
		packCenterAndShow();
	}

	protected abstract void addExtraMenu(JMenuBar bar);

	protected void addMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu aktionen = new JMenu("Aktionen");
		bar.add(aktionen);
		aktionen.add(panel.getNeuAction());
		aktionen.add(panel.getLoeschenAction());
		aktionen.add(panel.getBearbeitenAction());
		addExtraMenu(bar);
		setJMenuBar(bar);
	}

	protected abstract ModelMitListePanel<T> getPanel();

	private void initialize() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		panel = getPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		addMenu();
	}

	private void packCenterAndShow() {
		pack();
		Dimension paneSize = getSize();
		Dimension screenSize = getToolkit().getScreenSize();
		setLocation((screenSize.width - paneSize.width) / 2, (int) ((screenSize.height - paneSize.height) * 0.45));
		setVisible(true);
	}

	protected JCheckBoxMenuItem radioForFilter(AdresseFilter filter) {
		JCheckBoxMenuItem radioButtonMenuItem = new JCheckBoxMenuItem(new FilterAction<T>(filter, this));
		group.add(radioButtonMenuItem);
		return radioButtonMenuItem;
	}

	public void updateListe() {
		panel.updateListe();
	}

}
