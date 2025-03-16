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
                        bat 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Docker Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        bat '''
                        echo Logging into Docker Hub...
                        echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin
                        if %ERRORLEVEL% NEQ 0 exit /b 1
                        '''
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
                cleanWs()

                // Clean up unused Docker images (optional)
                bat '''
                echo "Removing local Docker image..."
                docker rmi preethamkarra97/my-app:latest || echo "Image deletion failed (ignoring error)"
                '''
            }
        }
    }
}
