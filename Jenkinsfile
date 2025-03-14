pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')  // Use the ID of your DockerHub credentials
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your GitHub repository
                git 'https://github.com/Preetham-commits/docker-jeckins.git'  // Replace with your GitHub URL
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    // Run Maven to build the project
                    sh 'mvn clean install'
                }
            }
        }

        stage('Docker Login') {
            steps {
                script {
                    // Login to Docker Hub
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build Docker image from Dockerfile
                    sh 'docker build -t preethamkarra97/my-app:latest .'  // Replace with your Docker image name
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Push Docker image to Docker Hub
                    sh 'docker push preethamkarra97/my-app:latest'  // Replace with your Docker image name
                }
            }
        }
    }

    post {
        always {
            // Clean up after the pipeline execution
            cleanWs()
        }
    }
}
