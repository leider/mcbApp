package mcb.frame;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import mcb.mail.MailSender;
import mcb.mail.SendCompleteListener;
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

public class AdresseFrame extends SimpleFrame<Adresse> implements MatchesAlleListener, MatchesSucheListener,
		SendCompleteListener {

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
				AdresseFrame.this.exportieren(true);
			}
		};

		Action exportAuswahlAction = new AbstractAction("Export Auswahl") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				AdresseFrame.this.exportieren(false);
			}
		};

		Action emailAdressenAusgeben = new AbstractAction("Verschicke Emails...") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				AdresseFrame.this.emailAdressen();
			}
		};

		Action importAction = new AbstractAction("Import Alle") {

			private static final long serialVersionUID = 8568897588247326614L;

			public void actionPerformed(ActionEvent e) {
				AdresseFrame.this.importieren();
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

		this.alle = this.radioForFilter(ApplicationData.ALLE_FILTER);
		ApplicationData.ALLE_FILTER.setMatchesListener(this);
		this.alle.setSelected(true);
		filter.add(this.alle);
		this.suche = this.radioForFilter(ApplicationData.SUCHE_FILTER);
		ApplicationData.SUCHE_FILTER.setMatchesListener(this);
		filter.add(this.suche);
		filter.addSeparator();
		filter.add(this.radioForFilter(new DeutschlandFilter()));
		filter.add(this.radioForFilter(new AuslandFilter()));
		filter.addSeparator();
		filter.add(this.radioForFilter(new EmailKaputtFilter()));
		filter.addSeparator();
		filter.add(this.radioForFilter(new GemeldeteFilter()));
		filter.add(this.radioForFilter(new NichtGemeldeteFilter()));
		filter.addSeparator();
		filter.add(this.radioForFilter(new EinladungEmailFilter()));
		filter.add(this.radioForFilter(new EinladungPostFilter()));
		filter.add(this.radioForFilter(new KeineEinladungFilter()));
	}

	private String createEmailConfirmationMessage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Es wird eine Einladung verschickt an: ");
		List<Adresse> emailAdressen = ApplicationData.getEmailAdressen();
		for (Adresse adresse : emailAdressen) {
			buffer.append(adresse.getEmail());
			buffer.append(", ");
		}
		return buffer.toString();
	}

	@Override
	public void dispose() {
		try {
			HibernateStarter.stopHibernate();
		} catch (SQLException e) {
			// so what?
		}
		super.dispose();
	}

	protected void emailAdressen() {
		String message = this.createEmailConfirmationMessage();
		int sendReally = JOptionPane.showConfirmDialog(this, message);
		if (sendReally == JOptionPane.OK_OPTION) {
			SwingUtilities.invokeLater(new MailSender(this));
		}
	}

	protected void exportieren(boolean alleAdressen) {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			ApplicationData.exportAdressen(chooser.getSelectedFile(), alleAdressen);
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
		this.alle.setSelected(true);
	}

	public void matchesSuchePerformed() {
		this.suche.setSelected(true);
	}

	@Override
	public void messagesSent() {
		JOptionPane.showMessageDialog(this, "Mails wurden gesendet.");
	}

}
