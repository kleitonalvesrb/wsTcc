package br.com.minhafarmacia.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.minhafarmacia.enumeradores.SexoType;

@XmlRootElement
@Entity()
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer idUsuario;
	private Integer idade;
	private String nome;
	private String email;
	private String senha;
	private String foto;
	private byte[] fotoByte;
	private String idFacebook;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String sexo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	
	
	public Usuario() {
	}
	public Usuario(String nome, String email, String senha,String foto, byte[] fotoByte){
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setFoto(foto);
		setFotoByte(fotoByte);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Nome: "+getNome()+" Email: "+getEmail()+" Senha: "+getSenha()+"\n Foto "+getFotoByte()+"Sexo "+getSexo()+" data "+getDataNascimento();
	}
//	public byte[] getFoto() {
//		return foto;
//	}
//	public void setFoto(byte[] foto) {
//		this.foto = foto;
//	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public byte[] getFotoByte() {
		return fotoByte;
	}
	public void setFotoByte(byte[] fotoByte) {
		this.fotoByte = fotoByte;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getIdFacebook() {
		return idFacebook;
	}
	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		System.out.println("------> ESTOU PASSANDO POR AQUI <-------");
		System.out.println("-----> "+dataNascimento+" <------");
		this.dataNascimento = new Date();
	}
	

	

	
}
