# Use this for Docker Hub
docker login --username $DOCKER_HUB_USER --password $DOCKER_HUB_PSW

docker build -t hub.docker.com/prithvi425/s3integration .
docker tag hub.docker.com/prithvi425/s3integration:latest prithvi425/s3integration:latest
docker push prithvi425/s3integration:latest

ecs-deploy -c secondCluster -n secondService -i prithvi425/s3integration:latest