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

import br.com.minhafarmacia.beans.Medicamento;
import br.com.minhafarmacia.beans.Usuario;
import br.com.minhafarmacia.dao.MedicamentoDAO;
import br.com.minhafarmacia.dao.UsuarioDAO;
import br.com.minhafarmacia.util.Util;

@Path("/medicamento")
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
	public void atualizarUsuario(@PathParam("email") String email, Medicamento medicamento) {
		if (medicamento != null && email != null && email.trim() != "") {
			try {
				medicamento.setFotoBytes(Util.converteToByte(medicamento.getFotoMedicamentoString()));
			} catch (IOException e) {
				System.out.println("Erro ao converter a foto do medicamento");
			}
			UsuarioDAO udao = new UsuarioDAO();
			Usuario u = udao.buscaUsuarioEmail(email);
			if (u != null){
				List<Medicamento> medicamentos = u.getMedicamentos();
				medicamentos.add(medicamento);
				u.setMedicamentos(medicamentos);
				udao.atualizarUsuario(u);
			}
		}
	}
	
	@GET
	@Path("/busca-medicamento/user-email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medicamento> buscaMedicamentos(@PathParam("email") String email){
		Util util = new Util();
		System.out.println("estamos aqui ->> "+email);
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		List<Medicamento> medicamentosAux = new ArrayList<>();
		if (email != ""){
			MedicamentoDAO mdao = new MedicamentoDAO();
			medicamentosAux.addAll(mdao.buscaTodosMedicamentosUsuario(email));
			for (Medicamento medicamento : medicamentosAux) {
				medicamentos.add(util.trataDadosMedicamento(medicamento));
			}
		}
		return medicamentos;
	}
}
