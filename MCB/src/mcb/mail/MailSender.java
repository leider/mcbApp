package mcb.mail;

import java.util.Date;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private Session session;
	private Date jetzt = new Date();

	private Session getSession() {
		if (session == null) {
			session = MailWithPasswordAuthentication.createSession();
		}
		return session;
	}

	public void send(String to, String subject, String body) throws Exception {
		String from = MailWithPasswordAuthentication.from;
		String replyto = MailWithPasswordAuthentication.replyto;
		MimeMessage message = new MimeMessage(getSession());
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.addRecipient(RecipientType.BCC, new InternetAddress(replyto));
		message.addFrom(new InternetAddress[] { new InternetAddress(from) });
		message.setReplyTo(new InternetAddress[] { new InternetAddress(replyto) });
		message.setSubject(subject);
		message.setSentDate(jetzt);
		message.setText(body, "UTF-8");

		Transport.send(message);
	}

}
