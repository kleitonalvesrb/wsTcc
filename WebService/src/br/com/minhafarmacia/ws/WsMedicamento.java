package br.com.minhafarmacia.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.minhafarmacia.beans.*;
import br.com.minhafarmacia.dao.*;
import br.com.minhafarmacia.util.*;

@Path("/medicamento")
@Produces(MediaType.APPLICATION_JSON)
public class WsMedicamento {
	/**
	 * Método responsavel por atualizar os medicamentos do usuário, recebe o
	 * email do usuário para identificar qual deverá ser atualizado e os dados
	 * do medicamentos
	 * 
	 * @param email
	 * @param medicamento
	 */
	@PUT
	@Path("/atualizar/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarUsuario(@PathParam("email") String email, Medicamento medicamento) {
		System.out.println("--MEDICAMENTO-->"+medicamento);
		System.out.println("--DOSAGEM--->"+medicamento.getDosagem().toString());
		if (medicamento != null && email != null && email.trim() != "") {
			try {
				medicamento.setFotoBytes(Util.converteToByte(medicamento.getFotoMedicamentoString()));
				System.out.println("1 ------> converteu a imagem para bytes");
			} catch (IOException e) {
				System.out.println("Erro ao converter a foto do medicamento");
			}
			UsuarioDAO udao = new UsuarioDAO();
			Usuario u = udao.buscaUsuarioEmail(email);
			System.out.println("vai verificar se encontrou usuario com o email informado");
			if (u != null){
				System.out.println(medicamento);
				List<Medicamento> medicamentos = u.getMedicamentos();
				System.out.println("2 ----> lista de medicamentos antes --->"+medicamentos.size());
				medicamentos.add(medicamento);
				System.out.println("3 ---> lista de medicamentos depois "+medicamentos.size());
				u.setMedicamentos(medicamentos);
				udao.atualizarUsuario(u);
				System.out.println("4 -----> inseriu o medicamento para o usuario");
			}
		}else{
			System.out.println("tem alguma coisa errada nessa merda");
		}
		//return Response.status(Status.UNAUTHORIZED).entity(medicamento).build();
		 
		return Response.ok().entity(medicamento).build();
	}
	
	@GET
	@Path("/busca-medicamento/email-user/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medicamento> buscaMedicamentos(@PathParam("email") String email){
//		System.out.println("----->"+id+"<---");
//		List<Medicamento> medicamentosAux;
//		List<Medicamento> medicamentos = new  ArrayList<>();
//		try {
//			Integer idUsuario = Integer.parseInt(id);
//			 medicamentosAux = new MedicamentoDAO().buscaTodosMedicaemntosUsuarioId(idUsuario);
//			Util util = new Util();
//			for (Medicamento m : medicamentosAux) {
//				System.out.println("tamanho do byte"+m.getFotoBytes().length);
//				medicamentos.add(util.trataDadosMedicamento(m));
//			}
//		} catch (NumberFormatException e) {
//			// TODO: handle exception
//		}
//		System.out.println(medicamentos.size()+"tamanho do array");
//		return medicamentos;
		
		Util util = new Util();
//		System.out.println(" 5 estamos aqui ->> "+email);
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		List<Medicamento> medicamentosAux = new ArrayList<>();
		if (email != ""){
			MedicamentoDAO mdao = new MedicamentoDAO();
			medicamentosAux.addAll(mdao.buscaTodosMedicamentosUsuario(email));
//			System.out.println("6 lista de medicamentos ---->"+medicamentosAux);
			for (Medicamento medicamento : medicamentosAux) {
//				System.out.println("7 dentro do for");
//				System.out.println("8 "+medicamento.getFotoBytes().length+"<---- tamanho da imagem");
				medicamentos.add(util.trataDadosMedicamento(medicamento));
			}
//			System.out.println("9 fora do for");
		}
		return medicamentos;
	}
}

/*
 * 
 * */
