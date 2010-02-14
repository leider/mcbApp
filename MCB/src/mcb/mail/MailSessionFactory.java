package mcb.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailSessionFactory {
	public static String from;
	public static String replyto;

	public static Session createSession() {
		Properties mailProperties = readProperties();

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

		properties.setProperty("mail.smtp.host", mailProperties.getProperty("smtp"));
		properties.setProperty("mail.smtp.port", mailProperties.getProperty("port"));

		return Session.getInstance(properties, authenticator);
	}

	private static Properties readProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("mail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		from = props.getProperty("from");
		replyto = props.getProperty("replyto");
		return props;
	}

	private MailSessionFactory() {
		super();
	}
}
