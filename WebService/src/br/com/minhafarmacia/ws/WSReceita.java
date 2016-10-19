package br.com.minhafarmacia.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.minhafarmacia.beans.Receita;
import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.UsuarioDAO;
import br.com.minhafarmacia.util.Util;

@Path("/receita")
@Produces(MediaType.APPLICATION_JSON)
public class WSReceita {
	@PUT
	@Path("/atualizar/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarReceitaUsuario(@PathParam("email") String email, Receita receita){
		if (receita != null && email != null && email.trim() != null){
			UsuarioDAO udao = new UsuarioDAO();
			Usuario user = udao.buscaUsuarioEmail(email);
			if(user!= null){
				try {
					receita.setFotoReceta(Util.converteToByte(receita.getFotoReceitaString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<Receita> receitas = new ArrayList<Receita>();
				receitas.add(receita);
				user.setReceitas(receitas);
				udao.atualizarUsuario(user);
				return Response.status(200).build();
			}else{
				return Response.status(404).build();
			}
			
		}else{
			return Response.status(400).build();
		}
	}
}
