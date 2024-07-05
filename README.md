this project containg the app, helm chart, the docker and the CI/CD pipeline.

to test the locally, run this:
docker run --name mysql-test -e MYSQL_ROOT_PASSWORD=YourSecurePassword123 -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=admin -e MYSQL_PASSWORD=YourSecurePassword123 -p 3306:3306 -d mysql/mysql-server:5.7

mvn spring-boot:run

curl -X PUT http://localhost:8080/hello/paco -H "Content-Type: application/json" -d '{"dateOfBirth": "2020-11-26"}'
curl -X GET http://localhost:8080/hello/paco

The second project is the zproject_toni, that contains the AWS infrastructure for the project, and when you run this
docker or the helmchart, you need the database that zproject_toni raises.