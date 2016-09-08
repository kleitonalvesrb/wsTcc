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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.UsuarioDAO;
import br.com.minhafarmacia.email.Email;
import br.com.minhafarmacia.util.Util;

@Path("/cliente")
public class WSCliente {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {
		// crio usuario

		return new Usuario();
	}

	/**
	 * Método responsavel por recuperar a senha do usuário, irá verificar se
	 * existe usuario com o email informado, caso exista irá trazer rodos os
	 * dados do usuário solicitado enviará a senha para o email juntamente com a
	 * data da solicitação
	 * 
	 * @param email
	 * @return O retorno será apenas para validação na parte de quem solicita,
	 *         caso nao exista dados com o email informado o usuário sera
	 *         retornado como vazio, caso exista será retornado usuário com o
	 *         email preenchido
	 */
	@GET
	@Path("/recupera-senha/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario recuperaSenhaUsuario(@PathParam("email") String email) {
		Usuario u = new Usuario();
		UsuarioDAO uDao = new UsuarioDAO();
		// Se encontrar usuario com o email solicitado buscara a senha e enviará
		// o email
		if (uDao.verificaExistencia(email)) {
			Usuario usuarioRecuperado = new Usuario(); // Usuario recuperado no
														// banco
			usuarioRecuperado = uDao.buscaUsuarioEmail(email);// usuario
																// recuperado do
																// banco
																// preenchido

			// Configurando o envio do email
			Email emailRecuperacao = new Email();
			emailRecuperacao.setAssunto("Recuperação de Senha");
			emailRecuperacao.setDestinatario(email);
			emailRecuperacao.setMsg(usuarioRecuperado.getSenha());
			emailRecuperacao.SendEmail();

			u.setEmail(email); // preenche o usuario apenas com o email e
								// retornar para o app para confirmar que o
								// email solicitado existe
		} else {
			u.setEmail("");
		}
		return u;
	}

	/**
	 * Consulta a existencia de usuário que já tenha email cadastrado no banco
	 * de dados ira retornar um usuario apenas com o email caso o usuário ja
	 * esteja cadastrado ou retornará um usuário com o email prenchido com
	 * string vazia caso não tenha cadastrado no banco
	 * 
	 * @param email
	 * @return Retornará o usuario vazio caso não seja encontrado ou usuario
	 *         preenchido apenas com o email caso seja encontrado
	 */
	@GET
	@Path("/consulta-email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario consultaUsuarioEmail(@PathParam("email") String email) {
		System.out.println("---->" + email);
		Usuario u = new Usuario();
		UsuarioDAO uDao = new UsuarioDAO();
		if (uDao.verificaExistencia(email))
			u.setEmail(email);
		else
			u.setEmail("");
		return u;
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
		System.out.println("chegou aqui com email " + email + " senha " + senha);
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = new Usuario();
		u = udao.fazLogin(email, senha);
		// System.out.println("------>"+u.getNome());
		if (u != null){
			u = Util.trataDadosUsuario(u);
			u.setMedicamentos(null);
		}

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
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereUsuario(Usuario usuario) {
		System.out.println("método inserir <--------------");
		if (usuario != null) {
			try {
				usuario.setFotoByte(Util.converteToByte(usuario.getFoto()));
				usuario.setFoto("true");
				usuario.setDataCadastro(new Date());
				System.out.println(usuario.getDataNascimento() + "<----------");
				new UsuarioDAO().inseirUsuario(usuario);
				System.out.println(Response.ok().entity(usuario).build());
				return Response.ok().entity(usuario).build();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Response.noContent().build());
//		Response.status(Status.UNAUTHORIZED).entity(arg0)
		return Response.noContent().build();

	}

	// public byte[] converteToByte(String str) throws FileNotFoundException,
	// IOException {
	// byte[] bytes;
	// bytes = str.getBytes("UTF-8");
	// byte[] decoded = Base64.getDecoder().decode(str);
	// System.out.println(decoded);
	// System.out.println(decoded.length);
	// converteByteArrayToFile(decoded);
	// return decoded;
	// }
	//
	// public static void converteByteArrayToFile(byte[] bytes) throws
	// FileNotFoundException, IOException {
	// FileOutputStream fos = new FileOutputStream(new
	// File("/Users/kleitonbatista/Desktop/img.pdf"));
	// fos.write(bytes);
	// fos = new FileOutputStream(new
	// File("/Users/kleitonbatista/Desktop/imgnow.png"));
	// fos.write(bytes);
	// fos = new FileOutputStream(new
	// File("/Users/kleitonbatista/Desktop/imgnow.jpg"));
	// fos.write(bytes);
	// fos = new FileOutputStream(new
	// File("/Users/kleitonbatista/Desktop/imgnow.jpeg"));
	// fos.write(bytes);
	// }
}
