package br.com.minhafarmacia.email;

public class TesteEnvioEmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("aki");
			Email email = new Email();
			email.setAssunto("Teste de email");
			email.setDestinatario("annaluisatr@gmail.com");
			email.setMsg("Eu te amo");
			email.SendEmail();
	
	}

}
