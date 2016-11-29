package br.com.minhafarmacia.email;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Classe responsável em enviar o email com a recuperação de senha ao usuário
 * 
 * @author kleitonbatista
 *
 */
public class Email {
	private String host;
	private String emailProprietario;
	private String senha;
	private String msg;
	private String assunto;
	private String destinatario;
	private String nome;

	public Email() {
		setEmailProprietario("projetominhafarmacia@gmail.com");
		setSenha("kleiton0103");
		setHost("smtp.googlemail.com");
		setNome("Minha Farmácia");
	}

	/**
	 * 
	 * @throws EmailException
	 */
	public void SendEmail() {
		SimpleEmail email = new SimpleEmail();

		try {
			email.setHostName(getHost());
			email.setAuthentication(getEmailProprietario(), getSenha());
			email.setSSLOnConnect(true);
			email.setFrom(getEmailProprietario(), getNome());
			email.setMsg(preparaMsg(getMsg()));
			email.setSubject(getAssunto());
			email.addTo(getDestinatario());
			email.send();
			System.out.println("email enviado");
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			System.out.println("erro ao enviar email");
		}

	}
	private String preparaMsg(String msg){
		StringBuilder sb = new StringBuilder();
		sb.append("Sua senha de acesso é : ");
		sb.append(msg);
		sb.append("\n Solicitação de recuperação de senha realizado em: ");
		sb.append(preparaHora()+" horário de Brasília");
		sb.append("\n\n\n Caso não tenha solicitado recuperação de senha ignorar esse E-mail");
		return sb.toString();
	}

	/**
	 * Formata a data para o padrao brasileiro
	 * 
	 * @return data e hora atual no formato de String
	 */
	private String preparaHora() {
//		StringBuilder sb = new StringBuilder();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMM 'de' yyyy 'as' hh:mm");
//		return sb.append(sdf.format(new Date())).toString();
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar ca = GregorianCalendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'as' HH:mm 'Hrs'");
		Date h = ca.getInstance().getTime(); // Ou qualquer outra forma que tem
		return sdf.format(h);
		
	}
//	public static String formataHorario(){
//		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
//		TimeZone.setDefault(tz);
//		Calendar ca = GregorianCalendar.getInstance(tz);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date h = ca.getInstance().getTime(); // Ou qualquer outra forma que tem
//		return sdf.format(h);
//		
//	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
