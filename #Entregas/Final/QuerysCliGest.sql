
use XLuUja9EDK;


############################################################################################################################################################
###### Criar um novo medico (exemplo de criacao de novo medico, no java serao usadas variaveis) ############################################################
############################################################################################################################################################

insert into medico(nome_medico,sexo,telefone,email,morada,cod_postal) 
	values ('Rodrigo Souza','Masculino',888888888,'rodrigosouza@gmail.com','Av. da Liberdade 245',8888888);

############################################################################################################################################################
###### Criar um novo paciente (exemplo de criacao de novo paciente, no java serao usadas variaveis) ########################################################
############################################################################################################################################################

insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Lucas Silva',100001,'Av. da Republica, 1',1050000,'26-10-1986','Masculino',999999999,'lucassilva@gmail.com');

############################################################################################################################################################
###### SELECIONAR O CAMPO CONFIRMADO DE UM AGENDAMENTO ESPECIFICO PARA UM MEDICO ESPECIFICO, PARA CONFIRMAR O STATUS DO AGENDAMENTO ########################
############################################################################################################################################################

SELECT confirmado FROM agendamento WHERE Medico_id_medico = 1 AND agendamento_data = '27-12-2019' AND agendamento_hora ='10:00'; 

############################################################################################################################################################
###### VERIFICAR AGENDAMENTO PARA UM MEDICO ESPECIFICO EM UMA DATA E HORA ESPECIFICA #######################################################################
############################################################################################################################################################

SELECT id_agendamento, agendamento_hora, agendamento_data, Paciente_id_paciente,Medico_id_medico, confirmado, Especialidade_id_especialidade, especialidade 
	FROM agendamento INNER JOIN especialidade ON Especialidade_id_especialidade = id_especialidade 
	WHERE Medico_id_medico = 1 AND agendamento_data = '27-12-2019' AND agendamento_hora = '10:00' ;


############################################################################################################################################################
###### ALTERAR O ESTADO DO AGENDAMENTO PARA CONFIRMADO. UTILIZEI O AGENDAMENTO 1 COMO EXEMPLO, NO JAVA SERAO USADAS VARIAVEIS.   ###########################
############################################################################################################################################################


UPDATE agendamento SET confirmado=1 WHERE id_agendamento= 1;


############################################################################################################################################################
###### DELETAR UM AGENDAMENTO.  UTILIZEI O AGENDAMENTO 1 COMO EXEMPLO, NO JAVA SERAO USADAS VARIAVEIS.  ####################################################
############################################################################################################################################################

DELETE FROM agendamento WHERE id_agendamento = 1;

############################################################################################################################################################
###### SELECIONAR AS ESPECIALIDADES DE UM MEDICO ESPECIFICO, UTILIZEI O MEDICO 1 COMO EXEMPLO, NO JAVA SERAO USADAS VARIAVEIS.##############################
############################################################################################################################################################

SELECT id_especialidade, especialidade FROM medico_has_especialidade
	INNER JOIN especialidade ON Especialidade_id_especialidade = id_especialidade 
	WHERE Medico_id_medico = 1;

############################################################################################################################################################
###### SELECIONAR LISTA DE MEDICOS COM SUAS RESPECTIVAS ESPECIALIDADES E AGRUPAR POR ID DOS MEDICOS.########################################################
############################################################################################################################################################

SELECT id_medico, nome_medico, telefone, email, morada, cod_postal, sexo, GROUP_CONCAT(id_especialidade), GROUP_CONCAT(especialidade) 
	FROM medico INNER JOIN medico_has_especialidade ON medico_id_medico = id_medico 
	INNER JOIN especialidade ON especialidade_id_especialidade = id_especialidade GROUP BY id_medico;



############################################################################################################################################################
###### SELECIONAR LISTA DE PACIENTES COM SUAS RESPECTIVAS SEGURADORAS E AGRUPAR POR ID DO PACIENTE.#########################################################
############################################################################################################################################################

SELECT id_paciente, nome, telefone, sexo, email, morada, cod_postal,nascimento, contribuinte,  GROUP_CONCAT(id_seguradora), GROUP_CONCAT(nome_seguradora) 
	FROM paciente INNER JOIN paciente_has_seguradora ON paciente_id_paciente = Seguradora_id_seguradora
	INNER JOIN seguradora ON Seguradora_id_seguradora = id_seguradora GROUP BY id_paciente;




############################################################################################################################################################
###### Selecionar a especialidade de um medico em um agendamento especifico (usei o agendamento 1 como exemplo )(nao implementado) #########################
############################################################################################################################################################

select Especialidade_id_especialidade from ((medico_has_especialidade inner join medico on medico_has_especialidade.Medico_id_medico = medico.id_medico) 
inner join agendamento on agendamento.Medico_id_medico = medico.id_medico) where id_agendamento = 1;




