pipeline {
    agent any
    environment {
        DOCKER_PASSWORD = credentials("docker_password")
    }

    stages {
        stage('Build & Test') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage("Deploy") {
            sh 'export MAJOR_VERSION=`git tag | cut -d . -f 1`'
            sh 'export MINOR_VERSION=`git tag | cut -d . -f 2`'
            sh 'export PATCH_VERSION=`git tag | cut -d . -f 3`'
            sh 'export NEW_MINOR_VERSION="$((MINOR_VERSION + 1))"'
            sh 'export IMAGE_VERSION=$MAJOR_VERSION.$NEW_MINOR_VERSION.$PATCH_VERSION'
            sh 'docker login docker.io -u claudiudaramusunibuc -p $DOCKER_PASSWORD'
            sh 'docker build -t claudiudaramusunibuc/hello-img:$IMAGE_VERSION .'
            sh 'docker push claudiudaramusunibuc/hello-img:$IMAGE_VERSION'
        }
    }
}