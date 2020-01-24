package application.models;

public abstract class Pessoa {

	private int id, telefone;
	private String nome, sexo, morada, codigoPostal, email;
	private String dataNascimento;
	
	public Pessoa(int id, int telefone, String nome, String sexo, String morada, String codigoPostal, String email,
			String dataNascimento) {
		this.id = id;
		this.telefone = telefone;
		this.nome = nome;
		this.sexo = sexo;
		this.morada = morada;
		this.codigoPostal = codigoPostal;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String toString() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
