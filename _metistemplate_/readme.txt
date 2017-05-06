*************
Instructions
*************

At root folder, you can run these commands 

### Build all modules and install .war file to local repository ###
  mvn clean install
  
### Start Tomcat8 and deploy package from local repository ###
  mvn -pl <project_name>-app cargo:run
  
### Replace web files ###
  mvn -pl <project_name>-app -Presources process-resources
  
