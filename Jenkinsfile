pipeline {
     environment{
        dockerimagefrontend=""
        dockerimagebackend=""
    }
    agent any
    stages {
        stage('Git clone') {
            steps {
            git branch: 'master',url: 'https://github.com/sukrutdekhane/SPE_Major_Backend.git'
            }
        }
       stage('Maven Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Docker Build Image backend') {
            steps {
                script{
                    dockerimagebackend=docker.build "sukrutdekhane/spe_major_project_backend:latest"
                }
            }
        }
        stage('Docker Build Image frontend') {
                    steps {
                            sh 'docker build -t sukrutdekhane/spe_major_project_frontend:latest ./spe_major_project_frontend-main'
                    }
                }
        stage('Push Docker Image backend') {
            steps {
                script{
                    docker.withRegistry('','docker-hub'){
                    dockerimagebackend.push()
                    dockerimagefrontend.push()
                    }
                }
            }
        }
         stage('Push Docker Image frontend') {
                     steps {
                        sh 'docker push sukrutdekhane/spe_major_project_frontend:latest'
                         }
                    }
                }
         stage('Ansible copy docker-compose file in client user1') {
            steps {
              ansiblePlaybook becomeUser: null, colorized: true, disableHostKeyChecking: true,
              inventory: 'inventory', playbook: 'playbook.yml', sudoUser: null
            }
        }
    }
}