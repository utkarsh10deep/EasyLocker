/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author utkarsh.deep
 */
public class Mail {
    static final String id[] = {"myeasylocker01","myeasylocker02"};
    public static String OTP;
    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public static void send(String subject,String message,String emailId) throws AddressException,
		MessagingException {
        
		Mail javaEmail = new Mail();
		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(subject,message, emailId);
		javaEmail.sendEmail();
	
    }

	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String subject,String message, String emailId) throws AddressException,
			MessagingException {
		String[] toEmails = {emailId};
		String emailSubject = subject;
		String emailBody = message;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		//emailMessage.setContent(emailBody, "text/html");//for a html email
		emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
                String key = Utilities.generateOTP();
                int num =0;
                for(int i=0;i<key.length();i++)
                {
                    num = num + (int)key.charAt(i);
                }
                
                
		String fromUser = "easylocker2019";//just the id alone without @gmail.com
		String fromUserEmailPassword = "lockereasy9102";  
                if(num%2==0)
                {
                    fromUser = id[0];
                    fromUserEmailPassword = "Asdf@571";
                }
                else
                {
                    fromUser = id[1];
                    fromUserEmailPassword = "Asdf@571";
                }
                
                
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

}

