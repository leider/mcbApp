package mcb.mail;

import java.util.Date;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mcb.persistenz.Adresse;
import mcb.persistenz.ApplicationData;

public class MailSender implements Runnable {

	private Session session;
	private Date jetzt = new Date();
	private SendCompleteListener listener;

	public MailSender(SendCompleteListener theListener) {
		super();
		this.listener = theListener;
	}

	private Session getSession() {
		if (this.session == null) {
			this.session = MailSessionFactory.createSession();
		}
		return this.session;
	}

	@Override
	public void run() {
		String subject = ApplicationData.getNeuestesTreffen().getBeschreibung();
		for (Adresse adresse : ApplicationData.getEmailAdressen()) {
			String to = adresse.getEmail();
			String body = ApplicationData.getNeuestesTreffen().getEmailPreviewText(adresse.getVorname());
			try {
				this.send(to, subject, body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.listener.messagesSent();
	}

	public void send(String to, String subject, String body) throws Exception {
		MimeMessage message = new MimeMessage(this.getSession());
		String from = MailSessionFactory.from;
		String replyto = MailSessionFactory.replyto;
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.addRecipient(RecipientType.BCC, new InternetAddress(replyto));
		message.addFrom(new InternetAddress[] { new InternetAddress(from) });
		message.setReplyTo(new InternetAddress[] { new InternetAddress(replyto) });
		message.setSubject(subject);
		message.setSentDate(this.jetzt);
		message.setText(body, "UTF-8");

		Transport.send(message);
	}

}
