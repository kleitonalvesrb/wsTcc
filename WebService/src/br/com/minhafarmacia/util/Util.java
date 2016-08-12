package br.com.minhafarmacia.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import br.com.minhafarmacia.beans.Usuario;

public class Util {
	public void u(){
		System.out.println("teste");
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
		converteByteArrayToFile(decoded);
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
