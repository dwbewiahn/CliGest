package application.models;

/**
 * Classe do modelo das especialidades, cont�m id da seguradora e o nome.
 * @author dwbew
 *
 */

public class Seguradora {

	private int id;
	private String nome;
	
	
	public Seguradora(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Seguradora(int id) {
		this.id = id;
		this.nome = "Nome Seguradora";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String toString() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
