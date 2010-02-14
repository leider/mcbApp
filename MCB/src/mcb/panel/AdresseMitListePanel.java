package mcb.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import mcb.model.Adresse;
import mcb.model.Summaries;
import mcb.persistenz.ApplicationData;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;

public class AdresseMitListePanel extends ModelMitListePanel<Adresse> {

	private static final long serialVersionUID = 444596326461558352L;

	public AdresseMitListePanel() {
		super();
	}

	@Override
	protected SelectionInListPanel<Adresse> createListePanel() {
		return new AdressenSelectionInListPanel();
	}

	@Override
	protected ModelPanel<Adresse> createModelPanel(PresentationModel<Adresse> model, BearbeitenAction<Adresse> action) {
		return new AdressePanel(model, action);
	}

	@Override
	protected void createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.add(new JLabel("Suche nach Vorname oder Name: "));
		final JTextField suchText = new JTextField();
		toolBar.add(suchText);
		suchText.setPreferredSize(new Dimension(300, suchText.getPreferredSize().height));
		suchText.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				// don't needed
			}

			public void keyReleased(KeyEvent e) {
				String text = suchText.getText();
				if ("".equals(text)) {
					ApplicationData.setFilter(ApplicationData.ALLE_FILTER);
				} else {
					ApplicationData.SUCHE_FILTER.setSucheText(text);
					ApplicationData.setFilter(ApplicationData.SUCHE_FILTER);
				}
				AdresseMitListePanel.this.updateListe();
			}

			public void keyTyped(KeyEvent e) {
				// don't needed
			}
		});
		toolBar.addSeparator();

		PresentationModel<Summaries> presentationModel = new PresentationModel<Summaries>(ApplicationData
				.getSummaries());
		toolBar.add(new JLabel("Samstag:"));
		JFormattedTextField samstag = BasicComponentFactory.createIntegerField(presentationModel
				.getModel(Summaries.FRUEHSTUCK_SAMSTAG));
		toolBar.add(samstag);
		int kantenlaenge = samstag.getPreferredSize().height;
		samstag.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
		samstag.setEditable(false);
		toolBar.add(new JLabel("Sonntag:"));
		JFormattedTextField sonntag = BasicComponentFactory.createIntegerField(presentationModel
				.getModel(Summaries.FRUEHSTUCK_SONNTAG));
		toolBar.add(sonntag);
		sonntag.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
		sonntag.setEditable(false);
		toolBar.add(new JLabel("Meldungen:"));
		JFormattedTextField meldungen = BasicComponentFactory.createIntegerField(presentationModel
				.getModel(Summaries.ANZAHL_MELDUNGEN));
		toolBar.add(meldungen);
		meldungen.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
		meldungen.setEditable(false);
		this.add(toolBar, BorderLayout.NORTH);
	}

	@Override
	protected String loeschenMessage(Adresse model) {
		return "Willst Du die Adresse von " + model.toString() + " wirklich terminieren?";
	}

	@Override
	protected void speichereModel(Adresse model) {
		ApplicationData.saveAdresse(model);
	}

}
