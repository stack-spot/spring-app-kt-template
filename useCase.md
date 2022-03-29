## Execução do projeto criado

Após criar o projeto, acesse o diretório `app` e execute o seguinte comando:

```bash
./gradlew clean build
```

Para testar a aplicação, execute o seguinte comando dentro do diretório `app`:

```bash
docker compose up
```

Com a aplicação em execução, acesse a url http://localhost:8080/actuator/health, e valide o seguinte retorno:

```
{"status":"UP"}
```