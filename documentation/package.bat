cd ..
del deliveries\football-manager.jar
call mvn clean package
copy target\football-manager-0.0.1-SNAPSHOT.jar deliveries\football-manager.jar