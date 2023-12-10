#!/bin/sh

# Setting versions
VERSION='1.0.1'
cd ..
./gradlew clean build -x test

ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'fintech docker image build...'
cd $ROOT_PATH && docker build -t fintech:$VERSION .
echo 'fintech docker image build... Done'



