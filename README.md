# RESTfulJSONDev3l
Jersey JSON RESTful web service project (template)
	- uses annotations without removing the need for a web.xml
	- com.sun.jersey : v1.19



#Usage Instructions to Pull down and use template project from GitHub
1. From command line retrieve project from GitHub
	- git clone https://github.com/DEV3L/RESTfulJSONDev3l.git

2. Run maven package from inside the created directory
	- mvn clean package

3. RESTfulJSONDev3l.war can be deployed to container of choice
	- http://dev3l-charters.rhcloud.com/RESTfulJSONDev3l/resources/example
		- My personal OpenShift server I deploy projects too

4. Use Maven to resolve the dependencies and create eclipse dynamic web project and class/project files
	- mvn eclipse:eclipse -Dwtpversion=2.0

5. Import the project into Eclipse as an existing project



#Project Creation Steps :
** Assumes Maven already installed

1. Create new Dynamic Web Project using maven from the console
	- mvn archetype:create -DgroupId=RESTfulJSONDev3l -DartifactId=RESTfulJSONDev3l -DarchetypeArtifactId=maven-archetype-webapp

2. Copy the created project to an eclipse workspace
	- /Workspaces/Eclipse/Sandbox/

3. Update the pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>RESTfulJSONDev3l</groupId>
  <artifactId>RESTfulJSONDev3l</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>RESTfulJSONDev3lApp</name>
  <url>http://maven.apache.org</url>
  <build>
    <finalName>RESTfulJSONDev3l</finalName>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.7</source>
          <target>1.7</target>
        </configuration>
      </plugin>
      <plugin>
        <!-- Java EE 6 doesn't require web.xml, Maven needs to catch up! -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.4</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <dependencies>
    <!-- Jersey REST Dependencies -->
    <dependency>
      <groupId>asm</groupId>
      <artifactId>asm</artifactId>
      <version>3.3.1</version>
    </dependency>
    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20140107</version>
    </dependency>
    <dependency>
      <groupId>com.sun.jersey</groupId>
      <artifactId>jersey-bundle</artifactId>
      <version>1.19</version>
    </dependency>
    <dependency>
      <groupId>com.sun.jersey</groupId>
      <artifactId>jersey-server</artifactId>
      <version>1.19</version>
    </dependency>
    <dependency>
      <groupId>com.sun.jersey</groupId>
      <artifactId>jersey-core</artifactId>
      <version>1.19</version>
    </dependency>
  </dependencies>
</project>
```

4. Use Maven to resolve the dependencies and create eclipse project files
	- From the console within the project
		- mvn eclipse:eclipse -Dwtpversion=2.0

5. Import the project into Eclipse as an existing project

6. Delete index.jsp located at src/main/webapp

7. Delete web.xml located at src/main/webapp/WEB-INF

8. Create a src/main/java source folder
	- Could use the existing src/main/resource that comes with the archetype, but this is not "best practice" maven
	
9. Create a Bean/POJO with an @XmlRootElement class attribute
	- The @XmlRootElement tells our application how this can be serialized
```java
package com.dev3l.jersey.bean;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DataBean {
	private String data;
	public DataBean() {
		this(null);
	}
	public DataBean(String data) {
		this.data = data;
	}
	public final String getData() {
		return data;
	}
	public final void setData(String data) {
		this.data = data;
	}
}
```

10. Create an ApplicationConfig with an @ApplicationPath attribute
	- the @ApplicationPath is the root path of the web service calls
```java
package com.dev3l.jersey.config;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("resources") // set the path to REST web services
public class ApplicationConfig extends Application {}
```

10. Create an Example resource with @PATH, @GET and @Produces(MediaType.APPLICATION_JSON)
```java
package com.dev3l.jersey.resource;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.dev3l.jersey.bean.DataBean;
@Path("example")
public class ExampleResource {
	private static List<DataBean> dataBeans = new ArrayList<DataBean>();
	static {
		dataBeans.add(new DataBean("first"));
		dataBeans.add(new DataBean("second"));
		dataBeans.add(new DataBean("third"));
		dataBeans.add(new DataBean("fourth"));
	}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataBean getDataBean() {
    	return new DataBean("test_data_bean");
    }	
    @GET
    @Path("query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query() {
    	GenericEntity<List<DataBean>> entity = new GenericEntity<List<DataBean>>( dataBeans ){};
    	return Response.ok().entity(entity).build();
    }
}
```

11. Add the project to a java web container
	- Default Tomcat 7.0

12. Start the server

13. Access the endpoints via a browser URL
	- http://localhost:8080/RESTfulJSONDev3l/resources/example
	- http://localhost:8080/RESTfulJSONDev3l/resources/example/query
