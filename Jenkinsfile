pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = "cost-tracking"
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
            echo "✅ Cost Tracking API is running via Docker Compose"
        }
        failure {
            echo "❌ Deployment failed"
        }
    }
}
