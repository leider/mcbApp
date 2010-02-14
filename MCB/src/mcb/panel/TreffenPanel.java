package mcb.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
		this.initComponents();
		this.buildPanel();
	}

	private void buildPanel() {

		FormLayout layout = new FormLayout(
				"3dlu, right:pref, 3dlu, 60dlu, 3dlu, 30dlu, 3dlu, pref:grow, 3dlu", // cols
				"3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, 20dlu, 3dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout, this);
		CellConstraints cc = new CellConstraints();
		int row = 2;
		builder.add(this.bearbeitenButton, cc.xyw(2, row, 5));
		row++;
		row++;
		builder.addSeparator("Allgemein", cc.xyw(2, row, 8));
		row++;
		row++;
		builder.addLabel("Name", cc.xy(2, row));
		builder.add(this.nameTextfield, cc.xyw(4, row, 3));
		row++;
		row++;
		builder.addLabel("Beschreibung", cc.xy(2, row));
		builder.add(this.beschreibungTextfield, cc.xyw(4, row, 5));
		row++;
		row++;
		builder.addLabel("Erster Tag", cc.xy(2, row));
		builder.add(this.ersterTagTextfield, cc.xy(4, row));
		row++;
		row++;
		builder.addLabel("Letzter Tag", cc.xy(2, row));
		builder.add(this.letzterTagTextfield, cc.xy(4, row));
		row++;
		row++;
		builder.addLabel("Emailtext", cc.xy(2, row));
		builder.add(this.emailScrollfield, cc.xyw(4, row, 5));
	}

	private void initComponents() {
		this.nameTextfield = BasicComponentFactory.createTextField(this.presentationModel
				.getBufferedModel(Treffen.NAME), false);
		this.ersterTagTextfield = BasicComponentFactory.createTextField(this.presentationModel
				.getBufferedModel(Treffen.ERSTER_TAG), false);
		this.letzterTagTextfield = BasicComponentFactory.createTextField(this.presentationModel
				.getBufferedModel(Treffen.LETZTER_TAG), false);
		this.beschreibungTextfield = BasicComponentFactory.createTextField(this.presentationModel
				.getBufferedModel(Treffen.BESCHREIBUNG), false);
		this.emailTextfield = BasicComponentFactory.createTextArea(this.presentationModel
				.getBufferedModel(Treffen.EMAIL_TEXT), false);
		this.emailTextfield.setRows(20);
		this.emailTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
		this.emailScrollfield = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.emailScrollfield.setViewportView(this.emailTextfield);
		this.bearbeitenButton = new JButton(this.bearbeitenAction);
		this.emailPreviewTextfield = new JTextArea();
		this.emailPreviewTextfield.setRows(20);
		this.emailPreviewTextfield.setColumns(120);
		this.emailPreviewTextfield.setLineWrap(true);
		this.emailPreviewTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
		this.setEnabled(false);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.nameTextfield.setEditable(enabled);
		this.ersterTagTextfield.setEditable(enabled);
		this.letzterTagTextfield.setEditable(enabled);
		this.emailTextfield.setEditable(enabled);
		this.emailScrollfield.setViewportView(enabled ? this.emailTextfield : this.emailPreviewTextfield);
		if (!enabled) {
			this.emailPreviewTextfield.setText(this.presentationModel.getBean().getEmailPreviewText());
			this.emailPreviewTextfield.setCaretPosition(0);
		}
	}
}
