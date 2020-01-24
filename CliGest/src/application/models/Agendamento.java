package application.models;

public class Agendamento {

	private int id;
	private String dataHora;
	private Paciente paciente;
	private Medico medico;
	private Especialidade especialidade;
	private boolean confirmado;
	
	public Agendamento(int id, String dataHora, Paciente paciente, Medico medico, Especialidade especialidade, boolean confirmado) {
		this.id = id;
		this.dataHora = dataHora;
		this.paciente = paciente;
		this.medico = medico;
		this.especialidade = especialidade;
		this.setConfirmado(confirmado);
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	
}
