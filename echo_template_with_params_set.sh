#!/bin/sh

#Set variables
set -e

if [ -z "$DOCKER_MACHINE_IP" ] ; then
  if which docker-machine >/dev/null; then
    export DOCKER_MACHINE_IP=$(docker-machine ip default)
  else
    export DOCKER_MACHINE_IP=localhost
 fi
 echo set DOCKER_MACHINE_IP ${DOCKER_MACHINE_IP}
fi

#Read in template one line at the time, and replace variables (more
#natural (and efficient) way, thanks to Jonathan Leffler).
while read line
do
    eval echo "$line"
done < "./docker-compose-template.yml"
