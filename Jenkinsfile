pipeline {
    agent any

    environment {
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'blue-green',
                    url: 'https://github.com/nitishkumar105/jenkins_test.git'
            }
        }

        stage('Ensure Base Stack Running') {
            steps {
                sh '''
                docker compose up -d --build
                '''
            }
        }

        stage('Detect Active Color') {
            steps {
                script {
                    def active = sh(
                        script: '''
                        docker exec nginx \
                        sh -c "grep proxy_pass /etc/nginx/conf.d/default.conf || true"
                        ''',
                        returnStdout: true
                    ).trim()

                    if (active.contains("app-blue")) {
                        env.ACTIVE_COLOR = "blue"
                        env.IDLE_COLOR = "green"
                    } else {
                        env.ACTIVE_COLOR = "green"
                        env.IDLE_COLOR = "blue"
                    }

                    echo "ACTIVE = ${env.ACTIVE_COLOR}"
                    echo "IDLE   = ${env.IDLE_COLOR}"
                }
            }
        }

        stage('Deploy Idle Color') {
            steps {
                sh '''
                docker compose up -d --build app-${IDLE_COLOR}
                '''
            }
        }

        stage('Wait for Health') {
            steps {
                sh '''
                echo "Waiting for app-${IDLE_COLOR}..."
                sleep 15
                '''
            }
        }

        stage('Switch Traffic') {
            steps {
                sh '''
                cp nginx/${IDLE_COLOR}.conf nginx/default.conf
                docker exec nginx nginx -s reload
                '''
            }
        }

        stage('Stop Old Color') {
            steps {
                sh '''
                docker compose stop app-${ACTIVE_COLOR}
                '''
            }
        }
    }

    post {
        success {
            echo " Blue–Green deployment SUCCESS Nitish "
        }
        failure {
            echo " Deployment FAILED — rollback possible"
        }
    }
}
