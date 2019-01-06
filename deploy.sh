pip install --user awscli
export PATH=$PATH:$HOME/.local/bin

add-apt-repository ppa:eugenesan/ppa
apt-get update
apt-get install jq -y

curl https://raw.githubusercontent.com/silinternational/ecs-deploy/master/ecs-deploy | sudo tee -a /usr/bin/ecs-deploy
sudo chmod +x /usr/bin/ecs-deploy

# Use this for AWS ECR
# eval $(aws ecr get-login --region us-west-2)

# Use this for Docker Hub
docker login --username $DOCKER_HUB_USER --password $DOCKER_HUB_PSW

docker build -t haoliangyu/ecs-auto-deploy .
docker tag hub.docker.com/prithvi425/s3integration:latest prithvi425/s3integration:latest
docker push prithvi425/s3integration:latest

ecs-deploy -c secondCluster -n secondService -i prithvi425/s3integration:latest