package mcb.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.log4j.Logger;

public class MailSessionFactory {
	public static String from;
	public static String replyto;
	static final Logger LOGGER = Logger.getLogger(MailSessionFactory.class.getName());

	public static Session createSession() {
		Properties mailProperties = MailSessionFactory.readProperties();

		final String username = mailProperties.getProperty("username");
		final String password = mailProperties.getProperty("password");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", username);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.ssl.trust", "*");
		properties.setProperty("mail.smtp.host", mailProperties.getProperty("smtp"));
		properties.setProperty("mail.smtp.port", mailProperties.getProperty("port"));
		properties.put("mail.smtp.starttls.enable", "true");

		return Session.getInstance(properties, authenticator);
	}

	private static Properties readProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("mail.properties"));
		} catch (IOException e) {
			MailSessionFactory.LOGGER.fatal(e.getMessage(), e);
		}
		MailSessionFactory.from = props.getProperty("from");
		MailSessionFactory.replyto = props.getProperty("replyto");
		return props;
	}

	private MailSessionFactory() {
		super();
	}
}
