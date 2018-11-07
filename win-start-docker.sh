#Stop all containers
docker stop $(docker ps -a -q)

#Remove containers
docker rm postgres-stats
docker rm rso-stats

#Remove image
#docker rmi stats

#DB
docker run -d --name postgres-stats -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=rso-stats -p 5432:5433 postgres:latest

#MicroService
mvn clean package
docker build -t stats .
docker run --name rso-stats -p 8082:8082 stats
