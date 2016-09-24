package br.com.minhafarmacia.email;

public class TesteEnvioEmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("aki");
			Email email = new Email();
			
			
			
			email.setAssunto("Teste de email");
			email.setDestinatario("kleiton.market@gmail.com");
			email.setMsg("teste");
			email.SendEmail();
			
			email = new Email();
			email.setAssunto("Teste de email para gay");
			email.setDestinatario("philipe_ra@hotmail.com");
			email.setMsg("seu gay");
			email.SendEmail();
			
			
			email = new Email();
			email.setAssunto("Teste de email");
			email.setDestinatario("kleitinho_alvesrb@hotmail.com");
			email.setMsg("teste");
			email.SendEmail();
			
			
			email = new Email();
			email.setAssunto("Teste de email");
			email.setDestinatario("kleiton.a.batista@gmail.com");
			email.setMsg("teste");
			email.SendEmail();
			
			
			email = new Email();
			email.setAssunto("Teste de email");
			email.setDestinatario("kleiton.a.batista@icloud.com");
			email.setMsg("teste");
			email.SendEmail();
	
	}

}
