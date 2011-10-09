package mcb.mail;

import java.util.Date;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.SwingUtilities;

import mcb.model.Adresse;
import mcb.model.Treffen;

import org.apache.log4j.Logger;

public class MailSender implements Runnable {

	private Session session;
	protected Date jetzt = new Date();
	private SendCompleteListener listener;
	static final Logger LOGGER = Logger.getLogger(MailSender.class.getName());
	private final Treffen neuestesTreffen;

	public MailSender(SendCompleteListener theListener, Treffen neuestesTreffen) {
		super();
		this.listener = theListener;
		this.neuestesTreffen = neuestesTreffen;
	}

	protected Session getSession() {
		if (this.session == null) {
			this.session = MailSessionFactory.createSession();
		}
		return this.session;
	}

	@Override
	public void run() {
		for (Adresse adresse : this.listener.getPersistenceStore().getAdressen().getEmailAdressen()) {
			try {
				this.send(adresse);
			} catch (Exception e) {
				MailSender.LOGGER.fatal(e.getMessage(), e);
			}
		}
		Runnable communicateAllSent = new Runnable() {

			@Override
			public void run() {
				MailSender.this.listener.messagesSent();
			}
		};
		SwingUtilities.invokeLater(communicateAllSent);
	}

	private void send(Adresse adresse) throws Exception {
		MimeMessage message = new MimeMessage(this.getSession());
		message.addRecipient(RecipientType.TO, new InternetAddress(adresse.getEmail()));
		message.addRecipient(RecipientType.BCC, new InternetAddress(MailSessionFactory.replyto));
		message.addFrom(new InternetAddress[] { new InternetAddress(MailSessionFactory.from) });
		message.setReplyTo(new InternetAddress[] { new InternetAddress(MailSessionFactory.replyto) });
		message.setSubject(this.neuestesTreffen.getBeschreibung());
		message.setSentDate(this.jetzt);
		message.setText(this.neuestesTreffen.getEmailPreviewText(adresse.getVorname()), "UTF-8");
		Transport.send(message);
	}

}
