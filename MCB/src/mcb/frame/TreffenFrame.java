package mcb.frame;

import javax.swing.JMenuBar;

import mcb.panel.TreffenMitListePanel;
import mcb.persistenz.Treffen;

public class TreffenFrame extends SimpleFrame<Treffen> {

	private static final long serialVersionUID = -6092233954906826211L;

	public TreffenFrame() {
		super("MCB Treffen");
	}

	@Override
	protected void addExtraMenu(JMenuBar bar) {
	}

	@Override
	protected TreffenMitListePanel getPanel() {
		return new TreffenMitListePanel();
	}

}
