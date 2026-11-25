# UNIVERSIDADE FEDERAL DE JUIZ DE FORA (UFJF)
## DEPARTAMENTO DE CIÊNCIA DA COMPUTAÇÃO (DCC)
### DISCIPLINA: DCC061 – ENGENHARIA DE SOFTWARE
**Docente:** Prof. Dr. Fabrício Martins Mendonça  
**Semestre Letivo:** 2026.2  

---

# RELATÓRIO TÉCNICO-CIENTÍFICO E APLICAÇÃO PRÁTICA

**Temas Selecionados:**
1. **Arquitetura de Software**
2. **Testes e Qualidade de Software**

**Estudo de Caso Prático:** Sistema de Gestão de Oficina Mecânica (*Spring Boot, Thymeleaf, JPA/Hibernate, Spring Security e JUnit 5/Mockito*)

---

## SUMÁRIO
1. [PARTE I: FUNDAMENTAÇÃO TEÓRICA DOS TEMAS](#1-parte-i-fundamentação-teórica-dos-temas)
   - [Tema 1: Arquitetura de Software](#tema-1-arquitetura-de-software)
     - [a) Definição do Tema](#a-definição-do-tema)
     - [b) Histórico na Computação e Engenharia de Software](#b-histórico-na-computação-e-engenharia-de-software)
     - [c) Importância em Engenharia de Software](#c-importância-em-engenharia-de-software)
     - [d) Tecnologias, Ferramentas e Frameworks](#d-tecnologias-ferramentas-e-frameworks)
     - [e) Padrões e Normas Aplicáveis](#e-padrões-e-normas-aplicáveis)
     - [f) Profissões do Mercado de Trabalho em TI](#f-profissões-do-mercado-de-trabalho-em-ti)
     - [g) Certificações do Mercado](#g-certificações-do-mercado)
     - [h) Impactos da Inteligência Artificial](#h-impactos-da-inteligência-artificial)
   - [Tema 2: Testes e Qualidade de Software](#tema-2-testes-e-qualidade-de-software)
     - [a) Definição do Tema](#a-definição-do-tema-1)
     - [b) Histórico na Computação e Engenharia de Software](#b-histórico-na-computação-e-engenharia-de-software-1)
     - [c) Importância em Engenharia de Software](#c-importância-em-engenharia-de-software-1)
     - [d) Tecnologias, Ferramentas e Frameworks](#d-tecnologias-ferramentas-e-frameworks-1)
     - [e) Padrões e Normas Aplicáveis](#e-padrões-e-normas-aplicáveis-1)
     - [f) Profissões do Mercado de Trabalho em TI](#f-profissões-do-mercado-de-trabalho-em-ti-1)
     - [g) Certificações do Mercado](#g-certificações-do-mercado-1)
     - [h) Impactos da Inteligência Artificial](#h-impactos-da-inteligência-artificial-1)
2. [PARTE II: APLICAÇÃO PRÁTICA NO SISTEMA DE OFICINA MECÂNICA](#2-parte-ii-aplicação-prática-no-sistema-de-oficina-mecânica)
   - [2.1 Arquitetura do Sistema Desenvolvido](#21-arquitetura-do-sistema-desenvolvido)
   - [2.2 Padrões Arquiteturais e de Projeto Aplicados](#22-padrões-arquiteturais-e-de-projeto-aplicados)
   - [2.3 Estratégia de Testes e Garantia da Qualidade](#23-estratégia-de-testes-e-garantia-da-qualidade)
   - [2.4 Matriz de Rastreabilidade e Cobertura de Regras de Negócio](#24-matriz-de-rastreabilidade-e-cobertura-de-regras-de-negócio)
3. [PARTE III: CONCLUSÃO](#3-parte-iii-conclusão)
4. [REFERÊNCIAS BIBLIOGRÁFICAS](#4-referências-bibliográficas)

---

# 1. PARTE I: FUNDAMENTAÇÃO TEÓRICA DOS TEMAS

---

## TEMA 1: ARQUITETURA DE SOFTWARE

### a) Definição do Tema
A **Arquitetura de Software** de um sistema computacional compreende a estrutura ou estruturas do sistema, consistindo em elementos de software, suas propriedades externamente visíveis e os relacionamentos entre eles (BASS; CLEMENTS; KAZMAN, 2021). 

Em termos formais, a arquitetura representa o conjunto de decisões de design de mais alto nível e mais difíceis de alterar em um ciclo de desenvolvimento. Ela não se preocupa apenas com a corretude funcional (o que o sistema deve fazer), mas principalmente com os **Requisitos Não-Funcionais** ou **Atributos de Qualidade** (*Quality Attributes*), tais como:
* Desempenho e escalabilidade;
* Modificabilidade e manutenibilidade;
* Disponibilidade, resiliência e tolerância a falhas;
* Segurança da informação e isolamento de privilégios;
* Testabilidade e acoplamento.

### b) Histórico na Computação e Engenharia de Software
* **Décadas de 1960 e 1970:** Nos primórdios da Ciência da Computação (marcado pela histórica Conferência da OTAN de 1968 em Garmisch, que cunhou a expressão *Engenharia de Software*), a ênfase residia na programação estruturada (Edsger Dijkstra) e na decomposição modular (David Parnas, 1972). Parnas demonstrou o conceito fundamental de **Ocultamento de Informação** (*Information Hiding*), embrião das fronteiras arquiteturais modernas.
* **Década de 1990:** O reconhecimento formal da Arquitetura de Software como disciplina própria consolidou-se na década de 1990, impulsionado por pesquisadores da *Carnegie Mellon University* (CMU) e do *Software Engineering Institute* (SEI), notadamente Mary Shaw e David Garlan com a publicação clássica *"Software Architecture: Perspectives on an Emerging Discipline"* (1996). Surge a taxonomia de estilos arquiteturais (Pipelines, Camadas, Sistemas Baseados em Eventos, Blackboard).
* **Anos 2000 a 2010:** Transição do modelo cliente-servidor tradicional para a Arquitetura Orientada a Serviços (SOA) e a consolidação do padrão MVC (*Model-View-Controller*) na web corporativa (Java EE, Spring Framework, Ruby on Rails).
* **2014 aos dias atuais:** Explosão dos Microsserviços (*Microservices Architecture*), arquiteturas orientadas a eventos (*Event-Driven Architecture*), computação sem servidor (*Serverless*) e o design direcionado ao domínio (*Domain-Driven Design* - DDD, de Eric Evans).

### c) Importância em Engenharia de Software
A arquitetura é a espinha dorsal de qualquer produto de software corporativo. Sua relevância traduz-se em:
1. **Comunicação entre Partes Interessadas (*Stakeholders*):** Oferece uma abstração de alto nível que permite a desenvolvedores, gerentes, clientes e analistas de segurança alinharem expectativas técnicas e de negócio.
2. **Decisões Prévias de Design:** O custo relativo de corrigir um erro arquitetural na fase de produção é até 100 vezes maior do que na fase de concepção (curva de custo de mudança de Boehm).
3. **Transferibilidade e Reuso de Modelos:** Padrões arquiteturais consolidados (como a Arquitetura em Camadas) transferem lições aprendidas de projetos legados para novos domínios sem necessidade de reinventar a estrutura.
4. **Viabilização de Atributos de Qualidade:** É impossível "adicionar segurança" ou "adicionar escalabilidade" em um sistema mal arquitetado sem reescrever seus alicerces.

### d) Tecnologias, Ferramentas e Frameworks
* **Modelagem e Visualização:**
  * *Modelo C4* (Contexto, Contêineres, Componentes e Código) criado por Simon Brown;
  * Ferramentas *Architecture as Code*: Structurizr, PlantUML, Mermaid.js;
  * Linguagens de Descrição Arquitetural: ArchiMate, UML 2.5.
* **Frameworks de Suporte Estrutural:**
  * *Spring Boot / Spring Framework (Java):* Provedor de injeção de dependências (IoC), inversão de controle, isolamento em camadas e segurança transacional;
  * *NestJS (Node.js/TypeScript), ASP.NET Core (C#), Quarkus / Micronaut.*
* **Governança e Análise de Conformidade Arquitetural:**
  * *ArchUnit (Java):* Biblioteca para testes unitários automatizados que garantem que classes da camada *Controller* nunca acessem repositórios diretamente, por exemplo;
  * *SonarQube:* Detecção de acoplamento indevido e dívida técnica arquitetural.

### e) Padrões e Normas Aplicáveis
* **ISO/IEC/IEEE 42010 (Systems and software engineering — Architecture description):** Norma internacional que define requisitos para a descrição formal de arquiteturas de software, sistemas e empresas, estabelecendo conceitos de *Viewpoints* (Pontos de Vista), *Views* (Vistas Arquiteturais) e *Stakeholders concerns*.
* **Modelo 4+1 Vistas de Philippe Kruchten:**
  1. Vista Lógica (*Logical View*);
  2. Vista de Processo (*Process View*);
  3. Vista de Desenvolvimento (*Development View*);
  4. Vista Física/Implantação (*Physical/Deployment View*);
  5. Casos de Uso/Cenários (*Use Cases View*).
* **TOGAF (The Open Group Architecture Framework):** Framework corporativo padronizado para arquitetura empresarial e de sistemas.

### f) Profissões do Mercado de Trabalho em TI
* **Arquiteto de Software (*Software Architect*):** Responsável pelas escolhas estruturais, definição de padrões, limites de responsabilidade de módulos e governança tecnológica.
* **Arquiteto de Soluções (*Solutions Architect*):** Integra componentes de software a serviços de infraestrutura, cloud e produtos legados corporativos.
* **Engenheiro de Software Principal / Staff Engineer:** Atua na liderança técnica da implementação, garantindo que as diretrizes arquiteturais sejam respeitadas no dia a dia da equipe.
* **Arquiteto Corporativo (*Enterprise Architect*):** Alinha a evolução de múltiplos sistemas aos objetivos estratégicos de longo prazo do negócio.

### g) Certificações do Mercado
* **iSAQB (International Software Architecture Qualification Board):** *CPSA-F* (Certified Professional for Software Architecture - Foundation Level) e *CPSA-A* (Advanced Level).
* **TOGAF 9 / TOGAF Enterprise Architecture Practitioner.**
* **Certificações em Nuvem com foco Arquitetural:**
  * *AWS Certified Solutions Architect (Associate & Professional);*
  * *Microsoft Certified: Azure Solutions Architect Expert;*
  * *Google Cloud Professional Cloud Architect.*
* **SEI Software Architecture Professional Certificate (Carnegie Mellon SEI).**

### h) Impactos da Inteligência Artificial
A Inteligência Artificial vem transformando a arquitetura em três frentes:
1. **Geração e Refatoração Assistida de Código Estrutural:** IAs generativas sugerem a modularização adequada de código monolítico e adaptam interfaces baseadas em contratos (APIs).
2. **Monitoramento e Otimização Arquitetural em Tempo de Execução (*AIOps*):** Modelos preditivos analisam telemetria e logs para acionar auto-escalonamento, detectar gargalos de microsserviços e isolar falhas em malhas de serviços (*Service Meshes*).
3. **Arquiteturas Específicas para IA (*Software 2.0 / RAG Architectures*):** Arquitetos agora precisam desenhar sistemas com suporte nativo a bancos de dados vetoriais, filas para pipelines de modelos de linguagem (LLMs) e camadas estritas de governança/sanitização de contexto de dados.

---

## TEMA 2: TESTES E QUALIDADE DE SOFTWARE

### a) Definição do Tema
* **Qualidade de Software:** É a conformidade com requisitos funcionais e de desempenho explicitamente declarados, padrões de desenvolvimento documentados e características implícitas que são esperadas de todo software profissional (PRESSMAN; MAXIM, 2021). Envolve aspectos internos (manutenibilidade, legibilidade, coesão) e externos (confiabilidade, usabilidade, segurança, eficiência).
* **Testes de Software:** Segundo Glenford Myers (1979), testar software é o processo sistemático de executar um programa com a intenção deliberada de encontrar defeitos (*bugs*). O teste moderno engloba verificação (estamos construindo o produto corretamente?) e validação (estamos construindo o produto certo?).

A pirâmide de testes (Mike Cohn) segmenta esse esforço em:
* **Testes Unitários:** Validação isolada de unidades atômicas (métodos, classes), com execução em milissegundos e dependências simuladas (*mocks*).
* **Testes de Integração:** Verificação da comunicação entre dois ou mais componentes reais (ex.: serviço persistindo em banco de dados).
* **Testes de Sistema / Ponta a Ponta (E2E):** Validação dos fluxos completos da aplicação através da interface com o usuário ou API externa.

### b) Histórico na Computação e Engenharia de Software
* **Década de 1970:** Publicação de *"The Art of Software Testing"* por Glenford Myers (1979), rompendo com o paradigma ingênuo de que o desenvolvedor devia testar seu código apenas para provar que ele "funciona". Edsger Dijkstra proclama a máxima: *"Testes de programas podem demonstrar a presença de defeitos, mas nunca a sua ausência."*
* **Década de 1980:** Formalização da Garantia da Qualidade de Software (SQA) com modelos matemáticos de confiabilidade e métricas de complexidade (como a Complexidade Ciclomática de Thomas McCabe, 1976).
* **Final da década de 1990 e Anos 2000:** Revolução Ágil com o *Extreme Programming* (XP) proposto por Kent Beck, introduzindo o **TDD** (*Test-Driven Development*), testes automatizados de regressão e ferramentas da família xUnit (notadamente o JUnit criado por Kent Beck e Erich Gamma em 1997).
* **Década de 2010 em diante:** Práticas de Integração Contínua e Entrega Contínua (CI/CD), automação total de pipelines com portais de qualidade (*Quality Gates*), testes de mutação e Engenharia do Caos (*Chaos Engineering* pela Netflix).

### c) Importância em Engenharia de Software
A garantia sistemática de testes e qualidade confere:
1. **Redução Drástica do Custo de Manutenção:** Corrigir uma falha em ambiente de teste unitário tem custo negligenciável se comparado ao custo financeiro e reputacional de um vazamento de dados ou indisponibilidade de serviço em produção.
2. **Confiança para Refatoração:** Códigos cobertos por testes automatizados permitem que a equipe altere algoritmos, atualize dependências e remova dívidas técnicas sem o medo de causar efeitos colaterais (*regressões*).
3. **Documentação Viva e Executável:** Casos de teste unitário bem escritos documentam exatamente quais entradas são permitidas, quais exceções devem ser disparadas e quais contratos de negócio vigoram no sistema.
4. **Cumprimento de Requisitos de Compliance e Regulatórios:** Sistemas críticos nas indústrias automotiva, médica, financeira e aeroespacial exigem níveis auditáveis de cobertura e qualidade.

### d) Tecnologias, Ferramentas e Frameworks
* **Testes Unitários e Simulação (Java):**
  * *JUnit 5 (Jupiter):* Framework padrão da indústria com anotações `@Test`, `@DisplayName`, `@BeforeEach`, assertivas (`assertEquals`, `assertThrows`);
  * *Mockito:* Framework líder para criação de dublês de teste (*Mocks*, *Stubs*, *Spies*), permitindo isolar camadas sem necessitar de conexões de rede ou banco de dados.
* **Qualidade Estática e Cobertura de Código:**
  * *JaCoCo (Java Code Coverage):* Geração de relatórios de cobertura de instruções e ramos (*branches*);
  * *SonarQube / SonarCloud:* Análise estática contínua de segurança (SAST), code smells, duplicação e vulnerabilidades (OWASP Top 10);
  * *SpotBugs, Checkstyle e PMD.*
* **Testes Funcionais, E2E e Performance:**
  * *Selenium WebDriver, Cypress e Playwright;*
  * *Apache JMeter, k6 e Gatling.*

### e) Padrões e Normas Aplicáveis
* **ISO/IEC 25010 (Systems and software engineering — Systems and software Quality Requirements and Evaluation - SQuaRE):** Modelo de qualidade de produto de software que estabelece 8 características essenciais:
  1. Adequação Funcional (*Functional Suitability*);
  2. Eficiência de Desempenho (*Performance Efficiency*);
  3. Compatibilidade (*Compatibility*);
  4. Usabilidade (*Usability*);
  5. Confiabilidade (*Reliability*);
  6. Segurança (*Security*);
  7. Manutenibilidade (*Maintainability*);
  8. Portabilidade (*Portability*).
* **ISO/IEC/IEEE 29119 (Software Testing):** Padrão internacional abrangente que define vocabulário, processos de teste organizacionais, documentação e técnicas de concepção de testes (particionamento de equivalência, análise de valor limite, testes de transição de estados).
* **CMMI-DEV e MPS.BR:** Modelos de maturidade de processos com foco explícito em Garantia da Qualidade de Produto e Processo (PPQA).

### f) Profissões do Mercado de Trabalho em TI
* **Engenheiro de Qualidade de Software (*QA Engineer*):** Planeja estratégias de qualidade, desenha planos de teste e valida critérios de aceitação.
* **Engenheiro de Desenvolvimento em Teste (*SDET - Software Development Engineer in Test*):** Desenvolvedor focado em construir frameworks de automação, pipelines de testes de regressão e ferramentas internas de garantia.
* **Analista de Testes / Test Lead:** Lidera a execução de ciclos de homologação, testes exploratórios e relatórios de métricas de defeitos.
* **Especialista em Testes Não-Funcionais:** Foco em testes de carga/estresse ou auditorias de segurança ofensiva (*PenTest*).

### g) Certificações do Mercado
* **ISTQB (International Software Testing Qualifications Board):**
  * *CTFL* (Certified Tester Foundation Level) – Padrão mundial de entrada;
  * *CTAL* (Advanced Level - Test Manager, Test Analyst, Technical Test Analyst);
  * Especializações: Agile Tester, Security Tester, Performance Tester.
* **ALATS / CBTS (Certificação Brasileira de Teste de Software):** Mantida pela ALATS no Brasil.
* **ASQ (American Society for Quality):** *CSQE* (Certified Software Quality Engineer).

### h) Impactos da Inteligência Artificial
A Inteligência Artificial atua como um divisor de águas na qualidade de software:
1. **Geração Automatizada de Testes de Borda:** Modelos de linguagem analisam assinaturas de métodos e regras de negócio para gerar baterias completas de testes parametrizados cobrindo cenários anômalos (*fuzzing* e *boundary analysis*).
2. **Cura Automática de Testes de Interface (*Self-Healing Tests*):** Algoritmos de IA detectam quando o seletor HTML de um botão mudou e adaptam o teste funcional sem quebrar o pipeline de CI/CD.
3. **Priorização Inteligente de Testes de Regressão:** Modelos de Machine Learning prevêem quais módulos do repositório têm maior probabilidade de falha com base no histórico de commits e autores, executando primeiro os testes mais críticos.
4. **Testes do próprio Software de IA:** Surge o ramo de validação de modelos: testes de alucinação, toxicidade, explicabilidade (*XAI*) e robustez contra ataques adversariais.

---

# 2. PARTE II: APLICAÇÃO PRÁTICA NO SISTEMA DE OFICINA MECÂNICA

## 2.1 Arquitetura do Sistema Desenvolvido

O sistema desenvolvido pelo grupo é o **Gestão de Oficina Mecânica** (`gestaoDeOficinaMecanica`), concebido sob o ecossistema corporativo **Java 25**, **Spring Boot 4.1**, **Spring Data JPA**, **Hibernate**, **Spring Security** e motor de renderização **Thymeleaf**.

A solução adota a clássica e robusta **Arquitetura em Camadas (*Layered Architecture*)**, combinada com o padrão arquitetural **MVC (*Model-View-Controller*)**:

```
+-----------------------------------------------------------------------------------+
|                            CAMADA DE APRESENTAÇÃO (VIEW)                          |
|  Thymeleaf Templates: login.html, ordens/dashboard.html, mecanicos/lista.html,   |
|                      usuarios/formulario.html, fragments/navbar.html              |
+-----------------------------------------------------------------------------------+
                                        |  (HTTP Requests / HTML Response)
                                        v
+-----------------------------------------------------------------------------------+
|                         CAMADA DE CONTROLE (CONTROLLER & MVC)                     |
|  MecanicoController, OrdemServicoController, UsuarioController                    |
|  * GlobalExceptionHandler (@ControllerAdvice): interceptação e resiliência de UI  |
+-----------------------------------------------------------------------------------+
                                        |  (Injeção de Dependências / DTOs)
                                        v
+-----------------------------------------------------------------------------------+
|                           CAMADA DE NEGÓCIO (SERVICE LAYER)                       |
|  MecanicoService, OrdemServicoService, UsuarioService                             |
|  * Execução estrita das Regras de Negócio e disparo de Exceções Customizadas      |
+-----------------------------------------------------------------------------------+
                                        |  (Chamadas a Repositórios JPA)
                                        v
+-----------------------------------------------------------------------------------+
|                        CAMADA DE ACESSO A DADOS (REPOSITORY)                      |
|  MecanicoRepository, OrdemDeServicoRepository, PecaRepository, UsuarioRepository  |
|  * Herança de JpaRepository<Entity, UUID> com mapeamento relacional via Hibernate|
+-----------------------------------------------------------------------------------+
                                        |  (JDBC Driver / SQL)
                                        v
+-----------------------------------------------------------------------------------+
|                      CAMADA DE PERSISTÊNCIA / BANCO DE DADOS                      |
|  PostgreSQL / Tabelas: mecanico, ordemde_servico, peca, usuario                   |
+-----------------------------------------------------------------------------------+
```

### Segurança Transversal (Cross-Cutting Concerns)
A aplicação implementa uma **Cadeia de Filtros de Segurança (*Security Filter Chain*)** gerenciada pelo `SecurityConfig.java`. Todas as requisições autenticadas exigem credenciais validadas via `UsuarioService` (implementação de `UserDetailsService`), com senhas protegidas por algoritmos de dispersão criptográfica irreversível (**BCrypt**).

---

## 2.2 Padrões Arquiteturais e de Projeto Aplicados

| Padrão | Onde foi aplicado no projeto | Benefício Arquitetural |
|---|---|---|
| **Layered Architecture (Camadas)** | Divisão explícita em pacotes `controller`, `service`, `repository`, `database.model` | Separação de responsabilidades (*SoC*), impedindo acoplamento de regras de negócio em telas ou consultas SQL no Controller. |
| **Data Transfer Object (DTO)** | `MecanicoDto`, `OrdemDeServicoDto`, `Pecadto`, `ErroResposta` (implementados com Java Records) | Evita a exposição direta das entidades gerenciadas pela JPA, encapsula dados trafegados na rede e reduz o risco de *Over-posting*. |
| **Repository Pattern** | Interfaces herdando de `JpaRepository<T, UUID>` | Abstrai as operações de persistência e viabiliza a simulação completa do banco em testes unitários via mocks. |
| **Inversão de Controle e Injeção de Dependência (IoC / DI)** | Injeção via construtor em todos os Services e Controllers | Reduz acoplamento a zero em tempo de compilação e torna todas as classes testáveis de forma independente. |
| **Front Controller & Interceptor** | `DispatcherServlet` do Spring e `GlobalExceptionHandler` anotado com `@ControllerAdvice` | Centraliza o tratamento de erros sem poluir controladores com lógica redundante de captura de falhas. |
| **Post-Redirect-Get (PRG)** | Controladores redirecionam após requisições `POST` usando `RedirectAttributes` | Impede reenvio acidental de formulários por atualização da página (*F5*) e transporta mensagens de erro/sucesso temporárias (*Flash Attributes*). |

---

## 2.3 Estratégia de Testes e Garantia da Qualidade

Para garantir a qualidade interna e externa do sistema (em consonância com a **ISO/IEC 25010** nos critérios de *Adequação Funcional*, *Confiabilidade* e *Manutenibilidade*), desenvolveu-se uma suíte completa de **testes unitários isolados** com **JUnit 5** e **Mockito**.

### Características Técnicas da Bateria de Testes:
1. **Velocidade de Execução (Milissegundos):** Nenhum teste unitário sobe contexto pesado de banco de dados ou servidor web embutido (`@ExtendWith(MockitoExtension.class)` substitui o pesado `@SpringBootTest`).
2. **Isolamento Total:** Todas as dependências externas (`MecanicoRepository`, `OrdemDeServicoRepository`, `PasswordEncoder`, etc.) são injetadas como dublês controlados (`@Mock`).
3. **Cenários Positivos e Negativos:** Não foram testados apenas os caminhos felizes (*happy paths*), mas primordialmente as condições de borda e disparo de exceções de negócio.

---

## 2.4 Matriz de Rastreabilidade e Cobertura de Regras de Negócio

Abaixo, detalha-se como as regras centrais de negócio do domínio mecânico foram mapeadas da especificação para a arquitetura e validadas pela suíte de testes:

### Regra de Negócio 1: Incompatibilidade de Especialidade
* **Requisito:** Um mecânico só pode ser atribuído a uma Ordem de Serviço se a sua especialidade (`Especialidade`: `Motor`, `Eletrica`, `Supensâo`) for idêntica ao `tipoServico` da OS. Caso contrário, o sistema deve interromper a operação e lançar `EspecialidadeIncompativelException`.
* **Implementação na Arquitetura:**
  * Classe: [`OrdemServicoService.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/main/java/com/estudos/gestaoDeOficinaMecanica/service/OrdemServicoService.java)
  * Método privado: `validarEspecialidade(OrdemdeServico ordem)`
* **Testes de Qualidade Criados:**
  * Teste: `deveSalvarOrdemComEspecialidadeCompativel()` em [`OrdemServicoServiceTest.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/test/java/com/estudos/gestaoDeOficinaMecanica/service/OrdemServicoServiceTest.java) $\rightarrow$ Valida sucesso quando `mecanico.especialidade == tipoServico`.
  * Teste: `deveLancarExcecaoQuandoEspecialidadeIncompativel()` $\rightarrow$ Utiliza `assertThrows(EspecialidadeIncompativelException.class)` e valida se a mensagem exata *"O mecânico selecionado não é especialista neste tipo de reparo."* foi devolvida.
  * Teste de Controller: `deveTratarExcecaoDeEspecialidadeAoSalvar()` em [`OrdemServicoControllerTest.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/test/java/com/estudos/gestaoDeOficinaMecanica/controller/OrdemServicoControllerTest.java) $\rightarrow$ Garante que o usuário é redirecionado para a tela com alerta sem que o sistema gere erro HTTP 500.

### Regra de Negócio 2: Controle de Estoque de Fornecedores
* **Requisito:** A Ordem de Serviço só pode transitar seu status para `"Em Andamento"` se todas as peças vinculadas possuírem quantidade em estoque estritamente maior que zero (`quantidadeEstoque > 0`). Caso contrário, deve lançar `PecaIndisponivelException`.
* **Implementação na Arquitetura:**
  * Classe: [`OrdemServicoService.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/main/java/com/estudos/gestaoDeOficinaMecanica/service/OrdemServicoService.java)
  * Métodos: `iniciarServico(UUID id)` e `validarEstoquePecas(OrdemdeServico ordem)`
* **Testes de Qualidade Criados:**
  * Teste: `deveIniciarServicoComPecasEmEstoque()` $\rightarrow$ Altera status para `"Em Andamento"` com sucesso.
  * Teste: `deveLancarExcecaoQuandoPecaSemEstoque()` $\rightarrow$ Bloqueia o início e valida lançamento de `PecaIndisponivelException`.
  * Teste de Borda: `deveLancarExcecaoQuandoUmaPecaSemEstoque()` $\rightarrow$ Valida cenário onde 3 peças têm estoque, mas a 4ª tem estoque zero.
  * Teste de Borda: `deveIniciarServicoSemPecas()` $\rightarrow$ Valida serviços puramente de mão de obra (lista de peças vazia).

### Regra de Negócio 3: Gestão de Acessos e Unicidade de Usuários
* **Requisito:** Cada mecânico pode possuir uma conta de usuário (`Usuario`) associada. O login deve ser único no sistema e as senhas não podem ser persistidas em texto plano.
* **Implementação na Arquitetura:**
  * Classe: [`UsuarioService.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/main/java/com/estudos/gestaoDeOficinaMecanica/service/UsuarioService.java)
  * Uso do `PasswordEncoder.encode(...)` e validação com `existsByLogin`.
* **Testes de Qualidade Criados:**
  * Testes em [`UsuarioServiceTest.java`](file:///c:/Users/Luis/Downloads/gestaoDeOficinaMecanica/src/test/java/com/estudos/gestaoDeOficinaMecanica/service/UsuarioServiceTest.java):
    * `deveCarregarUsuarioPorUsername()` (integração lógica com Spring Security);
    * `deveSalvarUsuarioComSenhaCriptografada()` (valida invocação do BCrypt);
    * `deveLancarExcecaoQuandoLoginDuplicado()` (garante integridade cadastral de credenciais).

---

# 3. PARTE III: CONCLUSÃO

A execução deste trabalho demonstrou na prática a interdependência indissociável entre **Arquitetura de Software** e **Qualidade/Testes de Software**. 

Uma arquitetura limpa, bem particionada em camadas e orientada a injeção de dependências é o que viabiliza a existência de testes unitários rápidos, determinísticos e confiáveis. Por outro lado, a bateria de testes atua como o mecanismo de salvaguarda que preserva a integridade da arquitetura contra a erosão de código ao longo do tempo.

O sistema de **Gestão de Oficina Mecânica** foi concebido não como um simples conjunto de telas, mas como um produto de software modular, seguro, manutenível e extensivamente auditado por código de teste, atendendo com excelência aos padrões de Engenharia de Software preconizados na disciplina DCC061 da UFJF.

---

# 4. REFERÊNCIAS BIBLIOGRÁFICAS

* BASS, Len; CLEMENTS, Paul; KAZMAN, Rick. **Software Architecture in Practice**. 4. ed. Boston: Addison-Wesley Professional, 2021.
* BECK, Kent. **Test-Driven Development: By Example**. Boston: Addison-Wesley, 2002.
* BROWN, Simon. **Software Architecture for Developers: Visualise, document and explore your software architecture**. Leanpub, 2018.
* INTERNATIONAL ORGANIZATION FOR STANDARDIZATION. **ISO/IEC 25010: Systems and software engineering — Systems and software Quality Requirements and Evaluation (SQuaRE) — System and software quality models**. Genebra: ISO, 2011.
* INTERNATIONAL ORGANIZATION FOR STANDARDIZATION. **ISO/IEC/IEEE 42010: Systems and software engineering — Architecture description**. Genebra: ISO, 2011.
* INTERNATIONAL ORGANIZATION FOR STANDARDIZATION. **ISO/IEC/IEEE 29119: Software and systems engineering — Software testing**. Genebra: ISO, 2013.
* MYERS, Glenford J.; SANDLER, Corey; BADGETT, Tom. **The Art of Software Testing**. 3. ed. Hoboken: John Wiley & Sons, 2011.
* PARNAS, David L. **On the criteria to be used in decomposing systems into modules**. Communications of the ACM, v. 15, n. 12, p. 1053-1058, 1972.
* PRESSMAN, Roger S.; MAXIM, Bruce R. **Engenharia de Software: Uma Abordagem Profissional**. 9. ed. Porto Alegre: AMGH Editora, 2021.
* SOMMERVILLE, Ian. **Engenharia de Software**. 10. ed. São Paulo: Pearson Education do Brasil, 2019.
