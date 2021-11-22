#!/bin/bash

REPOSITORY=/home/ec2-user
PROJECTNAME=CutLinePages

cd $REPOSITORY/$PROJECTNAME/
#echo "> Git Pull"
#
#git stash
#
#git pull origin master
#
#echo "> 프로젝트 Build 시작"
#
#chmod +x ./gradlew
#
#./gradlew build

echo "> Build 파일 복사"

cp ./build/libs/*.jar $REPOSITORY/jenkins/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f Gallary)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

#echo "> 새 어플리케이션 배포"

#JAR_NAME=$(ls -tr $REPOSITORY/jenkins/ | grep "*.jar" | tail -n 1)
#
#chmod +x ./$JAR_NAME
#
#echo "> JAR Name: $JAR_NAME" $REPOSITORY/jenkins/nohup.out
#nohup java -jar $JAR_NAME >> $REPOSITORY/jenkins/nohup.out 2>&1 &