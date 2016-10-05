#! /bin/bash -e

#rm -fr build
#mkdir build
cp ../build/libs/user-registration-service-0.1.0.jar build

docker build -t user-registration-service .
docker stop user-registration-service1
docker rm user-registration-service1
docker run --name user-registration-service1 --link max-rabbit:rabbit -p 8090:18090 -d maxvandewiel/user-registration-service 
exit 0
