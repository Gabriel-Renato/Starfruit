# Sistema de Controle de Estoque

Sistema desenvolvido em Java para gerenciamento de produtos em estoque, com interface gráfica Swing e integração com banco de dados MySQL.

## 📋 Funcionalidades

- ✅ **Cadastro de Produtos**: Adicione novos produtos ao estoque com nome, descrição, quantidade e preço
- ✅ **Listagem de Produtos**: Visualize todos os produtos cadastrados em uma tabela
- ✅ **Edição de Produtos**: Modifique informações de produtos existentes
- ✅ **Exclusão de Produtos**: Remova produtos do estoque
- ✅ **Validação de Entrada**: Sistema robusto de validação que aceita formatação brasileira (vírgula como separador decimal)
- ✅ **Atualização em Tempo Real**: Recarregue a lista de produtos a qualquer momento

## 🛠 Tecnologias Utilizadas

- **Java 11**: Linguagem de programação
- **Java Swing**: Interface gráfica do usuário
- **MySQL**: Banco de dados relacional
- **JDBC**: API para conexão com banco de dados
- **Maven**: Gerenciamento de dependências

## 📦 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java JDK 11** ou superior
- **Maven 3.6+**
- **MySQL 5.7+** ou **MySQL 8.0+**
- **IDE** (opcional): IntelliJ IDEA, Eclipse ou VS Code

## 🗄 Configuração do Banco de Dados

### 1. Criar o Banco de Dados

Execute no MySQL:

```sql
CREATE DATABASE estoque_db;
```

### 2. Criar a Tabela de Produtos

```sql
USE estoque_db;

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500),
    quantidade INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);
```

### 3. Configurar Credenciais

Edite o arquivo `src/main/java/br/com/estoque/model/Conexao.java` e ajuste as credenciais:

```java
private static final String URL = "jdbc:mysql://localhost:3306/estoque_db";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

## 🚀 Como Executar

### Compilar o Projeto

```bash
mvn clean compile
```

### Executar a Aplicação

```bash
mvn exec:java -Dexec.mainClass="br.com.estoque.Main"
```

Ou compile e execute diretamente:

```bash
# Compilar
mvn clean package

# Executar
java -cp target/Starfruit-1.0-SNAPSHOT.jar:target/lib/* br.com.estoque.Main
```

### Executar pelo IDE

Execute a classe `br.com.estoque.Main` diretamente pelo seu IDE.

## 📁 Estrutura do Projeto

```
Starfruit/
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
└── src/
    └── main/
        └── java/
            └── br/
                └── com/
                    └── estoque/
                        ├── Main.java                    # Classe principal
                        ├── dao/
                        │   └── ProdutoDAO.java          # Acesso a dados (CRUD)
                        ├── model/
                        │   ├── Produto.java             # Modelo de dados
                        │   └── Conexao.java             # Gerenciamento de conexão
                        └── view/
                            ├── EstoqueView.java         # Tela principal
                            └── ListaProdutosView.java   # Tela de listagem
```

## 💻 Uso da Aplicação

### Tela Principal

A tela principal permite:

1. **Adicionar Produto**:
   - Preencha os campos: Nome, Descrição, Quantidade e Preço
   - Clique em "Adicionar"
   - O produto será salvo no banco de dados

2. **Listar Produtos**:
   - Clique em "Listar" para abrir a janela de listagem
   - Todos os produtos serão exibidos em uma tabela

### Tela de Listagem

Na tela de listagem você pode:

1. **Visualizar Produtos**: Todos os produtos são exibidos automaticamente
2. **Atualizar**: Clique em "Atualizar" para recarregar a lista
3. **Editar**: 
   - Selecione um produto na tabela
   - Clique em "Editar"
   - Modifique os campos desejados
   - Confirme as alterações
4. **Apagar**:
   - Selecione um produto na tabela
   - Clique em "Apagar"
   - Confirme a exclusão

## ✨ Características Especiais

### Validação Inteligente de Números

O sistema aceita entradas no formato brasileiro:
- ✅ "3,5" será convertido para 3.5
- ✅ "10,99" será convertido para 10.99
- ✅ "3,5 kl" será automaticamente limpo e convertido para 3.5
- ✅ Remove espaços e texto extra automaticamente

### Validações Implementadas

- ✅ Campo Nome é obrigatório
- ✅ Quantidade deve ser um número inteiro positivo
- ✅ Preço deve ser um número válido (aceita vírgula ou ponto)
- ✅ Mensagens de erro claras e objetivas
- ✅ Confirmação antes de excluir produtos

## 🐛 Troubleshooting

### Erro de Conexão com Banco de Dados

- Verifique se o MySQL está rodando
- Confirme se as credenciais em `Conexao.java` estão corretas
- Verifique se o banco `estoque_db` existe
- Certifique-se de que a tabela `produto` foi criada

### Erro ao Executar

- Verifique se o Java 11+ está instalado: `java -version`
- Verifique se o Maven está instalado: `mvn -version`
- Certifique-se de que todas as dependências foram baixadas: `mvn clean install`

## 📝 Notas de Desenvolvimento

- O projeto utiliza o padrão de arquitetura em camadas (DAO, Model, View)
- As conexões com banco são gerenciadas automaticamente (try-with-resources)
- Interface gráfica responsiva com validações em tempo real

## 👨‍💻 Autor

Projeto desenvolvido para gerenciamento de estoque.

## 📄 Licença

Este projeto é de uso interno.

---

**Versão**: 1.0-SNAPSHOT  
**Última atualização**: 2024

