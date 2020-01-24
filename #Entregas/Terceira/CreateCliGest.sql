
CREATE SCHEMA IF NOT EXISTS `cliGestDB` DEFAULT CHARACTER SET utf8 ;
USE `cliGestDB` ;

-- -----------------------------------------------------
-- Table `cliGestDB`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`paciente` (
  `id_paciente` INT NOT NULL auto_increment,
  `nome` VARCHAR(45) NULL,
  `contribuinte` VARCHAR(45) NULL,
  `morada` VARCHAR(45) NULL,
  `cod_postal` INT NULL,
  `nascimento` VARCHAR(45) NULL,
  `sexo` VARCHAR(45) NULL,
  `telefone` INT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_paciente`));



-- -----------------------------------------------------
-- Table `cliGestDB`.`medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`medico` (
  `id_medico` INT NOT NULL auto_increment,
  `nome_medico` VARCHAR(45) NULL,
  `crm` VARCHAR(45) NULL,
  `telefone` INT NULL,
  `email` VARCHAR(45) NULL,
  `morada` VARCHAR(45) NULL,
  `cod_postal` INT NULL,
  PRIMARY KEY (`id_medico`));



-- -----------------------------------------------------
-- Table `cliGestDB`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`agendamento` (
  `id_agendamento` INT NOT NULL auto_increment,
  `agendamento_data` VARCHAR(45) NULL,
  `agendamento_hora` VARCHAR(45) NULL,
  `Paciente_id_paciente` INT NOT NULL,
  `Medico_id_medico` INT NOT NULL,
  PRIMARY KEY (`id_agendamento`, `Paciente_id_paciente`, `Medico_id_medico`),
  INDEX `fk_Agendamento_Paciente1_idx` (`Paciente_id_paciente` ASC) VISIBLE,
  INDEX `fk_Agendamento_Medico1_idx` (`Medico_id_medico` ASC) VISIBLE,
  CONSTRAINT `fk_Agendamento_Paciente1`
    FOREIGN KEY (`Paciente_id_paciente`)
    REFERENCES `cliGestDB`.`paciente` (`id_paciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agendamento_Medico1`
    FOREIGN KEY (`Medico_id_medico`)
    REFERENCES `cliGestDB`.`medico` (`id_medico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `cliGestDB`.`seguradora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`seguradora` (
  `id_seguradora` INT NOT NULL auto_increment,
  `nome_seguradora` VARCHAR(45) NULL,
  PRIMARY KEY (`id_seguradora`));



-- -----------------------------------------------------
-- Table `cliGestDB`.`paciente_has_seguradora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`paciente_has_seguradora` (
  `Paciente_id_paciente` INT NOT NULL,
  `Seguradora_id_seguradora` INT NOT NULL,
  PRIMARY KEY (`Paciente_id_paciente`, `Seguradora_id_seguradora`),
  INDEX `fk_Paciente_has_Seguradora_Seguradora1_idx` (`Seguradora_id_seguradora` ASC) VISIBLE,
  INDEX `fk_Paciente_has_Seguradora_Paciente_idx` (`Paciente_id_paciente` ASC) VISIBLE,
  CONSTRAINT `fk_Paciente_has_Seguradora_Paciente`
    FOREIGN KEY (`Paciente_id_paciente`)
    REFERENCES `cliGestDB`.`paciente` (`id_paciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_has_Seguradora_Seguradora1`
    FOREIGN KEY (`Seguradora_id_seguradora`)
    REFERENCES `cliGestDB`.`seguradora` (`id_seguradora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `cliGestDB`.`especialidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`especialidade` (
  `id_especialidade` INT NOT NULL auto_increment,
  `especialidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id_especialidade`));


-- -----------------------------------------------------
-- Table `cliGestDB`.`medico_has_especialidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliGestDB`.`medico_has_especialidade` (
  `Medico_id_medico` INT NOT NULL,
  `Especialidade_id_especialidade` INT NOT NULL,
  PRIMARY KEY (`Medico_id_medico`, `Especialidade_id_especialidade`),
  INDEX `fk_Medico_has_Especialidade_Especialidade1_idx` (`Especialidade_id_especialidade` ASC) VISIBLE,
  INDEX `fk_Medico_has_Especialidade_Medico1_idx` (`Medico_id_medico` ASC) VISIBLE,
  CONSTRAINT `fk_Medico_has_Especialidade_Medico1`
    FOREIGN KEY (`Medico_id_medico`)
    REFERENCES `cliGestDB`.`medico` (`id_medico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Medico_has_Especialidade_Especialidade1`
    FOREIGN KEY (`Especialidade_id_especialidade`)
    REFERENCES `cliGestDB`.`especialidade` (`id_especialidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
