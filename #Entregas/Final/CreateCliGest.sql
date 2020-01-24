
-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`paciente` (
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
-- Table `XLuUja9EDK`.`medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`medico` (
  `id_medico` INT NOT NULL auto_increment,
  `nome_medico` VARCHAR(45) NULL,
  `crm` VARCHAR(45) NULL,
  `telefone` INT NULL,
  `email` VARCHAR(45) NULL,
  `morada` VARCHAR(45) NULL,
  `cod_postal` INT NULL,
  PRIMARY KEY (`id_medico`));



-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`agendamento` (
  `id_agendamento` INT NOT NULL auto_increment,
  `agendamento_data` VARCHAR(45) NULL,
  `agendamento_hora` VARCHAR(45) NULL,
  `Paciente_id_paciente` INT NOT NULL,
  `Medico_id_medico` INT NOT NULL,
  PRIMARY KEY (`id_agendamento`, `Paciente_id_paciente`, `Medico_id_medico`),

  CONSTRAINT `fk_Agendamento_Paciente1`
    FOREIGN KEY (`Paciente_id_paciente`)
    REFERENCES `XLuUja9EDK`.`paciente` (`id_paciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agendamento_Medico1`
    FOREIGN KEY (`Medico_id_medico`)
    REFERENCES `XLuUja9EDK`.`medico` (`id_medico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`seguradora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`seguradora` (
  `id_seguradora` INT NOT NULL auto_increment,
  `nome_seguradora` VARCHAR(45) NULL,
  PRIMARY KEY (`id_seguradora`));



-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`paciente_has_seguradora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`paciente_has_seguradora` (
  `Paciente_id_paciente` INT NOT NULL,
  `Seguradora_id_seguradora` INT NOT NULL,
  PRIMARY KEY (`Paciente_id_paciente`, `Seguradora_id_seguradora`),
  
  CONSTRAINT `fk_Paciente_has_Seguradora_Paciente`
    FOREIGN KEY (`Paciente_id_paciente`)
    REFERENCES `XLuUja9EDK`.`paciente` (`id_paciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_has_Seguradora_Seguradora1`
    FOREIGN KEY (`Seguradora_id_seguradora`)
    REFERENCES `XLuUja9EDK`.`seguradora` (`id_seguradora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`especialidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`especialidade` (
  `id_especialidade` INT NOT NULL auto_increment,
  `especialidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id_especialidade`));


-- -----------------------------------------------------
-- Table `XLuUja9EDK`.`medico_has_especialidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XLuUja9EDK`.`medico_has_especialidade` (
  `Medico_id_medico` INT NOT NULL,
  `Especialidade_id_especialidade` INT NOT NULL,
  PRIMARY KEY (`Medico_id_medico`, `Especialidade_id_especialidade`),

  CONSTRAINT `fk_Medico_has_Especialidade_Medico1`
    FOREIGN KEY (`Medico_id_medico`)
    REFERENCES `XLuUja9EDK`.`medico` (`id_medico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Medico_has_Especialidade_Especialidade1`
    FOREIGN KEY (`Especialidade_id_especialidade`)
    REFERENCES `XLuUja9EDK`.`especialidade` (`id_especialidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
