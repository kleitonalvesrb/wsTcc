package br.com.minhafarmacia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.minhafarmacia.beans.Medicamento;
import br.com.minhafarmacia.beans.Usuario;

public class MedicamentoDAO {
	/**
	 * método responsavel por buscar todos os medicamentos de um usuario
	 * @param idUsuario
	 * @return lista de medicamentos
	 */
	
	public List<Medicamento> buscaTodosMedicamentosUsuario(String email){
		String jpql = "Select u from Usuario u where u.email = ?1";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, email);
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		try {
			Usuario u = new Usuario();
			u = (Usuario) query.getSingleResult();
			medicamentos.addAll(u.getMedicamentos());
			return medicamentos;
		} catch (NoResultException e) {
			return medicamentos;
		}
	}
	/*public List<Medicamento> buscaTodosMedicamentosUsuario(Integer idUsuario){
//		String jpql = "Select u from Usuario u where u.idusuario = ?1";
//select c FROM News c WHERE c.newsId = :id
		String jpql = "Select m from Medicamento m where m.usuario_id = ?1";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, idUsuario);
		List<Medicamento> medicamentos = query.getResultList();
		return medicamentos;
	}*/
}
