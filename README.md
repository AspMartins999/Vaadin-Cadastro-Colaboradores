# Flow CRM Tutorial


Este projeto é usado como tutorial para criar seu próprio aplicativo Vaadin com Spring Boot. Ele contém toda a configuração necessária e alguns arquivos necessários para implantar em produção na nuvem (HEROKU).

O aplicativo tem por objetivo gerenciar contatos de empresas (um CRM). Possui uma listagem de contatos com opções de pesquisa (filtro por nome), um formulário de CRUD de contatos, e um painel (dashboard) com totais de contatos por empresa. 


## Executar a aplicação 

Trata-se de um projeto Maven. Para rodar a partir da linha de comandos,
digite `mvnw` (Windows), or `./mvnw` (Mac & Linux). Em seguida, abra a URL
http://localhost:8080 em seu browser.

Você pode importar o projeto para o IDE de sua escolha como faria com qualquer projeto Maven.

## Leituras adicionais 

[how to import Vaadin projects to different 
IDEs](https://vaadin.com/docs/latest/flow/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).

## Implantação em Produção

Para criar uma versão de produção (production build), use  `mvnw clean package -Pproduction` (Windows),
ou `./mvnw clean package -Pproduction` (Mac & Linux).


Em seguida, o projeto estará empacotado em um arquivo JAR com todas as dependências e recursos de front-end, prontos para serem implantados. O arquivo pode ser encontrado na pasta  `target` depois que o build é encerrado.

Uma vez que o arquivo JAR é construído, você pode executá-lo (na pasta raiz do projeto), usando:
 
`java -jar target/flowcrmtutorial-1.0-SNAPSHOT.jar` (no Linux) ou

`java -jar target\flowcrmtutorial-1.0-SNAPSHOT.jar` (no Windows)


## Estrutura do Projeto 

- `MainLayout.java` em `src/main/java` contém a configuração de navigação (i.e., the   side/top bar and the main menu). Este setup usa:

  [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contém as visões(views) do server-side Java da aplicação.
- `views` folder in `frontend/` contém the client-side JavaScript views of your aplicação.
- `themes` folder in `frontend/` contém the custom CSS styles.

## Links úteis

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- View use case application that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Discover Vaadin's set of CSS utility classes that enable building any UI without custom CSS in the [docs](https://vaadin.com/docs/latest/ds/foundation/utility-classes). 
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).
