# RESTfulJSONDev3l
RESTEasy JSON RESTful web service project (template)
	- uses annotations without removing the need for a web.xml
	- org.jboss.resteasy : v3.0.11


#Usage Instructions to Pull down and use template project from GitHub
1. From command line retrieve project from GitHub
	- git clone https://github.com/DEV3L/RESTfulJSONDev3l.git

2. Run maven package from inside the created directory
	- mvn clean package

3. RESTfulJSONDev3l.war can be deployed to container of choice
	- http://services-dev3l.rhcloud.com/RESTfulJSONDev3l/resources/example
		- My personal OpenShift server I deploy projects too

4. Use Maven to resolve the dependencies and create eclipse dynamic web project and class/project files
	- mvn eclipse:eclipse -Dwtpversion=2.0

5. Import the project into Eclipse as an existing project

6. Access the endpoints via a browser URL
	- http://localhost:8080/RESTfulJSONDev3l/resources/example
	- http://localhost:8080/RESTfulJSONDev3l/resources/example/query
