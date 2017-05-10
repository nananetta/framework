***************
 Instructions
***************

At root folder, you can run these commands 

### Build all modules and install .war file to local repository ###
  mvn clean install
  
### Start Tomcat8 and deploy package from local repository ###
  mvn -pl _metistemplate_-app cargo:run
  
### Replace web files ###
  mvn -pl _metistemplate_-app -Presources process-resources
  
### Run Test ###
  mvn test