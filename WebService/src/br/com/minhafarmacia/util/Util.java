package br.com.minhafarmacia.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import br.com.minhafarmacia.beans.Medicamento;
import br.com.minhafarmacia.beans.Receita;
import br.com.minhafarmacia.beans.Usuario;

public class Util {
	public void u() {
		System.out.println("teste");
	}
	/**
	 * Trocar uma caracter que seja invalido na string
	 * @param str
	 * @param troca
	 * @param trocarPor
	 * @return string tratada
	 */
	public static String retiraCaracterInvalido(String str, String troca, String trocarPor){
		
		return str.replace(troca, trocarPor);
	}
	/**
	 * Converte uma data que vem com String
	 * 
	 * @param dataString
	 * @return data
	 */
	public static Date converteStringToDate(String dataString) {
		DateFormat formatter;
		if(dataString.length() <= 10){
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		}else{
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		}
		Date dataNascimentoCorreto = new Date();
		try {
			dataNascimentoCorreto = (Date) formatter.parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataNascimentoCorreto;
	}

	// "4 de set de 2016 02:08"
	/**
	 * Transforma um data por extenso em uma data no formato abreviado ex
	 * 10/09/2012
	 * 
	 * @param dataString
	 * @return daata abreviada
	 */
	public static String trataDataPadraoString(String dataString) {
		StringBuilder dia = new StringBuilder();
		StringBuilder mes = new StringBuilder();
		StringBuilder ano = new StringBuilder();
		StringBuilder dataCompleta = new StringBuilder();
		StringBuilder hora = new StringBuilder();
		StringBuilder data = new StringBuilder();
		// se o dia for entre 1 e 9 terá 16 letras entao acrescenta o 0 no
		// inicio

		System.out.println(dataString.length());

		if (dataString.length() == 16 || dataString.length() == 22) {
			data.append("0");
			data.append(dataString);
		} else {
			data.append(dataString);
		}
		// 3 de ago de 2016
		System.out.println("--->" + data + "<---");
		for (int i = 0; i <= data.length(); i++) {
			if (i <= 1)
				dia.append(data.charAt(i));
			if (i >= 6 && i <= 8)
				mes.append(data.charAt(i));
			if (i >= 13 && i <= 16)
				ano.append(data.charAt(i));
			if (dataString.length() >= 18 && i >= 17 && i <= 22)
				hora.append(data.charAt(i));
			

		}
		dataCompleta.append(dia);
		dataCompleta.append("/");
		dataCompleta.append(getNumeroMes(mes.toString()));
		dataCompleta.append("/");
		dataCompleta.append(ano);
		if (dataString.length() >= 18)
			dataCompleta.append(hora);

		return dataCompleta.toString();
	}

	/**
	 * Retorno o numero do mes de acordo com a abreviatura
	 * 
	 * @param mesString
	 * @return numero do mes em string
	 */
	public static String getNumeroMes(String mesString) {
		String mes = "";
		switch (mesString) {
		case "jan":
			mes = "01";
			break;
		case "fev":
			mes = "02";
			break;
		case "mar":
			mes = "03";
			break;
		case "abr":
			mes = "04";
			break;
		case "mai":
			mes = "05";
			break;
		case "jun":
			mes = "06";
			break;
		case "jul":
			mes = "07";
			break;
		case "ago":
			mes = "08";
			break;
		case "set":
			mes = "09";
			break;
		case "out":
			mes = "10";
			break;
		case "nov":
			mes = "11";
			break;
		case "dez":
			mes = "12";
			break;
		default:
			mes = "00";
			break;
		}

		return mes;
	}

	/**
	 * Método responsavel por fazer a códificação da imagem, pega a foto no
	 * banco que esta salvo em byte e a transforma em String base 64
	 * 
	 * @param Usuario
	 * @return Usuario dados tratado
	 */
	public static Usuario trataDadosUsuario(Usuario u) {

		if (u.getFotoByte() != null) {
			u.setFoto(u.getFotoByte().toString());
			System.out.println(u.getFotoByte());
			String e = Base64.getEncoder().encodeToString(u.getFotoByte());
			u.setFoto(e);
			System.out.println(e);
		}
		return u;
	}

	/**
	 * Método responsavel por fazer a códificacao da imagem, pega a foto no
	 * banco que esta salvo como byte e a transforma em String base 64
	 * 
	 * @param m
	 * @return todos os dados do medicamento
	 */
	public static Medicamento trataDadosMedicamento(Medicamento m) {
		if (m.getFotoBytes() != null) {
			m.setFotoMedicamentoString(m.getFotoBytes().toString());
			String e = Base64.getEncoder().encodeToString(m.getFotoBytes());
			m.setFotoMedicamentoString(e);
		}
		return m;
	}
	/**
	 * Método responsavel por fazer a códificacao da imagem, pega a foto no
	 * banco que esta salvo como byte e a transforma em String base 64
	 * 
	 * @param m
	 * @return todos os dados da receita
	 */
	public static Receita trataDadosReceita(Receita r){
		if(r.getFotoReceta() != null){
			r.setFotoReceitaString(r.getFotoReceta().toString());
			String rs = Base64.getEncoder().encodeToString(r.getFotoReceta());
			r.setFotoReceitaString(rs);
		}
		return r;
	}
	/**
	 * O método corta uma String, recebe um string como parametro, valor inicial e o final, o inicio
	 * indica a partir de onde ira começar a contar e o fim até onde
	 * @param str
	 * @param inicio
	 * @param fim
	 * @return string trata
	 */
	public static String trataTamanhoString(String str, int inicio, int fim){
		return str.substring(inicio,fim);
	}
	/**
	 * Método transforma uma data americana no formato brasileiro, de yyyy-MM-dd HH:mm para
	 *  dd/MM/yyyy HH:mm
	 * @param str
	 * @return data tratada
	 */
	public static String trataStringDataFormatoAmericano(String str){
		//2016-10-19 03:55
		String [] strDividida = str.split(" ");
		String hora = strDividida[1];
		String data = strDividida[0];
		
		StringBuilder dataCorreta = new StringBuilder();
		StringBuilder ano = new StringBuilder();
		StringBuilder mes = new StringBuilder();
		StringBuilder dia = new StringBuilder();
		ano.append(data.substring(0, 4));
		mes.append(data.substring(5, 7));
		dia.append(data.substring(8, data.length()));
		
		dataCorreta.append(dia);
		dataCorreta.append("/");
		dataCorreta.append(mes);
		dataCorreta.append("/");
		dataCorreta.append(ano);
		dataCorreta.append(" ");
		dataCorreta.append(hora);
		
		
		return dataCorreta.toString();
	}
	/**
	 * Método responsável por converter uma String base 64 em um array de bytes
	 * 
	 * @param str
	 *            base 64
	 * @return foto em um array de bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] converteToByte(String str) throws FileNotFoundException, IOException {
		byte[] bytes;
		bytes = str.getBytes("UTF-8");
		byte[] decoded = Base64.getDecoder().decode(str);
		System.out.println(decoded);
		System.out.println(decoded.length);
		// converteByteArrayToFile(decoded);
		return decoded;
	}

	/**
	 * 
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void converteByteArrayToFile(byte[] bytes) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img.pdf"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.png"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.jpg"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.jpeg"));
		fos.write(bytes);
	}
}
