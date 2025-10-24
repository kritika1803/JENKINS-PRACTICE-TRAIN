pipeline {
    agent any

    environment {
        PATH = "/usr/local/bin:${env.PATH}" // ensure npm & node are visible
    }

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                // NEW (employeeapi)
               dir('trainapi-react') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
        stage('Deploy Frontend to Tomcat') {
            steps {
                sh '''
                TOMCAT_WEBAPPS="/Users/chilakakritikareddy/Desktop/SOFTWARE/apache-tomcat-10.1.43/webapps/reacttrainapi"
                rm -rf "$TOMCAT_WEBAPPS"
                mkdir -p "$TOMCAT_WEBAPPS"
                cp -R trainapi-react/dist/* "$TOMCAT_WEBAPPS"
                '''
            }
        }

        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                dir('TRAINAPI-SPRINGBOOT') {
                    sh '/Users/chilakakritikareddy/Desktop/SOFTWARE/apache-maven-3.9.11/bin/mvn clean package'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
            steps {
                sh '''
                TOMCAT_WEBAPPS="/Users/chilakakritikareddy/Desktop/SOFTWARE/apache-tomcat-10.1.43/webapps"
                rm -f "$TOMCAT_WEBAPPS/springboottrainapi.war"
                rm -rf "$TOMCAT_WEBAPPS/springboottrainapi"
                cp TRAINAPI-SPRINGBOOT/target/*.war "$TOMCAT_WEBAPPS/"
                '''
            }
        }

    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
    }
}