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
- **Listar Especialdades**: endpoint para listar todas as especialidades cadastradas.  
- **Detalhes da Especialidade**: endpoint para mostrar os detalhes de uma determinada especialidade cadastrada.  
- **Cadastro de Médicos**: endpoint para cadastrar um novo médico.  
- **Listar Médicos**: endpoint para listar todos os médicos cadastrados.  
- **Detalhes do Médico**: endpoint para mostrar os detalhes de um determinado médico cadastrado.  
- **Cadastro de Pacientes**: endpoint para cadastrar um novo paciente.  
- **Listar Pacientes**: endpoint para listar todos os pacientes cadastrados.  
- **Detalhes do Paciente**: endpoint para mostrar os detalhes de um determinado paciente cadastrado.  
- **Fazer Agendamentos**: endpoint para realizar novos agendamentos.  
- **Listar Agendamentos**: endpoint para listar todos os agendamentos realizados.  

#### TCC-EngenhariaComputacao-Totem

- **Senha Para Atendimento**: endpoint que gera uma senha para o paciente que não possui cadastro prévio.  
- **Buscar Usuário por CPF**: endpoint responsável por buscar o usuário por CPF, realizar o reconhecimento facial do paciente previamente cadastrado e confirmar a presença do paciente.  
- **Resultado da Busca por CPF**: endpoint que gera mensagens do resultado do endpoint "Buscar Usuário por CPF" junto com sua imagem se reconhecido.

## Requisições do Usuário:

#### Agendamento:

- Agendar Consulta, (**POST**) localhost:8080/agendamentos/consulta/agendar
- Listar Consultas, (**GET**) localhost:8080/agendamentos/consulta
- Agendar Exame, (**POST**) localhost:8080/agendamentos/exame/agendar
- Listar Exames, **GET**) localhost:8080/agendamentos/exame

#### Totem:

- Gerar Senha Para Atendimento, (**GET**) localhost:4000/senha
- Confirmar Presença Consultório, (**POST**) localhost:4000/buscar-usuario-cpf

## Requisições do Administrador

#### Especialidade:

- Cadastrar Especialidade, (**POST**) localhost:8080/especialidade/cadastrar
- Listar Especialidades, (**GET**) localhost:8080/especialidade

#### Exame:

- Cadastrar Exame, (**POST**) localhost:8080/exame/cadastrar
- Listar Exames, (**GET**) localhost:8080/exame

#### Exame:

- Cadastrar Medico, (**POST**) localhost:8080/medico/cadastrar
- Listar Medicos, (**GET**) localhost:8080/medico

#### Paciente:

- Cadastrar Medico, (**POST**) localhost:8080/paciente/cadastrar
- Listar Medicos, (**GET**) localhost:8080/paciente

## Requisições do Médico

- Listar Consultas do Dia, (**GET**)localhost:8080/agendamentos/consulta

