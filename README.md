# TaskManager - Gerenciador de Tarefas

Projeto do módulo intermediário da Trilha Java (Capacita iRede).

## Evolução do projeto (refatoração)

Este projeto é a **evolução** do TaskManager entregue no módulo básico.

| | Versão inicial (módulo básico) | Versão atual (módulo intermediário) |
|---|---|---|
| Interface | Console (terminal, `Scanner`) | **Interface gráfica com JavaFX + FXML** |
| Armazenamento | Em memória (`ArrayList`), dados perdidos ao fechar | **Banco PostgreSQL via JDBC**, dados persistentes |
| Acesso a dados | `GerenciadorDeTarefas` com lista em memória | **Padrão DAO** (`TarefaDAO` + `Conexao`) |
| Listas | `ArrayList<Tarefa>` | **Classe genérica** `ListaGenerica<T>` com curingas |
| Modelo | Classe `Tarefa` | Classe `Tarefa` + **herança** (`TarefaPrioritaria`) |
| Testes | Nenhum | **JUnit 5** |

A versão console foi substituída pela tela JavaFX. O modelo (`Tarefa`, enums) foi mantido e reaproveitado.

## Funcionalidades

- Cadastrar tarefa (título, descrição, responsável, prazo, prioridade)
- Listar tarefas em uma tabela
- Editar tarefa selecionada
- Marcar tarefa como concluída
- Remover tarefa (com janela de confirmação)

## Requisitos do módulo atendidos

| Requisito | Onde está |
|---|---|
| Generics (`?`, `? extends T`, `? super T`) | `model/ListaGenerica.java` |
| JavaFX (`Application`, `start(Stage)`, TableView, botões, DatePicker, ComboBox) | `app/MainApplication.java` |
| FXML + Controller | `app/main.fxml` e `controller/MainController.java` |
| JDBC com `PreparedStatement` e `ResultSet` | `infra/TarefaDAO.java` |
| Conexão centralizada | `infra/Conexao.java` |
| CRUD completo (inserir, listar, atualizar, remover) | `infra/TarefaDAO.java` |
| Herança | `model/TarefaPrioritaria.java` |
| Testes JUnit (`@Test`, `Assertions`) | `test/TarefaTest.java` |

## Estrutura

```
src/com/leonardo/taskmanager/
  app/         MainApplication.java, main.fxml
  controller/  MainController.java
  infra/       Conexao.java, TarefaDAO.java
  model/       Tarefa, TarefaPrioritaria, Prioridade, Status, ListaGenerica
  exceptions/  TarefaException.java
test/
  TarefaTest.java
```

## Como rodar

### 1. Pré-requisitos

- JDK 21
- PostgreSQL instalado e rodando
- JavaFX SDK 21 (https://gluonhq.com/products/javafx/)
- Driver JDBC do PostgreSQL, `postgresql-42.7.4.jar` (https://jdbc.postgresql.org/download/)
- IntelliJ IDEA (recomendado)

### 2. Criar o banco e a tabela

No pgAdmin (Query Tool) ou no `psql`:

```sql
CREATE DATABASE taskmanager;
```

Conecte no banco `taskmanager` e execute:

```sql
CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100),
    descricao TEXT,
    responsavel VARCHAR(100),
    prazo DATE,
    prioridade VARCHAR(20),
    status VARCHAR(20),
    concluida BOOLEAN DEFAULT FALSE
);
```

### 3. Ajustar as credenciais

Em `src/com/leonardo/taskmanager/infra/Conexao.java`, altere para o usuário e a senha do **seu** PostgreSQL:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/taskmanager";
private static final String USUARIO = "postgres";
private static final String SENHA = "1234";
```

### 4. Configurar o projeto no IntelliJ

1. Abra a pasta `task_manager_controle_tarefas` como projeto.
2. Em **File > Project Structure > Libraries**, adicione:
    - o `postgresql-42.7.4.jar`
    - todos os `.jar` da pasta `lib` do JavaFX SDK
3. Confirme que a pasta `src` está marcada como **Sources Root** e a pasta `test` como **Test Sources Root**.

### 5. Executar

1. Em **Run > Edit Configurations**, crie uma configuração do tipo **Application**:
    - **Main class:** `com.leonardo.taskmanager.app.MainApplication`
    - **Module:** `task_manager_controle_tarefas`
    - **VM options** (troque pelo caminho do seu JavaFX):

   ```
   --module-path "CAMINHO/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml
   ```

2. Execute com o botão verde. A janela do TaskManager deve abrir.

Se aparecer o erro "JavaFX runtime components are missing", o VM option acima não foi configurado. Se aparecer "Location is not set", faça **Build > Rebuild Project**.

### 6. Rodar os testes

Abra `test/TarefaTest.java` e clique na seta verde ao lado de `class TarefaTest`. Os testes cobrem:

- criação de tarefa
- conclusão de tarefa
- `ListaGenerica` (adicionar e remover)
- `ListaGenerica` aceitando subclasses com `? extends T`

## Como conferir que os dados estão no banco

Depois de criar uma tarefa no app, execute no pgAdmin:

```sql
SELECT * FROM tarefas;
```

Feche e abra o app: as tarefas continuam na tabela, pois estão salvas no PostgreSQL.

## Limitações conhecidas

- Os testes do DAO usam PostgreSQL local, não SQLite em memória, por isso os testes automatizados cobrem o modelo e os Generics.
- A estilização com CSS ainda não foi aplicada.
- `TarefaPrioritaria` está implementada e demonstra herança, mas a tela cadastra apenas tarefas comuns.
- O projeto não usa Maven: as bibliotecas são adicionadas manualmente.

## Autor

Leonardo Ferreira