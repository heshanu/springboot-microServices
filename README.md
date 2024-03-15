----------------------------------------------------for cloud gateway----------------------------------------

mvn clean install  to create jar file 
create docker images

change localhost t0 in properties.yml-> host.docker.internal
httpL${CONFIG_SERVER:localhost}:9296

mvn clean install and create jar file again

build image 
build container
docker run -dit -p 9090:9090 -e CONFIG_SERVER_URL=host.docker.internal  -e EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka --name cloudgtway addff23232

-e envirnment variable


-------------------------------------------------
