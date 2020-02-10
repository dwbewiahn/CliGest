package application.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe do modelo dos medicos que herda dados da classe pessoa, contém os dados do medico como : nome, telefone, sexo, morada, codigo postal,
 * email, data de nascimento, especialidades e os horarios marcados.
 * @author dwbew
 *
 */

public class Medico extends Pessoa{

	private ObservableList<Especialidade> especialidades;
	private ObservableList<String> horariosMarcados;
	
	public Medico(int id, String nome, int telefone, String sexo, String morada, String codigoPostal, String email,
			String dataNascimento, ObservableList<Especialidade> especialidades) {
		super(id, telefone, nome, sexo, morada, codigoPostal, email, dataNascimento);
		this.especialidades = especialidades;
		this.horariosMarcados = FXCollections.observableArrayList();
	}
	
	public Medico(int id, String nome, int telefone, String sexo, String morada, String codigoPostal, String email,
			String dataNascimento, ObservableList<Especialidade> especialidades, ObservableList<String> horariosMarcados) {
		super(id, telefone, nome, sexo, morada, codigoPostal, email, dataNascimento);
		this.especialidades = especialidades;
		this.horariosMarcados = horariosMarcados;
	}

	public ObservableList<Especialidade> getEspecialidades() {
		return especialidades;
	}
	
	public void setEspecialidadades(ObservableList<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public ObservableList<String> getHorariosMarcados() {
		return horariosMarcados;
	}

	public void setHorariosMarcados(ObservableList<String> horariosMarcados) {
		this.horariosMarcados = horariosMarcados;
	}

	

}
