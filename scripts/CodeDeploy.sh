#!/bin/bash

REPOSITORY=/home/ec2-user
PROJECTNAME=CutLinePages


echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/jenkins/ | grep 'Gallary' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar $REPOSITORY/jenkins/$JAR_NAME 2>&1 &
