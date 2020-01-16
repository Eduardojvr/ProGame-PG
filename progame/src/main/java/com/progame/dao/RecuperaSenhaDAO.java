package com.progame.dao;
import java.util.Properties;
import com.progame.dao.UsuarioDAO;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class RecuperaSenhaDAO {

	public RecuperaSenhaDAO() {


	}

	public boolean esqueciSenha(String email) throws Exception {
		UsuarioDAO dao = new UsuarioDAO();
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", 
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication("progame.suporte@gmail.com", 
						"senha");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("progame.suporte@gmail.com")); 
			//Remetente

			Address[] toUser = InternetAddress //Destinatário(s)
					.parse(email);  
			
			String senha = dao.novaSenha(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Senha de acesso ProGame");//Assunto
			message.setText("Olá jogador! Sua nova senha temporária de acesso é: "+senha+" você pode alterar "
					+ "essa senha no seu perfil de usuário.");
			/**Método para enviar a mensagem criada*/
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
