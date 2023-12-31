# TCC-EngenhariaComputacao

# :page_with_curl: Nomes

- **Alexandre Freire Ropero Junior RA:125111375230**
- **Guilherme dos Reis Freitas RA: 125111356415**
- **Pedro Trindade Francisco RA: 125111346772**

# :clipboard: Descrição do projeto

O projeto tem como objetivo desenvolver um sistema voltado para área da saúde através de duas aplicações webs, sendo uma aplicação Java e uma aplicação Python.  
A TCC-EngenhariaComputacao-Principal é uma aplicação Java responsável por realizar os processos de cadastros e agendamentos de consultas por um Médico ou Funcionário.  
A TCC-EngenhariaComputacao-Totem é uma aplicação Python responsável pelo auto-atendimento do Paciente no consultório/laboratório através do reconhecimento facial.  

# :hammer: Funcionalidades do projeto

### O projeto tem as seguintes funcionalidades:

#### TCC-EngenhariaComputacao-Principal

- **Primeiro Acesso**: endpoint para trocar sua senha gerada automaticamente por uma de sua escolha.  
- **Esquecimento Senha**: endpoint que envia um token para o e-mail solicitado.  
- **Trocar Senha**: endpoint que fazer uma troca de senha caso o token solicitado esteja correto.  
- **Cadastro de Especialidades**: endpoint para cadastrar uma nova especialidade.  
- **Listar Especialidades**: endpoint para listar todas as especialidades cadastradas.  
- **Editar Especialidade**: endpoint para editar qualquer uma das especialidades cadastradas.  
- **Excluir Especilidade**: endpoint para excluir qualquer uma das especialidades cadastradas.  
- **Cadastro de Exames**: endpoint para cadastrar um novo exame.  
- **Listar Exames**: endpoint para listar todos os exames cadastrados.  
- **Editar Exame**: endpoint para editar qualquer um dos exames cadastrados.  
- **Excluir Exame**: endpoint para excluir qualquer um dos exames cadastrados.  
- **Cadastro de Médicos**: endpoint para cadastrar um novo médico.  
- **Listar Médicos**: endpoint para listar todos os médicos cadastrados.  
- **Editar Médico**: endpoint para editar qualquer um dos médicos cadastrados.  
- **Excluir Médico**: endpoint para excluir qualquer um dos médicos cadastrados.  
- **Cadastro de Pacientes**: endpoint para cadastrar um novo paciente.  
- **Listar Pacientes**: endpoint para listar todos os pacientes cadastrados.  
- **Editar Paciente**: endpoint para editar qualquer um dos pacientes cadastrados.  
- **Excluir Paciente**: endpoint para excluir qualquer um dos pacientes cadastrados.  
- **Fazer Agendamentos**: endpoint para realizar novos agendamentos.  
- **Excluir Agendamentos**: endepoint para excluir agendamentos.  
- **Confirmar Agendamentos**: endpoint para confirmar agendamentos realizados.  
- **Listar Agendamentos**: endpoint para listar todos os agendamentos realizados.  

#### TCC-EngenhariaComputacao-Totem

- **Senha Para Atendimento**: endpoint que gera uma senha para o paciente que não possui cadastro prévio.  
- **Buscar Usuário por CPF**: endpoint responsável por buscar o usuário por CPF, realizar o reconhecimento facial do paciente previamente cadastrado e confirmar a presença do paciente.  
- **Resultado da Busca por CPF**: endpoint que gera mensagens do resultado do endpoint "Buscar Usuário por CPF" junto com sua imagem se reconhecido.

# :incoming_envelope: Endpoints

## Requisições do Usuário:

#### Agendamento:

- Escolher Tipo de Agendamento, (**POST**) localhost:8080/agendamentos
- Escolher Tipo de Consulta, (**POST**) localhost:8080/agendamentos/consulta/agendar
- Agendar Consulta, (**POST**) localhost:8080/agendamentos/consulta/agendar{idEspecialidade}
- Listar Consultas, (**GET**) localhost:8080/agendamentos/consulta
- Excluir Consultas, (**GET**) localhost:8080/agendamentos/consulta/excluir{idConsulta}
- Agendar Exame, (**POST**) localhost:8080/agendamentos/exame/agendar
- Escolher Tipo de Exame, (**POST**) localhost:8080/agendamentos/exame/agendar
- Agendar Exame, (**POST**) localhost:8080/agendamentos/exame/agendar{idExame}
- Listar Exames, (**GET**) localhost:8080/agendamentos/exame
- Excluir Exames, (**GET**) localhost:8080/agendamentos/exame/excluir{idExame}

#### Totem:

- Gerar Senha Para Atendimento, (**GET**) localhost:4000/senha
- Confirmar Presença Consultório, (**POST**) localhost:4000/buscar-usuario-cpf

## Requisições do Administrador:

#### Agendamento:

- Listar Consultas, (**GET**) localhost:8080/agendamentos/consulta
- Excluir Consultas, (**GET**) localhost:8080/agendamentos/consulta/excluir{idConsulta}
- Confirmar Agendamento Consulta, (**POST**) localhost:8080/agendamentos/consulta/confirmarAgendamento{idConsulta}
- Listar Exames, (**GET**) localhost:8080/agendamentos/exame
- Excluir Exames, (**GET**) localhost:8080/agendamentos/exame/excluir{idExame}
- Confirmar Agendamento Exame, (**POST**) localhost:8080/agendamentos/exame/confirmarAgendamento{idExame}

#### Especialidade:

- Cadastrar Especialidade, (**POST**) localhost:8080/especialidade/cadastrar
- Listar Especialidades, (**GET**) localhost:8080/especialidade
- Atualizar Especialidade, (**PUT**) localhost:8080/especialidade/cadastrar{idEspecialidade}
- Excluir Especialidade, (**DELETE**) localhost:8080/especialidade/excluir{idEspecialidade}

#### Exame:

- Cadastrar Exame, (**POST**) localhost:8080/exame/cadastrar
- Listar Exames, (**GET**) localhost:8080/exame
- Atualizar Exame, (**PUT**) localhost:8080/exame/cadastrar{idExame}
- Excluir Exame, (**DELETE**) localhost:8080/exame/excluir{idExame}

#### Medico:

- Cadastrar Medico, (**POST**) localhost:8080/medico/cadastrar
- Listar Medicos, (**GET**) localhost:8080/medico
- Atualizar Medico, (**PUT**) localhost:8080/medico/editar{idMedico}
- Excluir Medico, (**DELETE**) localhost:8080/medico/excluir{idMedico}

#### Paciente:

- Cadastrar Paciente, (**POST**) localhost:8080/paciente/cadastrar  
- Listar Pacientes, (**GET**) localhost:8080/paciente
- Atualizar Paciente, (**PUT**) localhost:8080/paciente/editar{idPaciente}
- Excluir Paciente, (**DELETE**) localhost:8080/paciente/excluir{idPaciente}

## Requisições do Médico:

- Listar Consultas do Dia, (**GET**)localhost:8080/agendamentos/consulta  
