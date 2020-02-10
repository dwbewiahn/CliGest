package application.models;
/**
 * Classe do modelo das especialidades, contém id da especialidade e o nome.
 * @author dwbew
 *
 */
public class Especialidade {
	private int id;
	private String name;
	
	public Especialidade(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
