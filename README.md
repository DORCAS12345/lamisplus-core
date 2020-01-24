## Build 
 
### Prerequisites 
 
#### Java 

##Database Initialization
 
LAMISPlus is a Java application which is why you need to install a Java JDK.  Uses spring boot as it framework.
 
If you want to build the master branch you will need a Java JDK of minimum version 8. 

#### Maven 
 
Install the build tool [Maven](https://maven.apache.org/). 
 
You need to ensure that Maven uses the Java JDK needed for the branch you want to build. 
 
To do so execute 
 ```bash mvn -version ``` 
 
which will tell you what version Maven is using. Refer to the [Maven docs](https://maven.apache.org/configure.html) if you need to configure Maven. 
 
#### Git 

Install the version control tool [git](https://git-scm.com/) and clone this repository with 
 ```bash git clone https://github.com/lamisplus/lamisplus-core.git ``` 
 
### Build Command 
 
After you have taken care of the [Prerequisites](#prerequisites) 

Execute the following 
 ```bash cd mvn clean install ``` 
 This will generate the LAMISPlus application in `target/lamisplus.war` which you will have to deploy into an application server like for example [tomcat](https://tomcat.apache.org/) or [jetty](http://www.eclipse.org/jetty/). 

##Database Initialization
When you launh your application for the first time. The applicattion will initialize your database using liqguid base.  Lamisplus uses postgreSQL database as most of the data are stores as JSONB and is well suppoeted by Postgres.
 
### Deploy 
 For development purposes you can simply deploy the ` lamisplus.war`   using any server of your choice (tomcat among others) 
 ```bash mvn spring-boot:run 
``` If all goes well (check the console output) you can access the LAMISplus application at `localhost:8080/`.
