# Spring Kotlin Base Template

- **Descrição:** O template [`spring-app-kt-template`](https://github.com/stack-spot/spring-app-kt-template) provê a base de um projeto Spring/Kotlin com infra.
- **Categoria:** Template.
- **Stack:** zup-kotlin-stack.
- **Criado em:** 08/02/2022.
- **Última atualização:** 22/03/2022.
- **Download:** https://github.com/stack-spot/spring-app-kt-template.git


## **Visão Geral**
### **spring-app-kt-template**

O **spring-app-kt-template** fornece a estrutura mínima de uma aplicação Kotlin com Spring, assim como a capacidade de utilização de logging.
Também são fornecidos os arquivos e configurações bases para criação de infra EKS na AWS. 

## **Uso**

### **Pré-requisitos**
- Para utilizar esse template, é necessário ter instalado o cli da Stack Spot e já ter executado o comando:
```stk import https://github.com/stack-spot/zup-kotlin-stack```.
- Possuir Java 11 instalado na máquina.


### **Aplicação**
Para criar um projeto utilizando o **spring-app-kt-template**, execute o comando a baixo:

```
stk create app nome-da-minha-aplicacao -t zup-kotlin-stack/spring-app-kt-template  --env-url url-do-projeto-git-contendo-o-ambiente-compartilhado

```
Mais detalhes sobre o parâmetro env-url podem ser obtidos na [documentação oficial](https://docs.stackspot.com/v3.0.0/stk-cli/commands/commands-list/stk-create-app/).

## **Configuração**

### **Inputs**
Os inputs necessários para utilizar o template são:

| **Campo** | **Valor** | **Descrição** |
| :--- | :--- | :--- |
| Project Name| texto | Nome da aplicação  |
| Group id| texto | Estrutura de pacotes  |
| Do you want to expose rest api | booleano | Verificação para adição de libs relacionadas a exposição de APIs Spring Rest  |

