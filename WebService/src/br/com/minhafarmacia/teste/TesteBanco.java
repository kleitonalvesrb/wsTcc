package br.com.minhafarmacia.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.minhafarmacia.beans.Medicamento;
import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.UsuarioDAO;

public class TesteBanco {

	public static void main(String[] args) {
		// System.out.println("inicio");
		// Usuario u = new Usuario();
		// u.setNome("Anna");
		// u.setDataCadastro(new Date());
		// u.setFoto("-");
		// u.setIdade(21);
		// u.setIdFacebook("idFace");
		// u.setSenha("34521");
		// u.setSexo("Feminino");
		// byte []byt = null;
		// u.setFotoByte(byt);
		// u.setEmail("annaluisa@gmail.com");
		// //
		// //System.out.println(Util.trataDataPadraoString("1 de mar de 1998"));
		// u.setDataNascimento(Util.converteStringToDate(Util.trataDataPadraoString("1
		// de mar de 1998")));
		//// System.out.println("fim");
		// new UsuarioDAO().inseirUsuario(u);
		// //System.out.println(new
		// UsuarioDAO().verificaExistencia("annaluisa@gmail.com"));
		// //System.out.println(new UsuarioDAO().fazLogin("kleiton@gmail.com",
		// "q123"));
		// // u = new UsuarioDAO().buscaUsuarioEmail("kleiton");
		// //System.out.println(u);
		//// try {
		//// converteByteArrayToFile(u.getFotoByte());
		//// } catch (FileNotFoundException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// } catch (IOException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// }
		//inserir();
		//testeAtualizacao();
	//testeBuscar();
		testeBuscaEmail("adfadfaad");
	}
	public static void testeBuscaEmail(String email){
		System.out.println(new UsuarioDAO().buscaUsuarioEmail(email));
	}
	public static void testeBuscar(){
		UsuarioDAO udao = new UsuarioDAO();
		System.out.println("----->"+udao.fazLogin("kleiton@gmail.com", "1234")+"<-----");
	}
	public static void testeAtualizacao() {
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.fazLogin("kleiton@gmail.com", "1234");
		System.out.println("--->"+u);
		Medicamento m = new Medicamento();
		m.setApresentacao("Dorflex 25 mg");
		m.setClasseTerapeutica("Dores diversas");
		m.setCodigoBarras("0987654321");
		m.setLaboratorio("inter-life");
		m.setNomeProduto("Dorflex");
		m.setPrincipioAtivo("principio de dorflex");
		List<Medicamento> ms =  u.getMedicamentos();
		ms.add(m);
		u.setMedicamentos(ms);
		udao.atualizarUsuario(u);
	}
	public static void inserir(){
		Usuario u = new Usuario();
		u.setNome("kleiton");
		u.setEmail("kleiton.a.batista@gmail.com");
		u.setSenha("1234");
		u.setSexo("masculino");
		Medicamento m = new Medicamento();
		m.setApresentacao("remedio");
		m.setCodigoBarras("31243413");
		m.setNomeProduto("algo");
		ArrayList<Medicamento> ms = new ArrayList<>();
		ms.add(m);
		u.setMedicamentos(ms);
		new UsuarioDAO().inseirUsuario(u);
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
