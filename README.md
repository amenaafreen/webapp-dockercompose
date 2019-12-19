# TASK
Take a web application with a database connected to it. Deploy the application using docker, with tomcat running in one container and database in another container. Push the artifacts to the Artifactory. Automate the whole process using Jenkins.
 
# Taking a working web application and push it on to GitHub.
LoginWebApp
git add .
git commit -m “initial Commit”
git push origin master
GitHub Link for Project
https://github.com/amenaafreen/webapp-dockercompose

# Built with Maven
pom.xml
https://github.com/amenaafreen/webapp-dockercompose/blob/master/pom.xml

# docker-compose
Docker Compose is used for running multi-container applications. As our application deployment required me to run tomcat and mysql in different containers, I have used docker-compose.
Writing docker-compose to deploy the application with one container running tomcat and another container running mysql database. The mysql container is linked to the tomcat container.
How to write a docker-compose file to link tomcat and mysql.
https://stackoverflow.com/questions/53701629/tomcat-mysql-war-using-docker-compose-yml

# docker-compose.yml
Write the docker-compose file in the project folder and push it it GitHub
https://github.com/amenaafreen/webapp-dockercompose/blob/master/docker-compose.yml

# Troubleshooting how to connect mysql container to tomcat container in Docker
## JDBC connection for mysql and tomcat containers
 
To make my tomcat server to be able to connect to my MySQL server both running in separate containers.
In the login.jsp file, replace localhost as mysqldb
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "mypassword");
**Connection con = DriverManager.getConnection("jdbc:mysql://mysqldb:3306/userdb", "root", "mypassword");
mysqldb is the service name that we define in the docker-compose file
https://stackoverflow.com/questions/35510964/unable-to-connect-mysql-container-to-tomcat-container-in-docker   
https://stackoverflow.com/questions/35450248/cant-connect-to-mysql-docker-container-with-jdbc
https://stackoverflow.com/questions/43322033/create-database-on-docker-compose-startup

# JENKINS
Create a seed Job s Maven Project named DockerDeployment
Provide the github link for the source code
In the build → provide the job DSL by selecting look on file system (web.groovy).
## Job DSL Script
https://github.com/amenaafreen/webapp-dockercompose/blob/master/web.groovy
First do a docker-compose down to stop all running containers
Running a Container in the detach mode (The container runs in the background)
 docker-compose up -d.
When I tried to do curl http://localhost:8903/LoginWebApp/ I was getting an empty reply from the server.
For it I had to provide a delay of 10 secs (sleep 10)
curl -Is http://localhost:8903/LoginWebApp/ 

## Writing a configure block for deploying artifacts to Jfrog Artifactory

https://stackoverflow.com/questions/38530619/running-shell-scripts-in-a-jenkins-dsl-mavenjob

## DSL script Approval
Jenkins→ Manage Jenkins → Configure Global Security → CSRF Protection → Uncheck Enable Script Security for Job DSl scripts

## GitHub WebHooks
https://webhookrelay.com/blog/2017/11/23/github-jenkins-guide/

# PUSHING THE ARTIFACTS TO JFROG ARTIFACTORY
https://medium.com/@anusha.sharma3010/integrating-jenkins-with-artifactory-6d18974d163d
Download Jfrog from https://bintray.com/jfrog/artifactory/jfrog-artifactory-oss-zip/4.15.0
Navigate to → downloads → artifactoryfolder → bin → ./artifactory.sh
In artifactory→ tomcat → conf → server.xml (Connector port 8081) 
Install Artifactory Plugin on jenkins.
If there’s anything running on port 8081 → You can kill the process
List the process first → sudo lsof -i :8081
Then kill it using kill -9 PID

## CONFIGURING ARTIFACTORY IN JENKINS

Jenkins → Manage Jenkins → Configure System → Artifactory → Provide the name and url (Also the default deployer (user) credentials of artifactory)

