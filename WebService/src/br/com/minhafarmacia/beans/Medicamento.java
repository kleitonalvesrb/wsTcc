package br.com.minhafarmacia.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "MEDICAMENTO")
@SequenceGenerator(name = "MEDICAMENTO_SEQUENCE", sequenceName = "MEDICAMENTO_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Medicamento implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICAMENTO_SEQUENCE")
	private Integer id;
	@Column(length = 45, name = "cod_barras")
	private String codigoBarras;
	@Column(length = 100, name = "nome")
	private String nomeProduto;
	@Column(length = 100, name = "principio_ativo")
	private String principioAtivo;
	@Column(length = 255, name = "apresentacao")
	private String apresentacao;
	@Column(length = 100, name = "laboratorio")
	private String laboratorio;
	@Column(length = 100, name = "classe_terapeutica")
	private String classeTerapeutica;
	@Transient
	private String fotoMedicamentoString;
	@Column(name = "foto")
	private byte [] fotoBytes;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn()
	private Dosagem dosagem;
    
    public Dosagem getDosagem() {
		return dosagem;
	}



	public void setDosagem(Dosagem dosagem) {
		this.dosagem = dosagem;
	}



	public Medicamento() {
	}
    
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	




	public String getFotoMedicamentoString() {
		return fotoMedicamentoString;
	}



	public void setFotoMedicamentoString(String fotoMedicamentoString) {
		this.fotoMedicamentoString = fotoMedicamentoString;
	}



	public byte[] getFotoBytes() {
		return fotoBytes;
	}






	public void setFotoBytes(byte[] fotoBytes) {
		this.fotoBytes = fotoBytes;
	}



	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getPrincipioAtivo() {
		return principioAtivo;
	}
	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
	public String getApresentacao() {
		return apresentacao;
	}
	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getClasseTerapeutica() {
		return classeTerapeutica;
	}
	public void setClasseTerapeutica(String classeTerapeutica) {
		this.classeTerapeutica = classeTerapeutica;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "codigo "+getCodigoBarras()+" nome"+getNomeProduto();
	}
	
}
