pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/nitishkumar105/jenkins_test.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t springboot-pipeline-app .
                '''
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker stop springboot-pipeline-app || true
                docker rm springboot-pipeline-app || true

                docker run -d \
                --name springboot-pipeline-app \
                -p 9090:8080 \
                springboot-pipeline-app
                '''
            }
        }
    }
}
