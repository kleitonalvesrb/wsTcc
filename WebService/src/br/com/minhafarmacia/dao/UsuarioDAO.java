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
	/**
	 * Método responsavel por buscar o usuario no banco de dados pelo seu id
	 * @param id
	 * @return usuario completo ou null caso nao encontre nenhum usuario
	 */
	public Usuario buscaUsurioId(int id){
		String jpql = "select u from usuario u where u.idusuario = ?1";
		Query query = new FactoryCon().getManager().createQuery(jpql);
		query.setParameter(1, 1);
		try {
			Usuario u = (Usuario) query.getSingleResult();
			return u;
		} catch (NoResultException e) {
			return null;
		}
	}
	/**
	 * Método responsavel por atualizar os dados do usuário, ele receberá um usuário
	 * completo e irá atualizar o que está de diferente em seu conteúdo
	 * @param user
	 */
	public void atualizarUsuario(Usuario user){
		FactoryCon con = new FactoryCon();
		con.getManager().getTransaction().begin();
		con.getManager().merge(user);
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
		try {
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
		
	}
}
