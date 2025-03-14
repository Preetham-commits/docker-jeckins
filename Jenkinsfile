pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')  // Use the ID of your DockerHub credentials
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git credentialsId: 'github-credentials', url: 'https://github.com/Preetham-commits/docker-jeckins.git', branch: 'main'
                }
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    // Run Maven to build the project (skip tests for faster builds)
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Docker Login') {
            steps {
                script {
                    // Login to Docker Hub (Fixed Windows syntax issue)
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        bat 'echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build Docker image from Dockerfile
                    bat 'docker build -t preethamkarra97/my-app:latest .'  // Replace with your Docker image name
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Push Docker image to Docker Hub
                    bat 'docker push preethamkarra97/my-app:latest'  // Replace with your Docker image name
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace after pipeline execution
            cleanWs()
        }
    }
}
