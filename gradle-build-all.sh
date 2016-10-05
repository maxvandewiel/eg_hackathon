#!/bin/sh
cd user-registration-service
rm -rf build
./gradlew build
cd ../pub
rm -rf build
./gradlew build
cd ../ppv-adapter
rm -rf build
./gradlew build
cd ..
exit 0
