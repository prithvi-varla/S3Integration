# Spring Batch [![build status](https://travis-ci.org/prithvi-varla/S3Integration.svg?branch=develop)](https://travis-ci.org/prithvi-varla/S3Integration)

Spring Batch is a lightweight, comprehensive batch framework designed to enable the development of robust batch applications vital for the daily operations of enterprise systems.  Spring Batch builds upon the productivity, POJO-based development approach, and general ease of use capabilities people have come to know from the [Spring Framework](https://github.com/SpringSource/spring-framework), while making it easy for developers to access and leverage more advanced enterprise services when necessary.

More information about Spring batch is provided at https://docs.spring.io/spring-batch/trunk/reference/html/index.html

# Building from Source

Clone the git repository using the URL on the Github home page:

    $ git clone git@github.com:prithvi-varla/S3Integration.git

## Command Line
Gradle is the build tool used for Spring Batch.  You can perform a full build of Spring Batch via the command:

    $ ./gradlew build

 # Continous Build & Deployment

 Build and deploy using Travis-ci pipelie structure

https://travis-ci.org/prithvi-varla/S3Integration


 # Deployment

Done using AWS - ECS

Follow .travis.yml file and deploy.sh scripts to understand the deployment to AWS Fargate instance

http://18.237.248.16:9091/swagger-ui.html#


