pipeline{
    agent any
    tools {
        gradle 'gradle-7.5.1'
    }
    stages{
        stage('Build') {
            steps {
                sh 'gradle clean build -x test'
            }
        }
        stage('Deploy') {
            steps {
                sshagent(credentials: ['ec2-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ubuntu@54.174.9.65 uptime
                        scp /var/jenkins_home/workspace/neokdeuri_neokdeuri_back_main/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@54.174.9.65:/home/ubuntu/neokdeuri/back
                        ssh -t ubuntu@54.174.9.65 ./neokdeuri_back.sh
                    '''
                }
            }
        }
    }
}