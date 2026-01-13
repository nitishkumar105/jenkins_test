pipeline {
    agent any

    environment {
         COMPOSE_PROJECT_NAME = "springboot-pipeline"
         POSTGRES_DB = credentials('POSTGRES_DB')
         POSTGRES_USER = credentials('POSTGRES_USER')
         POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/nitishkumar105/jenkins_test.git'
            }
        }

        stage('Stop Existing Stack') {
            steps {
                sh '''
                docker compose down || true
                '''
            }
        }

        stage('Build & Start Stack') {
            steps {
                sh '''
                docker compose up -d --build
                '''
            }
        }

        stage('Verify Containers') {
            steps {
                sh '''
                docker compose ps
                '''
            }
        }
    }

    post {
        success {
            echo " jenkins-test-API is running via Docker Compose"
            echo "Yes i can do it"
        }
        failure {
            echo " Deployment failed"
        }
    }
}
