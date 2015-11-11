package authorization;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMessage {
	private static final String CHARSET = "KOI8-R";
	final private static String EMAIL = "team_puzzle@mail.ua";
	final private static String PASSWORD = "puzart6$2015";
	final private static String HOST = "smtp.mail.ru";
	final private static String PORT = "465";
	private static InternetAddress Address = new InternetAddress();
	private Properties mailProps = new Properties();

	public void sendMessage(String[] recipients, String subject, String text)
			throws MessagingException {
		initMailProps();
		Authenticator auth = new SMTPAuthenticator();
		InternetAddress dests[] = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			dests[i] = new InternetAddress(recipients[i].trim().toLowerCase());
		}
		Session mailSession = Session.getInstance(mailProps, auth);
		MimeMessage mimeMessage = new MimeMessage(mailSession);
		mimeMessage.setRecipients(Message.RecipientType.TO, dests);
		mimeMessage.setSubject(subject, CHARSET);
		Address.setAddress("team_puzzle@mail.ua");
		mimeMessage.setFrom(Address);
		mimeMessage.setSentDate(new java.util.Date());
		
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setText(text, CHARSET);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		
		mimeMessage.setContent(multipart);
		
		Transport.send(mimeMessage);
	}

	private void initMailProps() {
		mailProps.put("mail.smtp.host", HOST);
		mailProps.put("mail.smtp.port", PORT);
		mailProps.put("mail.smtp.user", EMAIL);
		mailProps.put("mail.smtp.password", PASSWORD);

		mailProps.put("mail.smtp.starttls.enable", "true");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.socketFactory.port", PORT);
		mailProps.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailProps.put("mail.smtp.socketFactory.fallback", "false");
	}

	public class SMTPAuthenticator extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(EMAIL, PASSWORD);
		}
	}
}
