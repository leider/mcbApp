package mcb.mail;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mcb.persistenz.PersistenceStore;

import org.junit.Test;

public class TestMailSending {

  @Test
  public void testSendingWithUmlauts() throws Exception {
    String to = "leider@me.com";
    String subject = "Test";
    String body = "Body mit Ümläuten ßßß";
    MailSender r = new MailSender(null, new PersistenceStore().getTreffens().getNeuestesTreffen());
    MimeMessage message = new MimeMessage(r.getSession());
    String from = MailSessionFactory.from;
    String replyto = MailSessionFactory.replyto;
    message.addRecipient(RecipientType.TO, new InternetAddress(to));
    message.addRecipient(RecipientType.BCC, new InternetAddress(replyto));
    message.addFrom(new InternetAddress[] { new InternetAddress(from) });
    message.setReplyTo(new InternetAddress[] { new InternetAddress(replyto) });
    message.setSubject(subject);
    message.setSentDate(r.jetzt);
    message.setText(body, "ISO-8859-1");

    // Transport.send(message);
  }

}
