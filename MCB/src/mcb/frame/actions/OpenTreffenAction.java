package mcb.frame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mcb.frame.TreffenFrame;

public class OpenTreffenAction extends AbstractAction {
	private static final long serialVersionUID = 8568897588247326614L;

	public OpenTreffenAction(String name) {
		super(name);
	}

	public void actionPerformed(ActionEvent e) {
		new TreffenFrame();
	}
}