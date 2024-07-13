this project containg the app, helm chart, the docker and the CI/CD pipeline.

to test the locally, run this:
docker run --name mysql-test -e MYSQL_ROOT_PASSWORD=YourSecurePassword123 -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=admin -e MYSQL_PASSWORD=YourSecurePassword123 -p 3306:3306 -d mysql/mysql-server:5.7

mvn spring-boot:run

curl -X PUT http://localhost:8080/hello/paco -H "Content-Type: application/json" -d '{"dateOfBirth": "2020-11-26"}'
curl -X GET http://localhost:8080/hello/paco

This contains the Java app, and the helm chart, and produces a docker image for the helm chart to run in Kubernetes or 
to run alone or in a deployment.

TERRAFORM
To apply terraform you have to go to:
toni/terraform/environments_main-infra/test
terraform init ... apply

to run the tests with the database in memory, run this command :
mvn test -Dspring.profiles.active=test