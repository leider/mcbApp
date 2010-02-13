package mcb.panel;

import javax.swing.JPanel;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

public abstract class ModelPanel<T extends Model> extends JPanel {

	private static final long serialVersionUID = -7811326380823888222L;
	protected BearbeitenAction<T> bearbeitenAction;
	protected PresentationModel<T> presentationModel;

	public ModelPanel(PresentationModel<T> presentationModel, BearbeitenAction<T> bearbeitenAction) {
		super();
		this.presentationModel = presentationModel;
		this.bearbeitenAction = bearbeitenAction;
	}

}
