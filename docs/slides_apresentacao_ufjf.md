# SLIDES E ROTEIRO DE APRESENTAÇÃO (15 A 20 MINUTOS)
## DISCIPLINA: DCC061 – ENGENHARIA DE SOFTWARE (UFJF)
**Professor:** Fabrício Martins Mendonça  
**Temas:** Arquitetura de Software & Testes e Qualidade de Software  
**Projeto Prático:** Sistema de Gestão de Oficina Mecânica  

---

## ESTRUTURA TEMPORAL DA APRESENTAÇÃO (18 MINUTOS)
* **Bloco 1: Introdução e Tema 1 - Arquitetura de Software** (5 minutos)
* **Bloco 2: Tema 2 - Testes e Qualidade de Software** (5 minutos)
* **Bloco 3: Aplicação Prática no Código-fonte** (6 minutos)
* **Bloco 4: Conclusão e Encerramento** (2 minutos)

---

### SLIDE 1: Capa da Apresentação (1 min)
* **Título:** Arquitetura de Software e Garantia da Qualidade por meio de Testes Automatizados
* **Subtítulo:** Um estudo teórico e aplicação prática no Sistema de Gestão de Oficina Mecânica
* **Instituição:** Universidade Federal de Juiz de Fora (UFJF) – Departamento de Ciência da Computação (DCC)
* **Disciplina:** DCC061 – Engenharia de Software | Prof. Fabrício Martins Mendonça
* **Integrantes do Grupo:** [Nome Completo e Matrícula de cada integrante]
> 🎙️ **Fala do Apresentador:** *"Bom dia/Boa tarde a todos, professor Fabrício e colegas. Nosso grupo escolheu aprofundar dois temas centrais da disciplina: Arquitetura de Software e Testes/Qualidade de Software. Desenvolvemos uma aplicação prática completa em Java e Spring Boot — um Sistema de Gestão de Oficina Mecânica — para demonstrar como decisões arquiteturais limpas viabilizam testes unitários rigorosos e alta qualidade de software."*

---

### SLIDE 2: Agenda do Seminário (0.5 min)
* 1. Arquitetura de Software: Conceitos, Normas, Ferramentas e IA
* 2. Testes e Qualidade de Software: Da Teoria às Certificações do Mercado
* 3. Estudo de Caso Prático: O Sistema de Gestão de Oficina Mecânica
* 4. Demonstração Prática: Padrões Arquiteturais e Bateria de Testes Unitários
* 5. Considerações Finais e Conclusões
> 🎙️ **Fala do Apresentador:** *"Nossa apresentação está dividida em quatro momentos: primeiro abordaremos o referencial teórico dos dois temas solicitados; em seguida, mostraremos como estruturamos a arquitetura do nosso sistema e como implementamos os testes para validar as regras críticas de negócio."*

---

### SLIDE 3: Tema 1 – O que é Arquitetura de Software? (1.5 min)
* **Definição (Bass, Clements e Kazman / SEI):** Estrutura dos elementos de software, propriedades visíveis externamente e relacionamentos mútuos.
* **Foco em Requisitos Não-Funcionais:** Desempenho, Modificabilidade, Segurança, Testabilidade e Disponibilidade.
* **Evolução Histórica:**
  * 1968: Crise do Software e início da modularização (David Parnas, 1972);
  * Anos 90: Consolidação da disciplina (Shaw & Garlan, CMU/SEI);
  * Anos 2000+: Decomposição MVC, SOA, Microsserviços e Event-Driven.
* **Importância:** Redução do custo da mudança (Curva de Boehm) e alinhamento entre stakeholders.
> 🎙️ **Fala do Apresentador:** *"A arquitetura representa as decisões de design mais difíceis de alterar no futuro. Como ensina Parnas desde 1972, a boa decomposição modular isola o que muda frequentemente. Corrigir um erro arquitetural em produção chega a custar 100 vezes mais do que na fase de design."*

---

### SLIDE 4: Tema 1 – Normas, Mercado e o Impacto da IA na Arquitetura (2 min)
* **Normas e Modelos Formais:**
  * **ISO/IEC/IEEE 42010:** Padronização internacional de descrições arquiteturais (Views & Viewpoints);
  * **Modelo 4+1 Vistas de Kruchten:** Vistas Lógica, Processo, Desenvolvimento, Física e Cenários.
* **Mercado de Trabalho:** Cargos de Arquiteto de Software, Arquiteto de Soluções e Staff Engineer.
* **Certificações Relevantes:** iSAQB (CPSA-F e CPSA-A), TOGAF 9 e certificações de nuvem (AWS/Azure Solutions Architect).
* **Impacto da Inteligência Artificial:**
  * Geração e refatoração assistida de código arquitetural;
  * AIOps: Monitoramento preditivo e auto-escalonamento de microsserviços;
  * Arquiteturas emergentes: Bancos vetoriais e pipelines RAG.
> 🎙️ **Fala do Apresentador:** *"No mercado profissional, o arquiteto orienta decisões técnicas respaldadas por normas como a ISO/IEC/IEEE 42010 e certificações globais como iSAQB e TOGAF. Hoje, a IA já auxilia tanto na detecção de dívida técnica e acoplamento indevido quanto no surgimento de novos padrões voltados a dados e modelos de linguagem."*

---

### SLIDE 5: Tema 2 – Testes e Qualidade de Software (1.5 min)
* **Qualidade de Software (Pressman & Sommerville):** Conformidade com requisitos explícitos e implícitos de clientes e usuários.
* **Conceito de Teste (Glenford Myers, 1979):** *"Testar é executar o software com a intenção deliberada de encontrar defeitos."*
* **A Pirâmide de Testes (Mike Cohn):**
  * Topo: Testes E2E (Mais lentos, caros, alto escopo);
  * Meio: Testes de Integração (Validação de contratos entre módulos);
  * Base: **Testes Unitários** (Rápidos, executam em milissegundos, isolados via Mocks).
* **Importância:** Confiança para refatorar código (*Refactoring Safety*) e documentação executável do sistema.
> 🎙️ **Fala do Apresentador:** *"Muitos pensam que testar é provar que o software funciona. Myers ensinou que a mentalidade correta é tentar encontrar falhas antes do usuário. A base de qualquer sistema confiável é a pirâmide de testes, onde centenas de testes unitários rápidos garantem que as regras de negócio nunca sofram regressão."*

---

### SLIDE 6: Tema 2 – Padrões, Normas, Certificações e a IA na Qualidade (2 min)
* **Padrões e Normas Internacionais:**
  * **ISO/IEC 25010 (SQuaRE):** 8 características de qualidade de produto (Adequação Funcional, Confiabilidade, Segurança, Manutenibilidade, etc.);
  * **ISO/IEC/IEEE 29119:** Processos formais e técnicas de projeto de testes (Valores Limite, Particionamento em Classes de Equivalência).
* **Profissões no Mercado:** QA Engineer, SDET (Software Development Engineer in Test), Test Lead.
* **Certificações Globais:** ISTQB (CTFL - Foundation Level, CTAL - Advanced Level), CBTS (Brasil).
* **Impactos da Inteligência Artificial em Testes:**
  * Geração automática de cenários de teste de borda e testes parametrizados;
  * Testes de interface auto-regenerativos (*Self-Healing Tests*);
  * Priorização inteligente de testes de regressão em pipelines de CI/CD.
> 🎙️ **Fala do Apresentador:** *"A norma ISO 25010 define o que é qualidade de software através de oito pilares fundamentais. O profissional de QA hoje trabalha como SDET, escrevendo código que testa código. Com a IA, temos ferramentas capazes de analisar branches não cobertos e sugerir testes para casos extremos que nós desenvolvedores poderíamos esquecer."*

---

### SLIDE 7: Estudo de Caso Prático – Sistema de Gestão de Oficina Mecânica (1 min)
* **Domínio do Negócio:** Controle operacional de manutenções automotivas, estoque de peças e equipe de mecânicos.
* **Stack Tecnológica Adotada:**
  * Java 25 & Spring Boot 4.1;
  * Spring Data JPA & Hibernate (PostgreSQL);
  * Spring Security (Autenticação BCrypt e Controle de Acesso);
  * Thymeleaf & Bootstrap 5 (Renderização Server-Side MVC);
  * JUnit 5 & Mockito (Suíte de Testes Unitários Isolados).
> 🎙️ **Fala do Apresentador:** *"Para materializar esses conceitos, construímos o Sistema de Gestão de Oficina Mecânica. Ele implementa regras estritas de compatibilidade técnica e estoque, além de autenticação para os mecânicos e dashboard operacional."*

---

### SLIDE 8: A Arquitetura Implementada no Código-fonte (2 min)
* **Estilo Adotado:** Arquitetura em Camadas (*Layered Architecture*) + MVC.
* **Isolamento de Responsabilidades:**
  * `View (Thymeleaf)`: Interface do usuário, sem lógica de persistência ou regras de negócio;
  * `Controller`: Recepção de requisições HTTP, validação preliminar e redirecionamento PRG (*Post-Redirect-Get*);
  * `Service Layer`: Núcleo da aplicação onde residem as regras de negócio e lançamentos de exceção;
  * `Repository`: Abstração de persistência usando o padrão Repository do Spring Data JPA;
  * `Domain Model & DTOs`: Encapsulamento com entidades JPA e Java Records.
* **Segurança Transversal:** `SecurityConfig` interceptando rotas e gerenciando ciclo de vida de sessões.
> 🎙️ **Fala do Apresentador:** *"No diagrama do slide, vemos como as camadas se comunicam estritamente de cima para baixo. Os Controllers nunca chamam o banco diretamente; eles delegam para a Service. Usamos DTOs com Java Records para transportar dados e criamos um GlobalExceptionHandler com @ControllerAdvice para garantir resiliência e evitar erros 500 na tela do usuário."*

---

### SLIDE 9: Aplicação da Qualidade – As Regras de Negócio Testadas (2 min)
* **Regra 1: Validação de Especialidade do Mecânico**
  * Mecânico com especialidade (`Motor`, `Eletrica`, `Suspensão`) deve ser compatível com o `tipoServico` da OS.
  * *Violação:* Dispara `EspecialidadeIncompativelException`.
* **Regra 2: Controle de Peças e Estoque de Fornecedores**
  * Para mudar o status da OS para `"Em Andamento"`, todas as peças vinculadas devem ter `quantidadeEstoque > 0`.
  * *Violação:* Dispara `PecaIndisponivelException`.
* **Regra 3: Integridade Cadastral de Acessos**
  * Logins únicos no banco e senhas obrigatoriamente criptografadas com hash seguro.
> 🎙️ **Fala do Apresentador:** *"A qualidade de um sistema é medida pela sua capacidade de fazer cumprir as regras do domínio. Se um usuário tentar atribuir um mecânico de suspensão para consertar um motor, o sistema barra na hora. E se tentar iniciar um serviço cuja peça está zerada no estoque, a exceção é disparada e capturada com elegância."*

---

### SLIDE 10: Evidência Prática – A Bateria de Testes Unitários (2 min)
* **Técnica:** Testes Unitários com Dublês de Teste (*Mocks* via Mockito).
* **Cobertura Implementada:**
  * `MecanicoServiceTest`: CRUD, busca por ID, tratamento de IDs inexistentes;
  * `OrdemServicoServiceTest`:
    * Cenário Feliz: Especialidade compatível e peças em estoque;
    * Cenário Negativo 1: Mecânico incompatível disparando `EspecialidadeIncompativelException`;
    * Cenário Negativo 2: Peça com estoque zero disparando `PecaIndisponivelException`;
    * Cenário de Borda: Uma peça sem estoque no meio de várias com estoque;
    * Cenário de Borda: OS sem peças (serviço exclusivo de mão de obra);
  * `UsuarioServiceTest`: Hash BCrypt de senha e bloqueio de logins duplicados;
  * `MecanicoControllerTest`, `OrdemServicoControllerTest`, `UsuarioControllerTest`: Cobertura dos blocos `try-catch` e transporte de mensagens via `RedirectAttributes`.
> 🎙️ **Fala do Apresentador:** *"Construímos quase 50 casos de teste unitários. Graças ao uso do Mockito, nenhum teste precisa de banco de dados ligado. Eles rodam em frações de segundo no build e garantem que se qualquer desenvolvedor alterar uma linha da lógica de validação amanhã, o teste falhará imediatamente."*

---

### SLIDE 11: Conclusões e Lições Aprendidas (1 min)
* **Interdependência:** Arquitetura de Software e Testes são duas faces da mesma moeda. Sem arquitetura desacoplada, o código é intestável; sem testes, a arquitetura se deteriora.
* **Benefícios Observados no Projeto:**
  * Facilidade de manutenção e refatoração com segurança;
  * Zero dependência de frameworks externos de compilação nas entidades;
  * Interface rica com Thymeleaf beneficiando-se do tratamento global de erros;
  * Código modular pronto para evolução (ex.: transição futura para APIs REST ou Microsserviços).
> 🎙️ **Fala do Apresentador:** *"Concluímos que a arquitetura não é burocracia, mas a garantia de que o software pode evoluir. E os testes são o contrato vivo que assegura que o sistema continua cumprindo seu papel com confiabilidade e qualidade."*

---

### SLIDE 12: Perguntas e Agradecimentos (0.5 min)
* Agradecimentos ao Prof. Dr. Fabrício Martins Mendonça e aos colegas.
* Repositório do Código-Fonte: Disponível via Classroom / Git.
* Espaço aberto para dúvidas da banca e da turma.
> 🎙️ **Fala do Apresentador:** *"Agradecemos a atenção do professor Fabrício e de todos os colegas e ficamos à disposição para responder às perguntas. Muito obrigado!"*
