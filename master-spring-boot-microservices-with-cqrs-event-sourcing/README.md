


create a swarm on addr:
```
docker swarm init --advertise-addr 192.168.0.1
```
create docker network:
```
docker network create --attachable -d overlay springbankNet
```
run axon in docker:
```
docker run -d --name axon-server \
-p 8024:8024 -p 8124:8124 \
--network springbankNet \
--restart always axoniq/axonserver:latest
```
run mongo db:
```
docker run -it -d --name mongo-container \
-p 27017:27017 --network springbankNet \
--restart always \
-v mongodb_data_container:/data/db \
mongo:latest 
```
run mysql:
```
docker run -it -d --name mysql-container \
-p 3306:3306 --network springbankNet \
-e MYSQL_ROOT_PASSWORD=springbankRootPsw \
--restart always \
-v mysql_data_container:/var/lib/mysql  \
mysql:latest
```
client tools in docker - adminer:
```
docker run -it -d --name adminer \
-p 8080:8080 --network springbankNet \
 -e ADMINER_DEFAULT_SERVER=mysql-container \
--restart always adminer:latest
```
