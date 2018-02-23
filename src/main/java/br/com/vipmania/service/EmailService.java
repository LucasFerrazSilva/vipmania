package br.com.vipmania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private MailSender sender;
	
	public void send(String userEmail) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			
			email.setSubject("Compra finalizada com sucesso");
			email.setTo(userEmail);
			email.setBcc("lucasferrazsilva@hotmail.com");
			email.setText("Sua compra foi finalizada com sucesso!!!");
			email.setFrom("compras@vipmania.com.br");
			
			sender.send(email);
		}
		catch(Exception e) {
			System.out.println("Erro ao enviar email\n" + e);
		}
	}
	
}