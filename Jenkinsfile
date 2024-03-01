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
        stage('Deploy Prepare'){
            steps{
                sh 'kill $(pgrep -f backend-0.0.1-SNAPSHOT)'
            }
        }
        stage('Deploy') {
            steps {
                sh 'nohup java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar &'
            }
        }
    }
}