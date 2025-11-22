# 🚀 NextStep - Java

## 📌 Sumário

- [📝 Descrição da Solução](#-descrição-da-solução)
- [🧩 Estrutura da Solução](#-estrutura-da-solução)
- [▶️ Como Rodar o Projeto](#️-como-rodar-o-projeto)
- [☕ Detalhes da API Java — Spring Boot](#-detalhes-da-api-java--spring-boot)
- [🚀 Como Rodar o Projeto API REST (Java)](#-como-rodar-o-projeto-api-rest-java)
- [☁️ Deploy da Solução](#️-deploy-da-solução)
- [🎥 Vídeo da API Java em Funcionamento](#-vídeo-da-api-java-em-funcionamento)
- [👥 Integrantes](#-integrantes)

## 📝 Descrição da Solução

O NextStep é uma plataforma inteligente desenvolvida para preparar pessoas para as profissões do futuro, oferecendo trilhas de aprendizado modernas, estruturadas e personalizadas.

Em um mundo onde a tecnologia evolui em ritmo acelerado e as demandas do mercado mudam constantemente, o NextStep surge como uma solução completa para quem deseja se atualizar, se qualificar e avançar na carreira com segurança.

A plataforma possui **duas frentes principais**:
- 🌐 **Painel Web Administrativo** — onde gestores criam e organizam trilhas de estudo;

- 📱 **Aplicativo Mobile** — onde os usuários consomem conteúdos, acompanham seu progresso e recebem recomendações personalizadas.

As trilhas são criadas em áreas essenciais como **Backend, Frontend, Cloud, Dados e Inteligência Artificial**, podendo incluir cursos, artigos, vídeos, podcasts, desafios práticos e outros recursos externos.
Para agilizar o processo, o admin conta com uma **IA integrada**, capaz de gerar automaticamente descrições completas de trilhas a partir apenas do título informado.

No app, o usuário tem uma jornada clara, simples e guiada. Com ajuda da **IA recomendadora**, o NextStep analisa o perfil, interesses e objetivos do usuário por meio de um pequeno questionário e indica automaticamente a trilha mais adequada, tornando o processo de aprendizado muito mais assertivo.

---

## 🧩 Estrutura da Solução

O **NextStep** foi desenvolvido com uma arquitetura moderna, modular e escalável, dividida em múltiplos serviços que se integram para entregar uma experiência fluida tanto para administradores quanto para usuários finais.

### ☕ Backend Administrativo — Java + Spring Boot

Responsável por toda a **lógica administrativa** da plataforma.

- CRUD de **trilhas** e **conteúdos** (cursos, artigos, desafios, etc.).
- Geração automática de descrições utilizando **IA integrada**.
- Exposição de APIs REST para o **painel web**.
- Integração direta com o **banco Oracle**.

[🔗 Repositório de Backend Java](https://github.com/felipesora/nextstep-backend-java)

### 🌐 Painel Web Administrativo — React.js

- Interface utilizada pelos **gestores** para criar e **gerenciar trilhas**.

- Desenvolvido em **React.js**.

- Consome exclusivamente a **API Java com Spring Boot**.

- Interface **moderna e responsiva**, focada em **produtividade**.

[🔗 Repositório do Frontend WEB](https://github.com/felipesora/nextstep-frontend-web)

### ⚙️ API do Usuário Final — .NET + ASP.NET Core

Camada que **atende o aplicativo mobile**.

- **Mapeia e expõe as tabelas de trilhas e conteúdos** criadas pelo backend Java.

- Responsável por **cadastro/login**, **progresso do usuário e consumo das trilhas**.

- Estruturada com **ASP.NET Core MVC + Entity Framework**.

- Focada em **alta performance e segurança**.

[🔗 Repositório de Backend .NET](https://github.com/felipesora/nextstep-backend-dotnet)

### 📱 Aplicativo Mobile — React Native + Expo

Aplicação voltada aos **usuários que irão consumir as trilhas**.

- Desenvolvido com **React Native + Expo**.

- Interface clara, intuitiva e otimizada para estudo.

- Consome a **API .NET**.

- Possui **IA recomendadora que sugere a trilha ideal com base no perfil do usuário**.

[🔗 Repositório do Mobile](https://github.com/felipesora/nextstep-frontend-mobile)

### 🗄️ Banco de Dados — Oracle

Armazena **todas as informações da plataforma**:

- Tabelas de **trilhas, conteúdos, usuários, progresso, notas e estatísticas**.

- Estrutura centralizada garantindo **consistência entre Java e .NET**.

[🔗 Repositório do Banco de Dados]()

### ☁️ Deploy & Infraestrutura — Azure

A API Java (admin) é publicada utilizando **práticas modernas de DevOps**:

- **Pipelines de CI/CD** no Azure DevOps.

- **Build automatizado**, execução de testes (quando houver) e **deploy contínuo**.

- Infraestrutura **escalável e segura**.

[🔗 Repositório de Cloud](https://github.com/felipesora/nextstep-cloud)

---

## 🗄️ Modelagem do Banco de Dados

Abaixo está a modelagem das tabelas utilizadas pelo sistema:

![Modelagem do banco](docs/modelagem-nextstep.png)

---

## ▶️ Como Rodar o Projeto

Para executar o NextStep localmente, siga a ordem correta dos serviços, garantindo que cada camada esteja funcionando antes de iniciar a próxima.

Abaixo está o fluxo recomendado:

### 1️⃣ Rodar a API Administrativa — Java + Spring Boot

1. Certifique-se de ter o **Java 21+** instalado.

2. Configure a conexão com o banco Oracle no application.properties.

3. Inicie o projeto Spring Boot.

4. Aguarde a criação/mapeamento inicial das tabelas necessárias.

> 💡 **Importante:** É essa API que fornece todos os dados administrativos e cria as trilhas e conteúdos utilizados por todo o ecossistema.

### 2️⃣ Rodar o Painel Web Administrativo — React.js

1. Instale dependências com `npm install`.

2. Configure as variáveis de API em cada service, com a url da api `Java`

3. Rode com `npm run dev`.

4. Acesse o painel e **cadastre algumas trilhas e conteúdos** — isso é essencial para que o app mobile e a API .NET tenham dados para consumir.

### 3️⃣ Rodar a API do Usuário Final — .NET + ASP.NET Core

1. Instale o .NET 8+.

2. Configure a connection string do Oracle.

3. Inicie o projeto (`dotnet run`).

4. Essa API irá consumir as tabelas geradas pelo backend Java e disponibilizar os dados para o app mobile.

### 4️⃣ Rodar o Aplicativo Mobile — React Native + Expo

1. Instale dependências com `npm install`.

2. Configure as variáveis de API em cada service, com a url da api de `.NET`.

3. Rode com `npx expo start`.

4. Abra no celular ou emulador para testar a jornada do usuário final.

### 📌 Observação Importante

Cada parte do NextStep possui **seu próprio repositório e um README separado**, com **todas as instruções detalhadas** de instalação, configuração e execução.

Esta seção é apenas um **guia geral**, mostrando a ordem correta de execução dos componentes.

## ☕ Detalhes da API Java — Spring Boot

A API Java do NextStep é responsável pela parte **administrativa** da plataforma, oferecendo operações de CRUD para trilhas, conteúdos, categorias e recursos adicionais. Ela também integra uma IA para gerar descrições e fornece os dados consumidos pelo painel web.

### 🔧 Tecnologias e Dependências Utilizadas

Abaixo estão as principais dependências que estruturam o backend em Java:

- Java 21+
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (Json Web Token)
- Spring AI Ollama
- Bean Validation (Jakarta Validation)
- Spring Web
- Oracle JDBC Driver
- Lombok
- Spring AMQP (integração com mensageria)

### 🔐 Usuário Administrador Padrão

Ao rodar o projeto pela primeira vez, a aplicação cria automaticamente um **usuário administrador padrão** no banco.

Ele é essencial para acessar o painel web administrativo antes de qualquer configuração adicional.

**Credenciais iniciais**:

- 📧 E-mail: `admin@email.com`

- 🔑 Senha: `admin123`

### 🤖 Requisito para IA — Ollama + deepseek-r1:8b

A API utiliza o **Spring AI Ollama** para gerar automaticamente as descrições das trilhas.

Para que isso funcione corretamente, é **necessário ter o Ollama instalado e rodando localmente**, com o modelo:

```bash
deepseek-r1:8b
```

Certifique-se de que:

- O Ollama esteja instalado,
- O serviço esteja ativo,
- O modelo deepseek-r1:8b esteja baixado e disponível.

Com isso, a integração de IA funcionará sem problemas.

### 📡 Mensageria — CloudAMQP ou RabbitMQ Local

A API utiliza mensageria via **Spring AMQP** para comunicação assíncrona.

No `application.properties`, o projeto está configurado para **usar automaticamente o CloudAMQP**, caso as variáveis de ambiente estejam definidas.

Entretanto, se nenhuma URL ou credencial for fornecida, a aplicação cai **automaticamente no RabbitMQ local**, usando os valores padrão:

```bash
# RabbitMQ
spring.rabbitmq.host=${RABBITMQ_HOST:localhost}
spring.rabbitmq.port=${RABBITMQ_PORT:5672}
spring.rabbitmq.username=${RABBITMQ_USERNAME:guest}
spring.rabbitmq.password=${RABBITMQ_PASSWORD:guest}
spring.rabbitmq.virtual-host=${RABBITMQ_VHOST:/}
```

Isso permite:

- ✔️ Rodar o projeto facilmente em ambiente local (com RabbitMQ padrão)
- ✔️ Migrar para produção usando CloudAMQP apenas configurando variáveis de ambiente
- ✔️ Manter portabilidade entre desenvolvimento, homologação e produção

### 🔑 Autenticação com JWT
Para acessar as rotas protegidas da API, é necessário realizar autenticação:  
1. Enviar uma requisição **POST** para: [http://localhost:8080/auth/login](http://localhost:8080/auth/login)
Com o corpo:  
```json
{
  "email": "admin@email.com",
  "senha": "admin123"
}
```

2. A resposta retornará um **token JWT**.

3. Esse token deve ser utilizado em todas as próximas requisições no header:
```bash
Authorization: Bearer {seu_token_aqui}
```

> ⚠️ **Observação**: a rota **POST - /usuarios/admin** é **pública** e pode ser usada para cadastrar novos usuários **sem necessidade de token**. Todas as demais rotas de CRUD e acesso ao sistema exigem autenticação via JWT.

### 🌐 Exemplos de Endpoints

#### 🔐 Usuário Administrador

- `POST - /usuarios/admin`  
  Cadastra um novo usuário administrador.

```jsonc
{
  "nome": "Felipe",
  "email": "felipe@email.com",
  "senha": "felipe123",
}
```

- `GET - /usuarios/admin`  
  Lista todos os usuários administradores cadastrados.

- `GET BY ID - /usuarios/admin/{id}`  
  Lista o usuário administrador cadastrado com este id.

- `PUT - /usuarios/admin/{id}`  
  Atualiza os dados do usuário administrador com este id.

```jsonc
{
  "nome": "Felipe Sora", // alterando nome
  "email": "felipe@email.com",
  "senha": "felipe123",
}
```

- `DELETE - /usuarios/admin/{id}`  
  Remove o usuário administrador com este id.

#### 📄 Solicitação de Conta

- `POST - /solicitacoes`  
  Cadastra uma nova solicitação de conta.

```jsonc
{
  "nome": "Felipe",
  "email": "felipe@email.com",
  "senha": "felipe123",
}
```

- `GET - /solicitacoes`  
  Lista todas as solicitações de conta cadastradas.

- `GET BY ID - /solicitacoes/{id}`  
  Lista a solicitação de conta cadastrada com este id.

- `DELETE - /solicitacoes/{id}`  
  Remove a solicitacão de conta com este id.

#### 📚 Trilhas de estudo

- `POST - /trilhas`  
  Cadastra uma nova trilha de estudo.

```jsonc
{
  "nome": "Frontend com React",
  "descricao": "Domine o desenvolvimento de interfaces modernas e performáticas com React, Hooks, Context API e integração com APIs REST.",
  "area": "WEB", // ou BACKEND, DATA_SCIENCE, MOBILE, DESIGN, DEVOPS, IA
  "nivel": "AVANCADO", // ou INICIANTE, INTERMEDIARIO
  "status": "ATIVA" //ou INATIVA
}
```

- `GET - /trilhas`  
  Lista todos as trilhas de estudo cadastrados.

- `GET ATIVAS - /trilhas/ativas`  
  Lista todos as trilhas de estudo cadastrados.

- `GET BY ID - /trilhas/{id}`  
  Lista a trilha de estudo cadastrada com este id.

- `PUT - /trilhas/{id}`  
  Atualiza os dados da trilha de estudo com este id.

```jsonc
{
  "nome": "Frontend com React",
  "descricao": "Domine o desenvolvimento de interfaces modernas e performáticas com React, Hooks, Context API e integração com APIs REST.",
  "area": "WEB", // ou BACKEND, DATA_SCIENCE, MOBILE, DESIGN, DEVOPS, IA
  "nivel": "AVANCADO", // ou INICIANTE, INTERMEDIARIO
  "status": "ATIVA" //ou INATIVA
}
```

- `DELETE - /trilhas/{id}`  
  Remove a trilha de estudo com este id.

#### 🗃️ Conteúdo da trilha

- `POST - /conteudos`  
  Cadastra um novo conteúdo para a trilha.

```jsonc
{
  "titulo": "Introdução ao Spring Boot",
  "descricao": "Aprenda os conceitos iniciais do Spring Boot, incluindo configuração, estrutura do projeto e dependências básicas.",
  "tipo": "VIDEO", // ou CURSO, ARTIGO, DESAFIO, PODCAST
  "link": "https://www.youtube.com/watch?v=9SGDpanrc8U",
  "id_trilha": 1
}
```

- `GET - /conteudos`  
  Lista todos os conteúdos cadastrados.

- `GET BY ID - /conteudos/{id}`  
  Lista o conteúdo cadastrado com este id.

- `PUT - /conteudos/{id}`  
  Atualiza os dados do conteúdo com este id.

```jsonc
{
  "nome": "Frontend com React",
  "descricao": "Domine o desenvolvimento de interfaces modernas e performáticas com React, Hooks, Context API e integração com APIs REST.",
  "area": "WEB", // ou BACKEND, DATA_SCIENCE, MOBILE, DESIGN, DEVOPS, IA
  "nivel": "AVANCADO", // ou INICIANTE, INTERMEDIARIO
  "status": "ATIVA" //ou INATIVA
}
```

- `DELETE - /conteudos/{id}`  
  Remove conteúdo com este id.

---

## 🚀 Como Rodar o Projeto API REST (Java)

Para executar o **NextStep Java**, siga os passos abaixo:

### 1️⃣ Configurar o Banco de Dados
- Abra o arquivo de configuração do banco (por exemplo, `application.properties`) e configure as **credenciais de acesso ao Oracle** (usuário, senha e URL).

### 2️⃣ Verificar Dependências
- Certifique-se de que o **Maven carregou todas as dependências** corretamente.  
- No IntelliJ IDEA, o Maven fará o download automático ao abrir o projeto, mas é recomendado verificar na aba **Maven** se todas as dependências foram resolvidas.

### 3️⃣ Executar o Projeto
- Abra o projeto no **IntelliJ IDEA**.  
- Clique no **ícone de play** na classe principal (`@SpringBootApplication`) para iniciar o servidor.  
- O projeto será iniciado no **localhost:8080**.

### 4️⃣ Acessar a Aplicação
- Abra o navegador e acesse: [http://localhost:8080](http://localhost:8080)

> ⚠️ Dica: Primeiro configure o banco e verifique as dependências do Maven para evitar erros de inicialização.

---

## ☁️ Deploy da Solução

A solução NextStep possui deploy para o **backend administrativo (Java + Spring Boot) e para o painel web administrativo (React.js)**. Ambos estão publicados e integrados entre si.

### 🔸 **API Administrativa — Java (Spring Boot)**

A API Java está hospedada no Render, e pode ser acessada pela URL:

**👉 API Java (Deploy)**:
[https://nextstep-backend-java.onrender.com](https://nextstep-backend-java.onrender.com)

>⚠️ **Importante**: Como a API está hospedada no Render, ela pode levar alguns segundos para acordar ao ser acessada pela primeira vez após ficar inativa. Por isso, ao abrir o painel web, aguarde a API inicializar caso as chamadas retornem erro no primeiro momento.

### 🔸 Painel Web Administrativo — React.js

O painel web está devidamente publicado e configurado para consumir a API Java no deploy.

**👉 URL do Painel Web**:
[https://nextstep-frontend-web.vercel.app/](https://nextstep-frontend-web.vercel.app/)

Este front-end já está apontando para a URL pública da API Java, permitindo:

- Login com o usuário administrador padrão
- Criação de trilhas, conteúdos, recursos e categorias

>Lembre-se: se ao abrir o painel ocorrer erro de carregamento, provavelmente a API ainda está inicializando no Render. Basta aguardar alguns segundos e recarregar a página.

---

## 🎥 Vídeo da API Java em Funcionamento

Para demonstrar o funcionamento da API administrativa desenvolvida em **Java + Spring Boot**, disponibilizei um vídeo completo mostrando:

- O processo de autenticação
- A criação de trilhas e conteúdos
- O funcionamento dos endpoints
- A integração com IA via Spring AI Ollama
- A estrutura geral da aplicação no painel administrativo

👉 **Assista ao vídeo aqui**:
[Clique para ver o vídeo da API Java](https://www.youtube.com/watch?v=eaWrRpiMwb0)

---

## 👥 Integrantes

- **Felipe Ulson Sora** – RM555462 – [@felipesora](https://github.com/felipesora)
- **Augusto Lopes Lyra** – RM558209 – [@lopeslyra10](https://github.com/lopeslyra10)
- **Vinicius Ribeiro Nery Costa** – RM559165 – [@ViniciusRibeiroNery](https://github.com/ViniciusRibeiroNery)