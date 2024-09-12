Golden Raspberry Awards API
Este projeto é uma API RESTful que retorna os produtores que tiveram o maior e o menor intervalo entre dois prêmios consecutivos do Golden Raspberry Awards. Ele foi desenvolvido como parte de um desafio técnico e utiliza Java com Spring Boot para o backend.

A aplicação lê dados de um arquivo CSV ao iniciar e realiza os cálculos para determinar esses intervalos, retornando as informações no formato especificado.

Requisitos
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas no seu ambiente:

JDK 17: A versão correta do Java é necessária para compilar e rodar o projeto.
Maven: Para construir o projeto e gerenciar as dependências.
Git: Para clonar o repositório.
Postman ou cURL: Para testar o endpoint da API.
Como rodar o projeto
Siga os passos abaixo para rodar a aplicação localmente:

Clonar o repositório Primeiro, você precisa clonar o projeto do repositório para a sua máquina:

bash
Copy code
git clone https://github.com/seu-usuario/golden-raspberry-awards-api.git
Entre no diretório do projeto:

bash
Copy code
cd golden-raspberry-awards-api
Compilar o projeto Antes de rodar a aplicação, compile o projeto usando o Maven:

bash
Copy code
mvn clean install
Rodar a aplicação Agora você pode rodar a aplicação com o seguinte comando:

bash
Copy code
mvn spring-boot:run
Acessando a API Com a aplicação rodando, você pode acessar o endpoint da API para visualizar os dados.

Abra o seu navegador ou uma ferramenta de requisições como Postman ou cURL e faça uma requisição GET para o seguinte endereço:

bash
Copy code
http://localhost:8080/api/producers/intervals
Você deve receber uma resposta no formato JSON, semelhante ao seguinte exemplo:

json
Copy code
{
    "menorIntervalo": [
        {
            "producer": "Bo Derek",
            "interval": 6,
            "previousWin": 1984,
            "followingWin": 1990
        }
    ],
    "maiorIntervalo": [
        {
            "producer": "Bo Derek",
            "interval": 6,
            "previousWin": 1984,
            "followingWin": 1990
        }
    ]
}
Isso indica que a aplicação está funcionando corretamente e retornando os dados conforme solicitado.

Rodando os testes de integração
A aplicação inclui testes de integração para garantir que os dados retornados pela API estejam corretos e em conformidade com o desafio proposto. Para rodar os testes, siga os passos abaixo:

Executar os testes

No diretório do projeto, execute o seguinte comando para rodar os testes de integração:

bash
Copy code
mvn test
Verificar os resultados

Os testes irão validar se a lógica de cálculo dos intervalos entre prêmios está correta e se o retorno da API segue o formato esperado. Se todos os testes passarem, você verá uma saída semelhante a esta:

bash
Copy code
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
Isso indica que a API foi testada com sucesso e está funcionando conforme esperado.