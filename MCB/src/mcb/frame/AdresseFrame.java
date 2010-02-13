package mcb.frame;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import mcb.panel.AdresseMitListePanel;
import mcb.persistenz.Adresse;
import mcb.persistenz.ApplicationData;
import mcb.persistenz.HibernateStarter;
import mcb.persistenz.filter.AuslandFilter;
import mcb.persistenz.filter.DeutschlandFilter;
import mcb.persistenz.filter.EinladungEmailFilter;
import mcb.persistenz.filter.EinladungPostFilter;
import mcb.persistenz.filter.EmailKaputtFilter;
import mcb.persistenz.filter.GemeldeteFilter;
import mcb.persistenz.filter.KeineEinladungFilter;
import mcb.persistenz.filter.MatchesAlleListener;
import mcb.persistenz.filter.MatchesSucheListener;
import mcb.persistenz.filter.NichtGemeldeteFilter;

public class AdresseFrame extends SimpleFrame<Adresse> implements MatchesAlleListener, MatchesSucheListener {

	private static final long serialVersionUID = -4920258782523646842L;
	private JCheckBoxMenuItem alle;
	private JCheckBoxMenuItem suche;

	public AdresseFrame() {
		super("MCB");
	}

	@Override
	protected void addExtraMenu(JMenuBar bar) {
		Action exportAction = new AbstractAction("Export Alle") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				exportieren(true);
			}
		};

		Action exportAuswahlAction = new AbstractAction("Export Auswahl") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				exportieren(false);
			}
		};

		Action emailAdressenAusgeben = new AbstractAction("Email Adressen") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				emailAdressen();
			}
		};

		Action importAction = new AbstractAction("Import Alle") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				importieren();
			}
		};

		Action treffenAction = new AbstractAction("Treffen bearbeiten...") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				new TreffenFrame();
			}
		};

		JMenu admin = new JMenu("Administration");
		bar.add(admin);
		admin.add(exportAction);
		admin.add(exportAuswahlAction);
		admin.add(emailAdressenAusgeben);
		admin.add(importAction);
		admin.addSeparator();
		admin.add(treffenAction);

		JMenu filter = new JMenu("Filter");
		bar.add(filter);

		alle = radioForFilter(ApplicationData.ALLE_FILTER);
		ApplicationData.ALLE_FILTER.setMatchesListener(this);
		alle.setSelected(true);
		filter.add(alle);
		suche = radioForFilter(ApplicationData.SUCHE_FILTER);
		ApplicationData.SUCHE_FILTER.setMatchesListener(this);
		filter.add(suche);
		filter.addSeparator();
		filter.add(radioForFilter(new DeutschlandFilter()));
		filter.add(radioForFilter(new AuslandFilter()));
		filter.addSeparator();
		filter.add(radioForFilter(new EmailKaputtFilter()));
		filter.addSeparator();
		filter.add(radioForFilter(new GemeldeteFilter()));
		filter.add(radioForFilter(new NichtGemeldeteFilter()));
		filter.addSeparator();
		filter.add(radioForFilter(new EinladungEmailFilter()));
		filter.add(radioForFilter(new EinladungPostFilter()));
		filter.add(radioForFilter(new KeineEinladungFilter()));
	}

	@Override
	public void dispose() {
		try {
			HibernateStarter.stopHibernate();
		} catch (SQLException e) {
		}
		super.dispose();
	}

	protected void emailAdressen() {
		ApplicationData.sendMail();
	}

	protected void exportieren(boolean alle) {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			ApplicationData.exportAdressen(chooser.getSelectedFile(), alle);
		}
	}

	@Override
	protected AdresseMitListePanel getPanel() {
		return new AdresseMitListePanel();
	}

	protected void importieren() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			ApplicationData.importiere(chooser.getSelectedFile());
		}
	}

	public void matchesAllePerformed() {
		alle.setSelected(true);
	}

	public void matchesSuchePerformed() {
		suche.setSelected(true);
	}

}
