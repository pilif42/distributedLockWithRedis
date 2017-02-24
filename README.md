##################################################
# To build
##################################################
mvn clean install


##################################################
# To run
##################################################
- Prerequisites:
    - Install Redis. See config used in application.yml under data-grid.

java -jar target/distributedLockWithRedis-1.0.0-SNAPSHOT.jar


##################################################
# To test
##################################################
curl http://localhost:8131/totest -v -X GET
200
