package br.com.minhafarmacia.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.minhafarmacia.beans.Usuario;

public class UsuarioDAO {
	
	/**
	 * Método responsavel por inserir um usuario no banco. O usuário vem da aplicação mobile
	 * @param user
	 */
	private FactoryCon conexao = new FactoryCon();
	public FactoryCon getConexao() {
		return conexao;
	}
	public void setConexao(FactoryCon conexao) {
		this.conexao = conexao;
	}
	public void inseirUsuario(Usuario user){
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().persist(user);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
	}
	public Usuario buscaUsuarioIdFacebook(String idFacebook){
		setConexao(new FactoryCon());
		String jpql = "select u from USUARIO u where u.idFacebook = :idFacebook";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter("idFacebook",idFacebook);
		try{
			Usuario u = (Usuario) query.getSingleResult();
			return u;
		}catch (NoResultException e) {
			return null;
		}finally {
			getConexao().getManager().close();
		}
	}
	/**
	 * Método responsavel por buscar o usuario no banco de dados pelo seu id
	 * @param id
	 * @return usuario completo ou null caso nao encontre nenhum usuario
	 */
	public Usuario buscaUsurioId(int id){
		setConexao(new FactoryCon());
		String jpql = "select u from USUARIO u where u.idUsuario = :idUsuario";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter("idUsuario", id);
		try {
			Usuario u = (Usuario) query.getSingleResult();
			return u;
		} catch (NoResultException e) {
			return null;
		}finally {
			getConexao().getManager().close();
		}
	}
	/**
	 * Método responsavel por atualizar os dados do usuário, ele receberá um usuário
	 * completo e irá atualizar o que está de diferente em seu conteúdo
	 * @param user
	 */
	public void atualizarUsuario(Usuario user){
		setConexao(new FactoryCon());
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().merge(user);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
	}
	
	public boolean verificaExistencia(String email){
		setConexao(new FactoryCon());
		String jpql = "select u from USUARIO u where u.email = ?1";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter(1, email);
		boolean flag = query.getResultList().size() > 0;
		getConexao().getManager().close();
		return flag;
	}
	
	/**
	 * Busca o usuário com os critérios de email e senha para realizar a autenticação do login
	 * @param email
	 * @param senha
	 * @return
	 */
	public Usuario fazLogin(String email, String senha){
		setConexao(new FactoryCon());
		String jpql = "select u from USUARIO u where u.email = ?1 and u.senha = ?2";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, senha);
		
		try{
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		}catch (NoResultException e) {
			return null;
		}finally {
			getConexao().getManager().close();
			System.out.println("entrou no finally para encerrar");
		}
	}
	/**
	 * Busca o usuario na base de dados por email
	 * @param email
	 * @return
	 */
	public Usuario buscaUsuarioEmail(String email){
		setConexao(new FactoryCon());
		String jpql = "Select u from USUARIO u where u.email = ?1";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter(1, email);
		try {
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}finally {
			getConexao().getManager().close();
		}
		
	}
	public boolean atualizaNomeUsuario(Usuario user){
		setConexao(new FactoryCon());
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().merge(user);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
		return true;
	}
	/**
	 * Atualiza o nome do usuario, busca pelo email
	 * @param email
	 * @return true ou false
	 */
	public boolean atualizaNomeUsuario(String email, String novoNome){
		Usuario u = buscaUsuarioEmail(email);
		if (u != null){
			System.out.println("OI");
			u.setNome(novoNome);
			System.out.println("Novo nome "+u.getNome());
			setConexao(new FactoryCon());
			getConexao().getManager().getTransaction().begin();
			getConexao().getManager().merge(u);
			getConexao().getManager().getTransaction().commit();
			getConexao().getManager().close();
			return true;
		}else{
			System.out.println("nao achou");
			return false;
		}
	}
	/**
	 * Atualziar a senha do usuario
	 * @param user
	 */
	public void atualizarSenhaUsuario(Usuario user){
		setConexao(new FactoryCon());
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().merge(user);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
	}
	/**
	 * Atualixar a foto de perfil
	 * @param user
	 */
	public void atualizarFotoPerfilUsuario(Usuario user){
		setConexao(new FactoryCon());
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().merge(user);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
	}
	
}
