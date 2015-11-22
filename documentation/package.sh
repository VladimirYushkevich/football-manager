#!/bin/sh

cd ..
rm deliveries/football-manager.jar
mvn clean package
cp target/football-manager-0.0.1-SNAPSHOT.jar deliveries/football-manager.jar
