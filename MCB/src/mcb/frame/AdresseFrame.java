package mcb.frame;

import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import mcb.frame.actions.EmailAction;
import mcb.frame.actions.ExcelExportAction;
import mcb.frame.actions.OpenTreffenAction;
import mcb.mail.SendCompleteListener;
import mcb.model.Adresse;
import mcb.model.Treffen;
import mcb.panel.AdresseMitListePanel;
import mcb.persistenz.PersistenceStore;
import mcb.persistenz.filter.AlleFilter;
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
import mcb.persistenz.filter.SucheFilter;

public class AdresseFrame extends SimpleFrame<Adresse> implements MatchesAlleListener, MatchesSucheListener, SendCompleteListener {

	private static final long serialVersionUID = -4920258782523646842L;
	private JCheckBoxMenuItem alle;
	private JCheckBoxMenuItem suche;

	public AdresseFrame(PersistenceStore persistenceStore) {
		super("MCB", persistenceStore);
	}

	@Override
	protected void addExtraMenu(JMenuBar bar) {
		bar.add(this.createAdminMenu());
		bar.add(this.createFilterMenu());
	}

	private JMenu createAdminMenu() {
		JMenu admin = new JMenu("Administration");
		admin.add(new ExcelExportAction(this, "Export Auswahl nach Excel", this.persistenceStore));
		admin.add(new EmailAction(this, "Verschicke Emails..."));
		admin.addSeparator();
		admin.add(new OpenTreffenAction("Treffen bearbeiten...", this.persistenceStore));
		return admin;
	}

	private JMenu createFilterMenu() {
		JMenu filter = new JMenu("Filter");
		this.alle = this.radioForFilter(AlleFilter.getInstance());
		filter.add(this.alle);
		this.suche = this.radioForFilter(SucheFilter.getInstance());
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
		filter.add(this.radioForFilter(new EinladungEmailFilter(this.getNeuestesTreffen())));
		filter.add(this.radioForFilter(new EinladungPostFilter(this.getNeuestesTreffen())));
		filter.add(this.radioForFilter(new KeineEinladungFilter(this.getNeuestesTreffen())));

		AlleFilter.getInstance().setMatchesListener(this);
		SucheFilter.getInstance().setMatchesListener(this);

		this.alle.setSelected(true);
		return filter;
	}

	@Override
	protected AdresseMitListePanel createPanel() {
		return new AdresseMitListePanel(this.persistenceStore);
	}

	public List<Adresse> getEmailAdressen() {
		return this.persistenceStore.getAdressen().getEmailAdressen();
	}

	public Treffen getNeuestesTreffen() {
		return this.persistenceStore.getTreffens().getNeuestesTreffen();
	}

	@Override
	public PersistenceStore getPersistenceStore() {
		return this.persistenceStore;
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
