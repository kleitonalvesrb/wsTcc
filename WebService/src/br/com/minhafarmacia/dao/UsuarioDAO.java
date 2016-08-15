package br.com.minhafarmacia.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.minhafarmacia.beans.Usuario;

public class UsuarioDAO {
	
	/**
	 * Método responsavel por inserir um usuario no banco. O usuário vem da aplicação mobile
	 * @param user
	 */
	public void inseirUsuario(Usuario user){
		FactoryCon con = new FactoryCon();
		con.getManager().getTransaction().begin();
		con.getManager().persist(user);
		con.getManager().getTransaction().commit();
	}
	
	public boolean verificaExistencia(String email){
		String jpql = "select u from Usuario u where u.email = ?1";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, email);
		return query.getResultList().size() > 0;
	}
	
	/**
	 * Busca o usuário com os critérios de email e senha para realizar a autenticação do login
	 * @param email
	 * @param senha
	 * @return
	 */
	public Usuario fazLogin(String email, String senha){
		String jpql = "select u from Usuario u where u.email = ?1 and u.senha = ?2";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, senha);
		
		try{
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		}catch (NoResultException e) {
			return null;
		}
	}
	/**
	 * Busca o usuario na base de dados por email
	 * @param email
	 * @return
	 */
	public Usuario buscaUsuarioEmail(String email){
		String jpql = "Select u from Usuario u where u.email = ?1";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, email);
		Usuario user = (Usuario) query.getSingleResult();
		return user;
	}
}
