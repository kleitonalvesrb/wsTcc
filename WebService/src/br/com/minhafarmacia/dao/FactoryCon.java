package br.com.minhafarmacia.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class FactoryCon {
	private EntityManager manager;
	
	public FactoryCon(){
		setManager(Persistence.createEntityManagerFactory("WebService").createEntityManager());
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
