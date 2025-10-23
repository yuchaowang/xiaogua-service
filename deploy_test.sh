#!/bin/bash
ls_date=`date +%Y%m%d`
jar="xiaogua-comments-service"
host="root-tencent"
servicename="xiaoguacommentsservice"
mvn clean install -e -U -Ptest
rc=$?
if [ ${rc} -ne '0' ]
then
    echo "install err!"
else
scp target/${jar}.jar ${host}:~/
ssh -tt ${host} << startbash
    sudo cp /data/${jar}/${jar}.jar /data/${jar}/jars/${jar}.jar${ls_date}
    sudo mv ~/${jar}.jar /data/${jar}/${jar}.jar
    sudo systemctl restart ${servicename}
    exit
startbash
    echo "success!"
fi