-- Populate Mockup information for CliGest DB 
use cligestdb;

insert into especialidade(especialidade) values ('Geral');
insert into especialidade(especialidade) values ('Cardiologia');
insert into especialidade(especialidade) values ('Dermatologia');
insert into especialidade(especialidade) values ('Endocrinologia');
insert into especialidade(especialidade) values ('Ortopedia');

insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Rodrigo Souza',54321,888888888,'rodrigosouza@gmail.com','Av. da Liberdade 245',8888-888);
insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Marta Alvares',54322,888888887,'martaalvares@gmail.com','Av. da Liberdade 244',8888-887);
insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Ines Cabral',54323,888888886,'inescabral@gmail.com','Av. da Liberdade 243',8888-886);
insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Pedro Silva',54324,888888885,'pedrosilva@gmail.com','Av. da Liberdade 242',8888-885);
insert into medico(nome_medico,crm,telefone,email,morada,cod_postal) values ('Goncalo Carvalho',54325,888888884,'goncalocarvalho@gmail.com','Av. da Liberdade 241',8888-884);

insert into medico_has_especialidade(Medico_id_medico,Especialidade_id_especialidade) values (1,2);
insert into medico_has_especialidade(Medico_id_medico,Especialidade_id_especialidade) values (2,1);
insert into medico_has_especialidade(Medico_id_medico,Especialidade_id_especialidade) values (3,3);
insert into medico_has_especialidade(Medico_id_medico,Especialidade_id_especialidade) values (4,5);
insert into medico_has_especialidade(Medico_id_medico,Especialidade_id_especialidade) values (5,4);

insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Lucas Silva',100001,'Av. da Republica, 1',1050000,'26/10/1986','Masculino',999999999,'lucassilva@gmail.com');
insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Carlos Lopes',100002,'Av. da Republica, 2',1050000,'11/07/1989','Masculino',999999998,'carloslopes@gmail.com');
insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Paula Monteiro',100003,'Av. da Republica, 3',1050000,'19/11/1991','Feminino',999999997,'paulamonteiro@gmail.com');
insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Juliana Siqueira',100004,'Av. da Republica, 4',1050000,'14/03/1995','Feminino',999999996,'julianasiqueira@gmail.com');
insert into paciente (nome,contribuinte,morada,cod_postal,nascimento,sexo,telefone,email) 
	values ('Sueli Gomes',100005,'Av. da Republica, 5',1050000,'21/12/2005','Feminino',999999995,'sueligomes@gmail.com');    
    

insert into seguradora(nome_seguradora) values ('Allianz');
insert into seguradora(nome_seguradora) values ('AdvanceCare');
insert into seguradora(nome_seguradora) values ('Multicare');
insert into seguradora(nome_seguradora) values ('Fidelidade');
insert into seguradora(nome_seguradora) values ('MAPFRE');

insert into paciente_has_seguradora(Paciente_id_paciente,Seguradora_id_seguradora) values (1,2);
insert into paciente_has_seguradora(Paciente_id_paciente,Seguradora_id_seguradora) values (2,1);
insert into paciente_has_seguradora(Paciente_id_paciente,Seguradora_id_seguradora) values (3,4);
insert into paciente_has_seguradora(Paciente_id_paciente,Seguradora_id_seguradora) values (4,3);
insert into paciente_has_seguradora(Paciente_id_paciente,Seguradora_id_seguradora) values (5,5);



insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('16/12/2019','10:30',2,1); 
insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('16/12/2019','10:45',1,1); 
insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('16/12/2019','11:00',3,2); 
insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('16/12/2019','11:15',4,3); 
insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('17/12/2019','11:15',4,3); 
insert into agendamento(agendamento_data,agendamento_hora,Paciente_id_paciente,Medico_id_medico) values ('17/12/2019','11:15',5,4); 
	
commit;
          