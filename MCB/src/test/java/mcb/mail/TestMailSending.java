package mcb.mail;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Ignore;
import org.junit.Test;

import mcb.persistenz.PersistenceStore;

public class TestMailSending {

  @Test
  @Ignore
  public void testSendingWithUmlauts() throws Exception {
    String to = "leider@me.com";
    String subject = "Test";
    String body = "Body mit �ml�uten ���";
    SendCompleteListener listener = new SendCompleteListener() {

      @Override
      public void currentlySending(String infoText) {

      }

      @Override
      public PersistenceStore getPersistenceStore() {
        return new PersistenceStore();
      }

      @Override
      public void messagesNotSent() {
      }

      @Override
      public void messagesSent() {
      }
    };
    MailSender r = new MailSender(listener);
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
