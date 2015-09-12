package mcb.ui.treffen;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import mcb.model.Treffen;
import mcb.persistenz.PersistenceStore;
import mcb.ui.base.BearbeitenAction;
import mcb.ui.base.ComponentFactory;
import mcb.ui.base.ModelPanel;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.builder.FormBuilder;

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

  public TreffenPanel(PresentationModel<Treffen> presentationModel, BearbeitenAction<Treffen> bearbeitenAction,
      PersistenceStore persistenceStore) {
    super(presentationModel, bearbeitenAction, persistenceStore);
    this.initComponents();
    this.buildPanel();
  }

  private void buildPanel() {
    FormBuilder
    .create()
    .columns("3dlu, right:pref, 3dlu, 60dlu, 3dlu, 30dlu, 3dlu, pref:grow, 3dlu")
    .rows(
        "3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, 20dlu, 3dlu")
        .panel(this)
        .add(this.bearbeitenButton).xyw(2, 2, 5)
        .addSeparator("Allgemein").xyw(2, 4, 8)
        .addLabel("Name").xy(2, 6)
        .add(this.nameTextfield).xyw(4, 6, 3)
        .addLabel("Beschreibung").xy(2, 8)
        .add(this.beschreibungTextfield).xyw(4, 8, 5)
        .addLabel("Erster Tag").xy(2, 10)
        .add(this.ersterTagTextfield).xy(4, 10)
        .addLabel("Letzter Tag").xy(2, 12)
        .add(this.letzterTagTextfield).xy(4, 12)
        .addLabel("Emailtext").xy(2, 14)
        .add(this.emailScrollfield).xyw(4, 14, 5)
        .build();
  }

  private void initComponents() {
    this.nameTextfield = ComponentFactory.createTextField(this.presentationModel.getBufferedModel(Treffen.NAME), false);
    this.ersterTagTextfield = ComponentFactory.createTextField(this.presentationModel.getBufferedModel(Treffen.ERSTER_TAG), false);
    this.letzterTagTextfield = ComponentFactory.createTextField(this.presentationModel.getBufferedModel(Treffen.LETZTER_TAG), false);
    this.beschreibungTextfield = ComponentFactory.createTextField(this.presentationModel.getBufferedModel(Treffen.BESCHREIBUNG), false);
    this.emailTextfield = ComponentFactory.createTextArea(this.presentationModel.getBufferedModel(Treffen.EMAIL_TEXT), false);
    this.emailTextfield.setRows(20);
    this.emailTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
    this.emailScrollfield = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.emailScrollfield.setViewportView(this.emailTextfield);
    this.bearbeitenButton = new JButton(this.bearbeitenAction);
    this.emailPreviewTextfield = ComponentFactory.createTextArea(this.presentationModel.getBufferedModel(Treffen.EMAIL_PREVIEW_TEXT),
        false);
    this.emailPreviewTextfield.setRows(20);
    this.emailPreviewTextfield.setColumns(120);
    this.emailPreviewTextfield.setLineWrap(true);
    this.emailPreviewTextfield.setFont(new Font("Monospaced", Font.PLAIN, 12));
    this.emailPreviewTextfield.setCaretPosition(0);
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
  }
}
