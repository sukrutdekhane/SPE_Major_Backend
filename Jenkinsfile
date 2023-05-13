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
                             dir('./spe_major_project_frontend-main') {
                             /* execute commands in the scripts directory */
                              script{
                                 dockerimagefrontend=docker.build "sukrutdekhane/spe_major_project_frontend:latest"
                                 }
                           }
                      }
                }
        stage('Push Docker Image') {
            steps {
                script{
                    docker.withRegistry('','docker-hub'){
                    dockerimagebackend.push()
                    dockerimagefrontend.push()
                    }
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