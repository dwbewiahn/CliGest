package application.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe do modelo dos pacientes que herda parametros da classe pessoa, contém os dados do paciente como : nome, telefone, sexo, morada, codigo postal,
 * email, data de nascimento, seguradoras contratadas e o numero do contribuinte.
 * @author dwbew
 *
 */

public class Paciente extends Pessoa{

	private ObservableList<Seguradora> seguradoras = FXCollections.observableArrayList();

	private int contribuinte;
	
	public Paciente(int id, int telefone, String nome, String sexo, String morada, String codigoPostal, String email,
			String dataNascimento, ObservableList<Seguradora> seguradoras, int contribuinte) {
		super(id, telefone, nome, sexo, morada, codigoPostal, email, dataNascimento);
		this.seguradoras = seguradoras;
		this.setContribuinte(contribuinte);
	}

	public ObservableList<Seguradora> getSeguradoras() {
		return seguradoras;
	}

	public void setSeguradoras(ObservableList<Seguradora> seguradoras) {
		this.seguradoras = seguradoras;
	}

	public int getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(int contribuinte) {
		this.contribuinte = contribuinte;
	}
}