pipeline {
    agent any

    tools {
        jdk "jdk17"                
    }

    environment {
        AWS_DEFAULT_REGION = 'us-east-1'
        S3_BUCKET = 'jenkinspipelines3bucket'
    }

    stages {
        stage('Check Java') {
            steps {
                sh 'java -version'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'sonar6.2'  
            }
            steps {
                withSonarQubeEnv('sonarserver') {
                    sh '''
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=Jenkins_pipeline \
                        -Dsonar.projectName="Test Project" \
                        -Dsonar.projectVersion=1.0 \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=.
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Prepare Test File') {
             steps {
                 sh '''
                     echo "This is a test file for S3 upload" > test.txt
                   '''
             }
        }

        stage('Upload to S3') {
            steps {
                sh '''
                    echo "Uploading test file to S3..."
                    aws s3 cp test.txt s3://${S3_BUCKET}/ --acl private
                '''
            }
        }
    }
}
