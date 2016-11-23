package br.com.minhafarmacia.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.minhafarmacia.util.Util;

@XmlRootElement
@Entity(name = "RECEITA")
@Cacheable(false)
@SequenceGenerator(name = "RECEITA_SEQUENCE", sequenceName = "RECEITA_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Receita implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEITA_SEQUENCE")
	@Column(name = "id_receita", nullable = false)
	private Integer id;
	@Lob 
	@Column(name="descricao", length = 512, nullable = true)
	private String descricao;
	@Transient
	private String fotoReceitaString;
	
	@Column(name = "foto", nullable = false)
	private byte[] fotoReceta;
	
	@Transient
	private String dataCadastroReceitaString;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastroReceita;
	public Receita(){
		
	}
	
	
	
	public String getDataCadastroReceitaString() {
		return dataCadastroReceitaString;
	}



	public void setDataCadastroReceitaString(String dataCadastroReceitaString) {
		setDataCadastroReceita(Util.converteStringToDate(Util.trataStringDataFormatoAmericano(dataCadastroReceitaString)));
		this.dataCadastroReceitaString = dataCadastroReceitaString;
	}



	public Date getDataCadastroReceita() {
		return dataCadastroReceita;
	}



	public void setDataCadastroReceita(Date dataCadastroReceita) {
		this.dataCadastroReceita = dataCadastroReceita;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFotoReceitaString() {
		return fotoReceitaString;
	}
	public void setFotoReceitaString(String fotoReceitaString) {
		this.fotoReceitaString = fotoReceitaString;
	}
	public byte[] getFotoReceta() {
		return fotoReceta;
	}
	public void setFotoReceta(byte[] fotoReceta) {
		this.fotoReceta = fotoReceta;
	}
	@Override
	public String toString() {
		return "ID "+getId()+" Descricao "+getDescricao();
	}
	
	
}
