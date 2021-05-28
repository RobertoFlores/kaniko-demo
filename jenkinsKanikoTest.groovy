pipeline {
      agent {
        kubernetes {
          //label 'qa-automation-runner'
          defaultContainer 'builder'
          yamlFile 'jenkinsKanikoTest.yaml'
        }
      }
  stages {
    stage('Build') {
    steps {
          script {
            sh "echo kaniko build is running"
            sh "ls -la"           
            sh "/kaniko/executor --dockerfile `pwd`/Dockerfile --context `pwd` --destination=robertrevolver/kaniko-test:0.0.1"
        } //container
      } //steps
    } //stage(build)
  }
}
