# cliGest

###### Nancia Laudino - 50036506
###### Douglas Bewiahn - 50038630




**Enquadramento**

Este documento representa o Briefing do projeto no âmbito da unidade curricular Programação Orientada por Objectos em conjunto com a unidade curricular Bases de Dados, do curso de Engenharia Informática da Universidade Europeia. Um documento que descreve as principais etapas/funcionalidades de uma aplicação.
A gestão de clientes tem sido um dos grandes problemas que muitas empresas enfrentam, não só pelo fluxo de informação que é gerado mas também devido aos recursos utilizados para se fazer essa gestão. Para solucionar esta problemática, criamos o cliGest, uma aplicação que está virada para empresas no ramo da saúde, mais propriamente clínicas, de forma a permitir aos gestores um melhor controle de toda a informação relevante, com a finalidade de gerir as agendas de cada médico/cliente para a marcação e realização de consultas. Consideramos que a organização dentro de uma empresa é um factor muito importante e que o atendimento rápido e eficiente é um ponto crucial para fidelizar clientes.



## **Cenário Principal**


Um dos objetivos principais da nossa aplicação é a implementação de um conjunto de medidas que visam garantir a integridade da informação armazenada bem como uma gestão segura no acesso à mesma. O Agendamento de consultas será o nosso cenário principal, ou seja, a principal funcionalidade da nossa aplicação. Criaremos uma sistema que permitirá aos admnistradores fazer uma melhor gestão de toda a informação existente dentro do cliGest. O admnistrador representa a pessoa responsável por toda a gestão dos dados, é o único que pode criar novos utilizadores e consultar toda a informacão do cliGest de forma geral. Admistradores, Pacientes e Médicos fazem parte de uma entidade que se chama Utilizador e necessitarão de um login e uma password para aceder à informações da aplicação, mas no cenário principal o nosso foco principal é o Admnistrador como gestor da aplicação no que diz respeito a agendamento de consultas e acesso à informações. 
Um admnistrador:
* Faz Login
* Insere novo utilizador
* Edita informações de utilizadores
* Elimina utilizadores
* Faz agendamentos
* Cancela agendamentos
* Visualiza relatórios médicos
* Visualiza exames médicos


## **Cenário Secundário**

* Os Pacientes poderão aceder à aplicação, podendo consultar alguns dados mas sem a possibilidade de os alterar. Estas permissões incluem a possibilidade de ver relatório médico, ver exames pedidos e consultar agendamentos feitos. 

* Será possivel consultar a lista de agendamentos de cada médico, evitando sobreposição de agendamentos, ie, nenhum médico poderá ter mais de um agendamento no mesmo dia e na mesma hora (o mesmo aplica-se para os pacientes).

* Os pacientes poderão também receber notificações automáticas via e-mail logo após a conclusão do agendamento e um dia antes da consulta (uma espécie de lembrete).

## **Referências**
Aplicações, com alguma semelhança, existentes no mercado:
* [1] Marcação de Consultas e Exames. Acedido em: 20 de Outubro de 2019 em: (https://www.saudecuf.pt/mycuf/)
* [2] Numa perspetiva de aumentar a proximidade junto do utente e a transparência dos serviços prestados foi desenvolvido o novo Portal do SNS. Acedido em: 20 de Outubro de 2019 em: (https://www.sns.gov.pt/apps/mysns/)
* [3] Agenda, Lembretes SMS automáticos, Fichas de Cliente, Cartão de Cliente, Marcações online- Acedido em: 20 de outubro de 2019 em: (https://pt.zappysoftware.com/)
