package br.com.minhafarmacia.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.UsuarioDAO;
import br.com.minhafarmacia.enumeradores.SexoType;

public class TesteBanco {

	public static void main(String[] args) {
		System.out.println("inicio");
		Usuario u = new Usuario();
		u.setNome("Anna");
		u.setDataCadastro(new Date());
		u.setFoto("-");
		u.setIdade(21);
		u.setIdFacebook("idFace");
		u.setSenha("34521");
		u.setSexo("Feminino");
		byte []byt = null;
		u.setFotoByte(byt);
		u.setEmail("AnnaLuis@gmail.com");
		new UsuarioDAO().inseirUsuario(u);
//		System.out.println("fim");
		
		
		//System.out.println(new UsuarioDAO().fazLogin("kleiton@gmail.com", "q123"));
		//Usuario u = new UsuarioDAO().buscaUsuarioEmail("annaluisatr@gmail.com");
		//System.out.println(u);
//		try {
//			converteByteArrayToFile(u.getFotoByte());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void converteByteArrayToFile(byte[] bytes) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img2.pdf"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img2.png"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img2.jpg"));
		fos.write(bytes);
		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img2.jpeg"));
		fos.write(bytes);
	}
}
