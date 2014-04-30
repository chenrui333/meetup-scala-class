#!/bin/sh
java \
    -Dfile.encoding=UTF-8 \
    -Xms1G \
    -Xmx2G \
    -Xss1M \
    -XX:+CMSClassUnloadingEnabled \
    -XX:MaxPermSize=1G \
    -jar "lib/sbt-launch-0.13.2.jar" \
    "$@"
