package br.com.minhafarmacia.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.minhafarmacia.beans.Medicamento;
import br.com.minhafarmacia.beans.Receita;
import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.MedicamentoDAO;
import br.com.minhafarmacia.dao.ReceitaDAO;
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
				receitas.addAll(user.getReceitas());
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
	@GET
	@Path("/busca-receita/email-user/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Receita> buscaMedicamentosUsuario(@PathParam("email") String email){

		List<Receita> receitas = new ArrayList<Receita>();
		List<Receita> receitasAux = new ArrayList<>();
		if (email != ""){
			ReceitaDAO rdao = new ReceitaDAO();
			receitasAux.addAll(rdao.buscaTodasReceitasUsuario(email));
			for (Receita receita : receitasAux) {
				receitas.add(Util.trataDadosReceita(receita));
			}
		}
		return receitas;
	}
}
// if(email != null && !email.trim().isEmpty()){
// UsuarioDAO udao = new UsuarioDAO();
// Usuario u = udao.buscaUsuarioEmail(email);
// List<Receita> receitas = new ArrayList<Receita>();
// List<Receita> receitasAux = new ArrayList<>();
// receitasAux.addAll()
// for(Receita receita : receitasAux){
// receitas.add(Util.trataDadosReceita(receita));
// System.out.println(receita.getDataCadastroReceita()+"<<<<<<<<<<<");
// }
// return receitas;
// }
// return new ArrayList<Receita>();
/*
 * System.out.println(" 5 estamos aqui ->> "+email); List<Medicamento>
 * medicamentos = new ArrayList<Medicamento>(); List<Medicamento>
 * medicamentosAux = new ArrayList<>(); if (email != ""){ MedicamentoDAO mdao =
 * new MedicamentoDAO();
 * medicamentosAux.addAll(mdao.buscaTodosMedicamentosUsuario(email)); //
 * System.out.println("6 lista de medicamentos ---->"+medicamentosAux); for
 * (Medicamento medicamento : medicamentosAux) { //
 * System.out.println("7 dentro do for"); //
 * System.out.println("8 "+medicamento.getFotoBytes().
 * length+"<---- tamanho da imagem");
 * medicamentos.add(util.trataDadosMedicamento(medicamento)); }
 */
