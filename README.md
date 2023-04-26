# BANK API
Query data from the Narodowy Bank Polski's public APIs and return relevant information from them

#Start server
To start server you can use two options:

1) Using command line (usually use 8080 port):
cd {project folder}
java -jar target/dynatrace-0.0.1-SNAPSHOT.jar

2) Download project's image from docker repository and start in container
https://hub.docker.com/r/dudmax/dynatrace
docker pull dudmax/dynatrace

#Testing functionality
After starting a server you have two options to testing functionality
1) Via using Swagger-UI (exchanges-controller):
http://localhost:8080/swagger-ui/

2) Via just sending GET request (from browser/postman/etc)
http://localhost:8080/exchanges/USD/2023-04-19

#Operations examples
1) Request URL - http://localhost:8080/exchanges/USD/2023-04-19
Response - 4.2244

2) Request URL - http://localhost:8080/exchanges/EUR/minmax/35
Response in JSON:
{
  "minValue": 4.598,
  "maxValue": 4.7109
}

3) Request URL - http://localhost:8080/exchanges/GBP/diff/10
Response - 0.1058
