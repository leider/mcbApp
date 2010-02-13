package mcb.panel;

import java.awt.Font;

import javax.swing.*;

import mcb.persistenz.Treffen;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TreffenPanel extends ModelPanel<Treffen> {

	private static final long serialVersionUID = -427646783597528169L;
	private JTextField nameTextfield;
	private JTextField ersterTagTextfield;
	private JTextField letzterTagTextfield;
	private JButton bearbeitenButton;
	private JScrollPane emailScrollfield;
	private JTextArea emailTextfield;
	private JTextField beschreibungTextfield;
	private JTextArea emailPreviewTextfield;

	public TreffenPanel(PresentationModel<Treffen> presentationModel, BearbeitenAction<Treffen> bearbeitenAction) {
		super(presentationModel, bearbeitenAction);
		initComponents();
		buildPanel();
	}

	private void buildPanel() {

		FormLayout layout = new FormLayout(
				"3dlu, right:pref, 3dlu, 60dlu, 3dlu, 30dlu, 3dlu, 200dlu, 3dlu", // cols
				"3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, 20dlu, 3dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout, this);
		CellConstraints cc = new CellConstraints();
		int row = 2;
		builder.add(bearbeitenButton, cc.xyw(2, row, 5));
		row++;
		row++;
		builder.addSeparator("Allgemein", cc.xyw(2, row, 8));
		row++;
		row++;
		builder.addLabel("Name", cc.xy(2, row));
		builder.add(nameTextfield, cc.xyw(4, row, 3));
		row++;
		row++;
		builder.addLabel("Beschreibung", cc.xy(2, row));
		builder.add(beschreibungTextfield, cc.xyw(4, row, 5));
		row++;
		row++;
		builder.addLabel("Erster Tag", cc.xy(2, row));
		builder.add(ersterTagTextfield, cc.xy(4, row));
		row++;
		row++;
		builder.addLabel("Letzter Tag", cc.xy(2, row));
		builder.add(letzterTagTextfield, cc.xy(4, row));
		row++;
		row++;
		builder.addLabel("Emailtext", cc.xy(2, row));
		builder.add(emailScrollfield, cc.xyw(4, row, 5));
	}

	private void initComponents() {
		nameTextfield = BasicComponentFactory.createTextField(presentationModel.getBufferedModel(Treffen.NAME), false);
		ersterTagTextfield = BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(Treffen.ERSTER_TAG), false);
		letzterTagTextfield = BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(Treffen.LETZTER_TAG), false);
		beschreibungTextfield = BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(Treffen.BESCHREIBUNG), false);
		emailTextfield = BasicComponentFactory.createTextArea(presentationModel.getBufferedModel(Treffen.EMAIL_TEXT),
				false);
		emailTextfield.setRows(20);
		emailTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
		emailScrollfield = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		emailScrollfield.setViewportView(emailTextfield);
		bearbeitenButton = new JButton(bearbeitenAction);
		emailPreviewTextfield = new JTextArea();
		emailPreviewTextfield.setRows(20);
		emailPreviewTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
		setEnabled(false);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		nameTextfield.setEditable(enabled);
		ersterTagTextfield.setEditable(enabled);
		letzterTagTextfield.setEditable(enabled);
		emailTextfield.setEditable(enabled);
		emailScrollfield.setViewportView(enabled ? emailTextfield : emailPreviewTextfield);
		if (!enabled) {
			emailPreviewTextfield.setText(presentationModel.getBean().getEmailPreviewText());
			emailPreviewTextfield.setCaretPosition(0);
		}
	}
}
