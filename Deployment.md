# Deployment to AWS EC2 instance.
- Create an executable jar file in the qualwalk-monolith-web project. Use the command `mvn clean install spring-boot:repackage`.
- Copy the executable jar from local to EC2 instance using winscp.
- Move the jar into an organized folder. Once done use the `nohup java -jar <jar-name>` command to run the jar on the EC2 instance.
- Use `fuser <port_number>/tcp` to identify the process running on the port number. 
- Use `lsof -i:<port_number>` to kill the process running on the port

# Useful commands
- `nohup java -jar backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev` to activate profile dev
- `psql --host=<host> --port=<port> --username=qualwalk --password --dbname=<dbname>` to access psql in command line.
- `nc -zv <db host> <port>` to check connectivity to RDS instance
- `kill $(lsof -t -i:<port>)` force kill a port
- `keycloak-16.1.0]$ ./bin/standalone.sh -b=0.0.0.0 &` starting Keycloak standalone mode
- `sudo nginx -s reload` reload nginx settings
- `sudo nginx -t` to test the `nginx.conf` for errors.
- `sudo systemctl status nginx` check status of nginx
- `sudo systemctl restart nginx` restart nginx
- `sudo su` to start terminal as elevated user.

# Useful Docker commands
- `docker ps` we can see all the running containers. Additional options are available.
- `docker pull <image name>:<version name>` to pull a docker image onto the local.
- `docker run <image name>` starts the image in a container in attached mode. Can be run with multiple options.
- `docker stop <running container id>` stops a running container.
- `docker start <running container id>` starts a running container.
- `docker run -p <host port>:<container port> <image name>` Binding host port to container port.
- `docker logs <container id>` displays logs of a container.
- `docker exec -it <container id>` gets terminal of the container.
- `docker-compose -f <file name> up` starts all the containers specified in the file.
- `docker-compose -f <file name> down` shuts down all containers in the specified data.
- `docker build -t <image name>:<tag name> .` to build image with tag <tag name>
- `docker-compose up --build --force-recreate service-name-here` Builds service while running
- `docker run -v <host directory> : <container directory>` to create Host volumes using docker run command.
- `docker run -v <container directory>` to create anonymous volumes.
- `docker run -v name:<container directory>` Create named volumes. best to use in production.
- 