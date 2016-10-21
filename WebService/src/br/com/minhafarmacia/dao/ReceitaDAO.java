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
