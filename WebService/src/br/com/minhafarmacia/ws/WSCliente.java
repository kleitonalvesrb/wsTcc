package br.com.minhafarmacia.ws;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.UsuarioDAO;
import br.com.minhafarmacia.util.Util;

@Path("/cliente")
public class WSCliente {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {
		// crio usuario
		Usuario u = new UsuarioDAO().buscaUsuarioEmail("kleiton.a.batista1@gmail.com");

		return u;
	}
	@GET
	@Path("/consulta-email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario consultaUsuarioEmail(@PathParam("email") String email){
		
		return null;
	}
	/**
	 * Método responsavel para buscar o usuario no banco com os parametos email
	 * e senha
	 * 
	 * @param email
	 * @param senha
	 * @return usuario
	 */
	@GET
	@Path("/login/{email}-{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario efetuaLogin(@PathParam("email") String email, @PathParam("senha") String senha) {
		System.out.println("chegou aqui com email "+email+" senha "+senha);
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = new Usuario();
		u = udao.fazLogin(email, senha);
		//System.out.println("------>"+u.getNome());
		if (u != null)
			u = Util.trataDadosUsuario(u);

		return u;
	}

	/**
	 * Método responsavel por inserir usuario no banco de dados
	 * 
	 * @param usuario
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insereUsuario(Usuario usuario){
				
		if (usuario != null) {
			try {
				usuario.setFotoByte(Util.converteToByte(usuario.getFoto()));
				usuario.setFoto("true");
				usuario.setDataCadastro(new Date());
				System.out.println(usuario.getDataNascimento()+"<----------");
				new UsuarioDAO().inseirUsuario(usuario);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

//	public byte[] converteToByte(String str) throws FileNotFoundException, IOException {
//		byte[] bytes;
//		bytes = str.getBytes("UTF-8");
//		byte[] decoded = Base64.getDecoder().decode(str);
//		System.out.println(decoded);
//		System.out.println(decoded.length);
//		converteByteArrayToFile(decoded);
//		return decoded;
//	}
//
//	public static void converteByteArrayToFile(byte[] bytes) throws FileNotFoundException, IOException {
//		FileOutputStream fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/img.pdf"));
//		fos.write(bytes);
//		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.png"));
//		fos.write(bytes);
//		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.jpg"));
//		fos.write(bytes);
//		fos = new FileOutputStream(new File("/Users/kleitonbatista/Desktop/imgnow.jpeg"));
//		fos.write(bytes);
//	}
}
