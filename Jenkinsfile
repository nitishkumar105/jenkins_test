pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "nitishk078/springboot-pipeline"
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'ec2-deploy',
                    url: 'https://github.com/nitishkumar105/jenkins_test.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $DOCKER_IMAGE:latest .
                '''
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-creds',
                    usernameVariable: 'Nitishk078',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['springboot-docker-ec2']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ubuntu@43.204.111.254 "
                      cd ~/app &&
                      docker compose pull &&
                      docker compose down --remove-orphans &&
                      docker compose up -d
                    "
                    '''
                }
            }
        }
    }

    post {
        success {
            echo " Deployed successfully to EC2 "
        }
        failure {
            echo " Deployment failed"
        }
    }
}
