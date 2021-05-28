pipeline {
  agent none
  stages {
    //Build container image
    stage('Build') {
      agent {
        kubernetes {
          label 'jenkinsrun'
          defaultContainer 'builder'
          yaml """
    kind: Pod
    metadata:
      name: kaniko
    spec:
      containers:
      - name: builder
        image: gcr.io/kaniko-project/executor:debug
        imagePullPolicy: Always
        command:
        - /busybox/cat
        tty: true
        volumeMounts:
          - name: kaniko-secret
            mountPath: /kaniko/.docker
      volumes:
        - name: kaniko-secret
          secret: regcred
          items:
            - key: .dockerconfigjson
              path: config.json

    """
        }
      }
    steps {
          script {
            sh "/kaniko/executor --dockerfile `pwd`/Dockerfile --context `pwd` --destination=robertrevolver/kaniko-test:0.0.1"
        } //container
      } //steps
    } //stage(build)
    //Test goes here
    //SonarQube goes here
    //Documentation generation goes here
    //Deploy goes here
    //Performance testing goes here
  } //stages
} //pipeline
