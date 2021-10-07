pipeline { 
  agent any stages {
    stage('build') {
      steps { 
        sh './gradlew clean build'
      }
    } 
    
    stage('upload') { 
      steps { 
        sh 'aws s3 cp build/libs/application.war s3://jenkins-deploy-application/application.war --region ap-northeast-2' 
      }
    } 
    
    stage('deploy') {
      steps {
        sh 'aws elasticbeanstalk create-application-version --region ap-northeast-2 --application-name SpringbootThymeleaf --version-label ${BUILD_TAG}--source-bundle S3Bucket="cutlinepage",S3Key="CutLinePage.war"' 
        sh 'aws elasticbeanstalk update-environment --region ap-northeast-2 --environment-name springbootthymeleaf-sample --version-label ${BUILD_TAG}' 
      } 
    }
    
  } 
}

