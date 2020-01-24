use cligestdb;

## Criar um novo medico (exemplo de criacao de novo medico, no java serao usadas variaveis) ##
insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Rodrigo Alves',54320,888888883,'rodrigoalves@gmail.com','Av. da Liberdade 240',8888-883);

## Criar um novo paciente (exemplo de criacao de novo paciente, no java serao usadas variaveis) ##
insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Silvana Albuquerque',100004,'Av. da Republica, 4',1050000,'21/12/2001','Feminino',999999994,'silvanaalbuquerque@gmail.com');  	

## Selecionar Agendamentos para uma data especifica (usei dia 16/12/2019 como exemplo mas no java sera uma variavel) ##
select * from agendamento as agenda where agendamento_data = '16/12/2019';

## Selecionar Agendamentos para uma medico especifico ? (use medico 2 como exemplo mas no java sera usado uma variavel) ##
select * from agedamento as agenda_medico where Medico_id_medico = 2;

## Selecionar Agendamentos para uma paciente especifico ? (usei paciente 1 como exemplo mas no java sera usado uma variavel) ##
select * from agedamento as agenda_medico where Paciente_id_paciente = 1;

## Selecionar a especialidade de um medico em um agendamento especifico (usei o agendamento 1 como exemplo )(nao implementado)##
select Especialidade_id_especialidade from ((medico_has_especialidade inner join medico on medico_has_especialidade.Medico_id_medico = medico.id_medico) inner join agendamento on agendamento.Medico_id_medico = medico.id_medico) where id_agendamento = 1;

## Quantidade de agendamentos hoje para um medico especifico ( usei medico 1 como exemplo mas no java sera uma variavel) (nao implementado)##
select count(*) from agendamento where agendamento_data = '16/12/2019' and Medico_id_medico = 1;


