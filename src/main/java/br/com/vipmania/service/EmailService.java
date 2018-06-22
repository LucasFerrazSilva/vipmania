package br.com.vipmania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private MailSender sender;
	
	public void send(String userEmail, Long orderId) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			
			StringBuilder textBuilder = new StringBuilder();
			
			textBuilder.append("Sua compra foi finalizada com sucesso!!!\n")
					   .append("\n")
					   .append("O número do seu pedido é ").append(orderId).append("\n")
					   .append("\n")
					   .append("Para mais informações, consulte o site.\n");
			
			email.setSubject("Compra finalizada com sucesso");
			email.setTo(userEmail);
			email.setBcc("lucasferrazsilva@hotmail.com");
			email.setText(textBuilder.toString());
			email.setFrom("compras@vipmania.com.br");
			
			sender.send(email);
		}
		catch(Exception e) {
			System.out.println("Erro ao enviar email\n" + e);
		}
	}
	
}