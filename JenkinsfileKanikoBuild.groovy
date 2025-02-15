pipeline {
      agent {
        kubernetes {
          //label 'qa-automation-runner'
          defaultContainer 'kaniko'
          yamlFile 'kaniko-operations-local.yaml'
        }
      }
  stages {
    stage('Build') {
    steps {
          script {
            sh "echo kaniko build is running"
            sh "ls -la"           
        } //container
      } //steps
    } //stage(build)
  }
}
