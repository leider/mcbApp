package mcb.mail;

import org.junit.Test;

public class SendMailTest {

	@Test
	public void testSending() throws Exception {
		new MailSender().send("leider@andrena.de", "Subjekt", "Body");
	}
}
