CREATE SCHEMA IF NOT EXISTS XLuUja9EDK DEFAULT CHARACTER SET utf8 ;
USE XLuUja9EDK ;

-- -----------------------------------------------------
-- Table XLuUja9EDK.`paciente` -- ok
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS XLuUja9EDK.`paciente` (
`id_paciente` int NOT NULL AUTO_INCREMENT,
`nome` varchar(45)  NULL,
`contribuinte` varchar(45)  NULL,
`morada` varchar(45) NULL,
`cod_postal` int NULL,
`nascimento` varchar(45) NULL,
`sexo` varchar(45)  NULL,
`telefone` int NULL,
`email` varchar(45) NULL,
PRIMARY KEY (`id_paciente`));

-- -----------------------------------------------------
-- Table XLuUja9EDK.`medico` -- ok
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS XLuUja9EDK.`medico` (
`id_medico` int NOT NULL AUTO_INCREMENT,
`nome_medico` varchar(45) NULL,
`sexo` varchar(20) NULL,
`telefone` int  NULL,
`email` varchar(45)  NULL,
`morada` varchar(45)  NULL,
`cod_postal` int  NULL,
PRIMARY KEY (`id_medico`));	


-- -----------------------------------------------------
-- Table XLuUja9EDK.`seguradora`-- ok
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS XLuUja9EDK.`seguradora` (
`id_seguradora` int NOT NULL AUTO_INCREMENT,
`nome_seguradora` varchar(45)  NULL,
PRIMARY KEY (`id_seguradora`));	


-- -----------------------------------------------------
-- Table XLuUja9EDK.`paciente_has_seguradora` -- ok
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS XLuUja9EDK.`paciente_has_seguradora` (
`Paciente_id_paciente` int NOT NULL,
`Seguradora_id_seguradora` int NOT NULL,
KEY `fk_paciente_id_paciente` (`Paciente_id_paciente`),
KEY `fk_seguradora_id_seguradora` (`Seguradora_id_seguradora`),
CONSTRAINT `fk_paciente_id_paciente` FOREIGN KEY (`Paciente_id_paciente`) REFERENCES `paciente` (`id_paciente`),
CONSTRAINT `fk_seguradora_id_seguradora` FOREIGN KEY (`Seguradora_id_seguradora`) REFERENCES `seguradora` (`id_seguradora`));

-- -----------------------------------------------------
-- Table XLuUja9EDK.`especialidade` -- ok
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS XLuUja9EDK.`especialidade` (
`id_especialidade` int NOT NULL AUTO_INCREMENT,
`especialidade` varchar(45)  NULL,
PRIMARY KEY (`id_especialidade`));	

-- -----------------------------------------------------
-- Table XLuUja9EDK.`medico_has_especialidade` --ok
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS XLuUja9EDK.`medico_has_especialidade` (
`Medico_id_medico` int NOT NULL,
`Especialidade_id_especialidade` int NOT NULL,
PRIMARY KEY (`Medico_id_medico`,`Especialidade_id_especialidade`),
KEY `fk_Medico_has_Especialidade_Especialidade1` (`Especialidade_id_especialidade`),
CONSTRAINT `fk_Medico_has_Especialidade_Especialidade1` FOREIGN KEY (`Especialidade_id_especialidade`) REFERENCES `especialidade` (`id_especialidade`),
CONSTRAINT `fk_Medico_has_Especialidade_Medico1` FOREIGN KEY (`Medico_id_medico`) REFERENCES `medico` (`id_medico`));

-- -----------------------------------------------------
-- Table XLuUja9EDK.`agendamento` -- ok
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS XLuUja9EDK.`agendamento` (
`id_agendamento` int NOT NULL AUTO_INCREMENT,
`agendamento_data` varchar(45)  NULL,
`agendamento_hora` varchar(45)  NULL,
`Paciente_id_paciente` int NOT NULL,
`Medico_id_medico` int NOT NULL,
`confirmado` tinyint(1) DEFAULT 0,
`Especialidade_id_especialidade` int DEFAULT NULL,
PRIMARY KEY (`id_agendamento`,`Paciente_id_paciente`,`Medico_id_medico`),
KEY `fk_Agendamento_Paciente1` (`Paciente_id_paciente`),
KEY `fk_Agendamento_Medico1` (`Medico_id_medico`),
KEY `fk_id_especialidade_fk` (`Especialidade_id_especialidade`),
CONSTRAINT `fk_Agendamento_Medico1` FOREIGN KEY (`Medico_id_medico`) REFERENCES `medico` (`id_medico`),
CONSTRAINT `fk_Agendamento_Paciente1` FOREIGN KEY (`Paciente_id_paciente`) REFERENCES `paciente` (`id_paciente`),
CONSTRAINT `fk_id_especialidade_fk` FOREIGN KEY (`Especialidade_id_especialidade`) REFERENCES `especialidade` (`id_especialidade`));	

