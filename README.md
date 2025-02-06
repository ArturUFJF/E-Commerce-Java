# Sistema de Vendas

Este é um sistema de vendas desenvolvido em Java utilizando o Maven como gerenciador de dependências. O sistema permite a gestão de vendas, aplicação de cupons de desconto e geração de relatórios.

## Tecnologias Utilizadas

- **Java**
- **Maven**
- **JUnit** (para testes de unidade)

## Estrutura do Projeto

```
/src
 ├── main
 │   ├── java
 │   │   ├── br.ufjf.dcc.dcc025.Models   # Modelos de domínio
 │   │   ├── br.ufjf.dcc.dcc025.Services # Serviços do sistema
 │   │   ├── br.ufjf.dcc.dcc025.Exceptions # Exceções personalizadas
 │   ├── resources
 ├── test
 │   ├── java
 │   │   ├── br.ufjf.dcc.dcc025.Models.SistemaVendasTest # Testes unitários
```

## Como Executar o Projeto

Certifique-se de ter o Maven instalado e configurado corretamente. Para compilar e executar o sistema, utilize os seguintes comandos no terminal:

```sh
mvn clean install
mvn exec:java -Dexec.mainClass="br.ufjf.dcc.dcc025.Main"
```

## Testes

O projeto possui testes de unidade localizados em:
```
test -> java -> br.ufjf.dcc.dcc025.Models.SistemaVendasTest
```
Para executar os testes, utilize o seguinte comando:

```sh
mvn test
```

## Autor

Artur Ferreira de Castro

---

Este projeto foi desenvolvido como parte de estudos e práticas da disciplina de Orientação a Objeto.

