package mcb.frame.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mcb.frame.AdresseFrame;
import mcb.mail.MailSender;
import mcb.model.Adresse;

public class EmailAction extends AbstractAction {
	private final AdresseFrame adresseFrame;
	private static final long serialVersionUID = 8568897588247326614L;

	public EmailAction(AdresseFrame adresseFrame, String name) {
		super(name);
		this.adresseFrame = adresseFrame;
	}

	public void actionPerformed(ActionEvent e) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Es wird eine Einladung verschickt an: ");
		List<Adresse> emailAdressen = this.adresseFrame.getEmailAdressen();
		for (Adresse adresse : emailAdressen) {
			buffer.append(adresse.getEmail());
			buffer.append(", ");
			if (buffer.length() > 100) {
				break;
			}
		}
		String text = buffer.toString();
		String message = text.substring(0, Math.min(text.length(), 100));
		int sendReally = JOptionPane.showConfirmDialog(this.adresseFrame, message);
		if (sendReally == JOptionPane.OK_OPTION) {
			new Thread(new MailSender(this.adresseFrame)).run();
		}
	}
}