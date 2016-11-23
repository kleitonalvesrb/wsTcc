package br.com.minhafarmacia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.minhafarmacia.beans.Receita;
import br.com.minhafarmacia.beans.Usuario;

public class ReceitaDAO {
	private FactoryCon conexao = new FactoryCon();

	public FactoryCon getConexao() {
		return conexao;
	}

	public void setConexao(FactoryCon conexao) {
		this.conexao = conexao;
	}
	
	
	public Receita buscaReceitaId(Integer id){
		setConexao(new FactoryCon());
		String jpql = "select r from RECEITA r where r.id = :id";
		Query query = getConexao().getManager().createQuery(jpql);
//		query.setParameter(1, id);
		query.setParameter("id", id);
		Receita r = new Receita();
		try {
			r = (Receita)query.getSingleResult();			
		} catch (NoResultException e) {
			return r;
		}finally {
			getConexao().getManager().close();
		}
		return r;
	}
	public void deletaReceita(Receita obj){
		setConexao(new FactoryCon());
		obj = getConexao().getManager().merge(obj);
		getConexao().getManager().getTransaction().begin();
		getConexao().getManager().remove(obj);
		getConexao().getManager().getTransaction().commit();
		getConexao().getManager().close();
	}
	
	public List<Receita> buscaTodasReceitasUsuario(String email){
		setConexao(new FactoryCon());
		String jpql = "Select u from USUARIO u where u.email = ?1";
		Query query = getConexao().getManager().createQuery(jpql);
		query.setParameter(1, email);
		List<Receita> receitas = new ArrayList<Receita>();
		try {
			Usuario u = new Usuario();
			u = (Usuario) query.getSingleResult();
			receitas.addAll(u.getReceitas());
			return receitas;
		} catch (NoResultException e) {
			return receitas;
		}finally {
			getConexao().getManager().close();
		}
	}
}
