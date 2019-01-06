#!groovy

env.DOCKER_REGISTRY_PUSH = 'hub.docker.com'
env.CONTAINER1 = 'prithvi425/s3integration'

def buildAndPushToQA() {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub',
                        usernameVariable: 'dockeruser', passwordVariable: 'dockerpass'],
                        [$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                        credentialsId: 'awsCred', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        echo 'Printing Environmental Variables: '
        sh 'env'
        stage 'build'
        checkout scm
        sh 'git rev-parse HEAD > commit'

  		env.BUILD = readFile('commit').trim()
        env.CONTAINERANDBUILD = env.CONTAINER1 + ':' + env.BUILD

		echo 'Add proxies for working gradle build in concu nonprod jenkins server'
		sh './gradlew -Dhttp.proxyHost="http://proxy-us-aws-nonprod.concurasp.com" -Dhttp.proxyPort="3128" -Dhttps.proxyHost="proxy-us-aws-nonprod.concurasp.com" -Dhttps.proxyPort="3128" -Dhttp.nonProxyHosts="auto-util2.concur.concurtech.org|*.concurtech.net|localhost|*.concurasp.com|*.concurtech.org" -Dhttps.nonProxyHosts="auto-util2.concur.concurtech.org|*.concurtech.net|localhost|*.concurasp.com|*.concurtech.org" --refresh-dependencies clean build'

        echo 'Login into Dockerhub'
        sh 'docker login -u ${dockeruser} -p ${dockerpass}'

        echo 'Build the docker image'
        sh 'docker build -t ${DOCKER_REGISTRY_PUSH}/${CONTAINER1} .'

        echo 'Tag docker image with build number and latest tags'
        sh 'docker tag  ${DOCKER_REGISTRY_PUSH}/${CONTAINER1}:latest ${CONTAINER1}:latest'
        sh 'docker tag  ${CONTAINER1}:latest ${CONTAINER1}:${BUILD}'

        stage 'push'

        echo 'Push docker image into Dockerhub'
        sh 'docker push ${CONTAINER1}:${BUILD}'
        sh 'docker push ${CONTAINER1}:latest'


        stage 'deploy'

        sh 'mkdir ~/.aws && echo "[default]\naws_access_key_id = $AWS_ACCESS_KEY_ID\naws_secret_access_key = $AWS_SECRET_ACCESS_KEY\nregion=us-west-2" > ~/.aws/credentials'

        echo 'Login using AWS CLI'
        sh 'yes "" | aws configure --profile default ; aws ecr get-login > awslogin.sh ; sudo sh awslogin.sh'

        echo 'Register task definition'
        sh 'aws ecs register-task-definition --cli-input-json file://deployment.json'

        echo 'Update service'
        sh 'aws ecs update-service --cluster secondCluster --service secondService --task-definition s3Integration --desired-count 1'


      }

}

stage 'Initialize'
node{
    println("Develop Branch. Kicking off Build and deploying Testing1")
    buildAndPushToQA()
}