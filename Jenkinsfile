def PROJECT_NAME = 'backend-0.0.1-SNAPSHOT'
pipeline{
    agent any
    tools {
        gradle 'gradle-7.5.1'
    }
    stages{
        stage('Prepare'){
            steps {
                sh 'gradle clean'
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean build -x test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'nohup java -jar ./build/libs/${PROJECT_NAME}.jar &'
            }
        }
    }
}