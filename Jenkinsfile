pipeline {
    agent any

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
                    catchError(buildResult: 'FAILURE') {
                        bat 'mvn clean verify -DskipTests'
                    }
                }
            }
        }

        stage('Docker Login') {
		    steps {
		        script {
		            withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
		                bat 'echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin || exit 1'
		            }
		        }
		    }
		}

        stage('Docker Build') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE') {
                        bat 'docker build -t preethamkarra97/my-app:latest .'
                    }
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE') {
                        bat 'docker push preethamkarra97/my-app:latest'
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean up workspace after pipeline execution
                cleanWs()

                // Remove dangling images to save space
                bat 'docker rmi preethamkarra97/my-app:latest || echo "Image deletion failed (ignoring error)"'
                bat 'docker system prune -f || echo "Docker prune failed (ignoring error)"'
            }
        }
    }
}
