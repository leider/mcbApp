package mcb.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mcb.persistenz.Adresse;
import mcb.persistenz.ApplicationData;
import mcb.persistenz.Besuch;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class AdressePanel extends ModelPanel<Adresse> {

	private static final long serialVersionUID = -427646783597528169L;
	private JTextField vornameTextfield;
	private JTextField nachnameTextfield;
	private JTextField strasseTextfield;
	private JTextField plzTextfield;
	private JTextField ortTextfield;
	private JComboBox landTextfield;
	private JTextField emailTextfield;
	private JComboBox emailgrundTextfield;
	private JCheckBox gespannCheckbox;
	private JCheckBox soloCheckbox;
	private JTextField geburtstagTextfield;
	private JList besuchListe;
	private JCheckBox meldungCheckbox;
	private JSpinner fruehstueckSamstagIntegerField;
	private JSpinner fruehstueckSonntagIntegerField;
	private JButton bearbeitenButton;

	public AdressePanel(PresentationModel<Adresse> presentationModel, BearbeitenAction<Adresse> bearbeitenAction) {
		super(presentationModel, bearbeitenAction);
		initComponents();
		buildPanel();
		initListeners();
	}

	private void buildPanel() {
		FormLayout layout = new FormLayout(
				"3dlu, right:pref, 3dlu, 35dlu, 3dlu, 35dlu, 3dlu, 40dlu, 3dlu, 60dlu, 3dlu, 53dlu, 3dlu", // cols
				"3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout, this);
		CellConstraints cc = new CellConstraints();

		int row = 2;
		builder.add(bearbeitenButton, cc.xyw(2, row, 5));
		row++;
		row++;
		builder.addSeparator("Adresse", cc.xyw(2, row, 11));
		row++;
		row++;
		builder.addLabel("Vorname, Name", cc.xy(2, row));
		builder.add(vornameTextfield, cc.xyw(4, row, 5));
		builder.add(nachnameTextfield, cc.xyw(10, row, 3));
		row++;
		row++;
		builder.addLabel("Straße", cc.xy(2, row));
		builder.add(strasseTextfield, cc.xyw(4, row, 9));
		row++;
		row++;
		builder.addLabel("Land, PLZ, Ort", cc.xy(2, row));
		builder.add(landTextfield, cc.xy(4, row));
		builder.add(plzTextfield, cc.xy(6, row));
		builder.add(ortTextfield, cc.xyw(8, row, 5));
		row++;
		row++;
		builder.addLabel("Geburtstag", cc.xy(2, row));
		builder.add(geburtstagTextfield, cc.xyw(4, row, 5));
		row++;
		row++;
		builder.addSeparator("Email", cc.xyw(2, row, 11));
		row++;
		row++;
		builder.addLabel("Adresse", cc.xy(2, row));
		builder.add(emailTextfield, cc.xyw(4, row, 8));
		row++;
		row++;
		builder.add(emailgrundTextfield, cc.xyw(4, row, 8));
		row++;
		row++;
		builder.addSeparator("Fahrzeuge", cc.xyw(2, row, 11));
		row++;
		row++;
		builder.add(gespannCheckbox, cc.xyw(4, row, 5));
		builder.add(soloCheckbox, cc.xyw(10, row, 2));
		row++;
		row++;
		builder.addSeparator("vergangene Treffen", cc.xyw(2, row, 5));
		builder.addSeparator("aktuelles Treffen", cc.xyw(8, row, 5));
		row++;
		row++;
		builder.add(new JScrollPane(besuchListe), cc.xywh(2, row, 5, 5));
		builder.add(meldungCheckbox, cc.xyw(8, row, 4));
		row++;
		row++;
		builder.addLabel("Frühstück Samstag", cc.xyw(8, row, 3));
		builder.add(fruehstueckSamstagIntegerField, cc.xy(12, row));
		row++;
		row++;
		builder.addLabel("Frühstück Sonntag", cc.xyw(8, row, 3));
		builder.add(fruehstueckSonntagIntegerField, cc.xy(12, row));
	}

	protected void fruehstueckSamstagChanged() {
		Adresse adresse = presentationModel.getBean();
		if (adresse == null || adresse.getAktuellesTreffen() == null) {
			fruehstueckSamstagIntegerField.setValue(0);
			return;
		}
		adresse.getAktuellesTreffen().setFruehstueckSamstag((Integer) fruehstueckSamstagIntegerField.getValue());
		ApplicationData.saveAdresse(adresse);
	}

	protected void fruehstueckSonntagChanged() {
		Adresse adresse = presentationModel.getBean();
		if (adresse == null || adresse.getAktuellesTreffen() == null) {
			fruehstueckSonntagIntegerField.setValue(0);
			return;
		}
		adresse.getAktuellesTreffen().setFruehstueckSonntag((Integer) fruehstueckSonntagIntegerField.getValue());
		ApplicationData.saveAdresse(adresse);
	}

	private void initComponents() {
		String[] countries = { "A", "B", "CH", "CZ", "D", "DK", "E", "F", "FIN", "FL", "GB", "GR", "H", "HR", "I",
				"IRL", "L", "N", "NL", "P", "PL", "S" };
		ListModel countryListModel = new ArrayListModel<String>(Arrays.asList(countries));
		ValueModel countryModel = presentationModel.getBufferedModel(Adresse.LAND);
		SelectionInList<String> countrySil = new SelectionInList<String>(countryListModel, countryModel);
		landTextfield = BasicComponentFactory.createComboBox(countrySil);

		String[] fehlergruende = { "", "Empfänger existiert nicht", "Mailbox voll",
				"Email aus Spamgründen nicht akzeptiert" };
		ListModel fehlerListModel = new ArrayListModel<String>(Arrays.asList(fehlergruende));
		ValueModel fehlerModel = presentationModel.getBufferedModel(Adresse.FEHLERGRUND);
		SelectionInList<String> fehlerSil = new SelectionInList<String>(fehlerListModel, fehlerModel);
		emailgrundTextfield = BasicComponentFactory.createComboBox(fehlerSil);

		vornameTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Adresse.VORNAME),
				false);
		nachnameTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Adresse.NAME),
				false);
		strasseTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Adresse.STRASSE),
				false);
		plzTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Adresse.PLZ), false);
		ortTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Adresse.ORT), false);
		emailTextfield = BasicComponentFactory
				.createTextField(presentationModel.getBufferedModel(Adresse.EMAIL), false);
		gespannCheckbox = BasicComponentFactory.createCheckBox(presentationModel.getBufferedModel(Adresse.GESPANN),
				"Gespann");
		soloCheckbox = BasicComponentFactory.createCheckBox(presentationModel.getBufferedModel(Adresse.SOLO), "Solo");
		geburtstagTextfield = BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(Adresse.GEBURTSTAG), false);
		SelectionInList<Besuch> besuchList = new SelectionInList<Besuch>(presentationModel
				.getBufferedModel(Adresse.VERGANGENE_TREFFEN));
		besuchListe = BasicComponentFactory.createList(besuchList);
		besuchListe.setEnabled(false);
		meldungCheckbox = new JCheckBox("Meldung");
		fruehstueckSamstagIntegerField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		fruehstueckSonntagIntegerField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		((SpinnerNumberModel) fruehstueckSonntagIntegerField.getModel()).setMinimum(0);
		bearbeitenButton = new JButton(bearbeitenAction);
		setEnabled(false);
	}

	private void initListeners() {
		presentationModel.addPropertyChangeListener(PresentationModel.PROPERTYNAME_BEAN, new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				updateCheckboxes();
			}
		});

		meldungCheckbox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				meldungChanged();
			}

		});
		fruehstueckSamstagIntegerField.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				fruehstueckSamstagChanged();
			}

		});
		fruehstueckSonntagIntegerField.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				fruehstueckSonntagChanged();
			}

		});
	}

	protected void meldungChanged() {
		Adresse adresse = presentationModel.getBean();
		if (adresse == null) {
			meldungCheckbox.setSelected(false);
			return;
		}
		if (meldungCheckbox.isSelected()) {
			adresse.addAktuellesTreffen();
			ApplicationData.saveAdresse(adresse);
		} else {
			ApplicationData.loescheModel(adresse.getAktuellesTreffen());
			adresse.removeAktuellesTreffen();
			ApplicationData.saveAdresse(adresse);
		}
		updateCheckboxes();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		vornameTextfield.setEditable(enabled);
		nachnameTextfield.setEditable(enabled);
		strasseTextfield.setEditable(enabled);
		plzTextfield.setEditable(enabled);
		ortTextfield.setEditable(enabled);
		landTextfield.setEnabled(enabled);
		emailTextfield.setEditable(enabled);
		gespannCheckbox.setEnabled(enabled);
		geburtstagTextfield.setEditable(enabled);
		soloCheckbox.setEnabled(enabled);
		emailgrundTextfield.setEnabled(enabled);
	}

	protected void updateCheckboxes() {
		Adresse adresse = presentationModel.getBean();
		if (adresse != null) {
			Besuch aktuellesTreffen = adresse.getAktuellesTreffen();
			if (aktuellesTreffen == null) {
				fruehstueckSamstagIntegerField.setValue(0);
				fruehstueckSonntagIntegerField.setValue(0);
				meldungCheckbox.setSelected(false);
			} else {
				meldungCheckbox.setSelected(true);
				fruehstueckSamstagIntegerField.setValue(aktuellesTreffen.getFruehstueckSamstag());
				fruehstueckSonntagIntegerField.setValue(aktuellesTreffen.getFruehstueckSonntag());
			}
			emailTextfield.setForeground(adresse.isEmailfehler() ? Color.RED : nachnameTextfield.getForeground());
		}
	}

	protected void updateEmailColor() {
		emailTextfield.setBackground(Color.RED);
	}
}
