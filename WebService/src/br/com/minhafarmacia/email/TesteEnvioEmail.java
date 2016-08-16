package br.com.minhafarmacia.email;

import org.apache.commons.mail.EmailException;

public class TesteEnvioEmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Email email = new Email();
			email.setAssunto("Teste de email");
			email.setDestinatario("kleiton.a.batista@gmail.com");
			email.setMsg("Ola Kleiton, estamos testando o envio de email");
			email.SendEmail();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
