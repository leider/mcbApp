package mcb.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import mcb.panel.ModelMitListePanel;
import mcb.persistenz.filter.AdresseFilter;

import com.jgoodies.binding.beans.Model;

public abstract class SimpleFrame<T extends Model> extends JFrame {

	private static final long serialVersionUID = -3301128307489915655L;

	protected ModelMitListePanel<T> panel;

	private ButtonGroup group = new ButtonGroup();

	public SimpleFrame(String string) {
		super(string);
		this.initialize();
		this.packCenterAndShow();
	}

	protected abstract void addExtraMenu(JMenuBar bar);

	protected void addMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu aktionen = new JMenu("Aktionen");
		bar.add(aktionen);
		aktionen.add(this.panel.getNeuAction());
		aktionen.add(this.panel.getLoeschenAction());
		aktionen.add(this.panel.getBearbeitenAction());
		this.addExtraMenu(bar);
		this.setJMenuBar(bar);
	}

	protected abstract ModelMitListePanel<T> createPanel();

	private void initialize() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.panel = this.createPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.addMenu();
	}

	private void packCenterAndShow() {
		this.pack();
		Dimension paneSize = this.getSize();
		Dimension screenSize = this.getToolkit().getScreenSize();
		this.setLocation((screenSize.width - paneSize.width) / 2, (int) ((screenSize.height - paneSize.height) * 0.45));
		this.setVisible(true);
	}

	protected JCheckBoxMenuItem radioForFilter(AdresseFilter filter) {
		FilterAction<T> filterAction = new FilterAction<T>(filter, this);
		this.panel.addFilterAction(filterAction);
		JCheckBoxMenuItem radioButtonMenuItem = new JCheckBoxMenuItem(filterAction);
		this.group.add(radioButtonMenuItem);
		return radioButtonMenuItem;
	}

	public void updateListe() {
		this.panel.updateListe();
	}

}
