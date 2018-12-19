#!groovy

env.DOCKER_REGISTRY_PUSH = 'hub.docker.com'
env.CONTAINER1 = 'prithvi425/s3integration'

def buildAndPushToQA() {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub',
                        usernameVariable: 'dockeruser', passwordVariable: 'dockerpass']]) {
        echo 'Printing Environmental Variables: '
        sh 'env'
        stage 'build'
        checkout scm
        sh 'git rev-parse HEAD > commit'

  		env.BUILD = readFile('commit').trim()
        env.CONTAINERANDBUILD = env.CONTAINER1 + ':' + env.BUILD

		sh './gradlew --refresh-dependencies clean build'
        sh 'docker login -u ${dockeruser} -p ${dockerpass}'
        sh 'docker build -t ${DOCKER_REGISTRY_PUSH}/${CONTAINER1} .'
        sh 'docker tag  ${DOCKER_REGISTRY_PUSH}/${CONTAINER1}:latest ${DOCKER_REGISTRY_PUSH}/${CONTAINER1}:${BUILD}'

        stage 'push'
        sh 'docker push ${DOCKER_REGISTRY_PUSH}/${CONTAINER1}:${BUILD}'
        sh 'docker push ${DOCKER_REGISTRY_PUSH}/${CONTAINER1}:latest'


      }

}

stage 'Initialize'
node{
    println("Develop Branch. Kicking off Build and deploying Testing1")
    buildAndPushToQA()
}