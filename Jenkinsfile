
pipeline {
  agent any

  tools {
      maven 'Maven3'
  }

  environment {
      //PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
      DOCKERHUB_CREDENTIALS_ID = 'c32ad004-8b3a-4016-a96d-1b828ee6fa08'
      DOCKERHUB_REPO = 'lecture-assignment'
      DOCKER_IMAGE_TAG = 'latest'
  }

  stages {

      stage('Checkout') {
          steps {
              git 'https://github.com/Jere-4/OTP1_assignments'
          }
      }

      stage('Run Tests') {
          steps {
              bat 'mvn clean test'
          }
      }

      stage('Code Coverage') {
          steps {
              bat 'mvn jacoco:report'
          }
      }

      stage('Build Docker Image') {
          steps {
              script {
                  docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
              }
          }
      }

      stage('Push Docker Image to Docker Hub') {
          steps {
              script {
                  docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                      docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                  }
              }
          }
      }

  }
}
