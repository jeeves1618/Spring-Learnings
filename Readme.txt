Installed Apache Tomcat 9.0 for Spring
The default port is 8085 for Tomcat 9.0

Primary Functions of a Spring Container
1. Create and Manage Objects (Inversion of Control)
2. Inject Object's Dependencies (Dependency Injection)

Annotations
@Component will register the class and make the bean available for Spring Framework
@Qualifier, when there are multiple implementation or child classes, use this annotation to specify which implementation should be used for bean creation
@Autowired is used for injecting @Component classes. If you haven't annotated with @Component, that bean will not be visible for @Autowired
@Value is used to assign a value to a variable and will work only within classes that are annotated with @Component. Example @Value("${source.of.advice}") String sourceOfAdvice;
@Scope annotation defines the scope of the bean. By default it is singleton. 
@PostConstruct - Any method with this annotation gets invoked right after that constructor of that bean.
@PreDestroy - A method annotated with @PreDestroy runs only once, just before Spring removes our bean from the application context.

The above two annotations are equivalent to init-method  and destroy-method methods through XML configuration. 
@Bean - The @Bean annotation tells Spring that we are creating a bean component manually. Here is a real-time use case of using @Bean: You can use @Bean to make an existing non spring (i.e it doesn't have @Component annotation) third-party class available to your Spring framework application context.
@Transactional - Removes the need for boilerplate code like getTransaction() in Hibernate application.
@Repository will have the class automatically registered as DAO.
@RequestMapping will map the path to the method. If you want to add a HTTP method constraint to it, you have to write it like this -
@RequestMapping(path="/processForm", method=requestMethod.GET). Instead, you can go for the @Getmapping annotations.
@Getmapping is the combination of @RequestMapping and method=requestMethod.GET, so on and so forth for POST, PUT etc.

Project Structure of WebApp is available here: https://maven.apache.org/plugins/maven-war-plugin/usage.html
web.xml is the deployment descriptor for servlet based java web applications. 

Hibernate is a framework to persist Java objects. Hibernate provides Object to Relational mapping or ORM. Hibernate is a wrapper around JDBC. 	

1. Section 12 ending has details on WAR file deployment 
2. Refer video 122 for Controller level request mapping

For more details on mapping between entities, https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3 and https://dzone.com/articles/hibernate-mapping

HTTP Methods
GET    - Read a list of entities or entity
PUT    - Update and existing entity
POST   - Create a new entity
DELETE - Delete an existing entity

A HTTP request will have request line, header variables and message body (or payload)
A HTTP response will have response line, header variables and message body (or payload)

Response line will have status codes, Code ranges are given below.
100 - 199 Informational
200 - 299 Successful
300 - 399 Re-direction
400 - 499 Client Error
500 - 599 Server Error

The message format of the payload is described by the MIME type. MIME stands for Multipurpose Internet Mail Extension. MIME has the syntax of Type/SubType. Eg. text/html 
application/json, application/xml. 

https://www.jsontest.com/

https://jsonplaceholder.typicode.com/

Spring Boot Training Notes
-----------------------------------
com.github.javafaker for fake data.
This is helpful while using transient database like H2. When ever we start the application, we will have data loaded in the table.

http://localhost:8222/h2-console will be enabled through the dependency dev tools

@JsonIgnore to avoid recursion. @JsonIgnore will not deserialize 

Pagination and sorting repository instead of crud repository

show-sql in YAML decides whether the SQL should be shown in the run log

@Valid validation has to be added in order to the validation in the RestController itself instead of sending it all the way to repository

Springdoc openapi ui dependency for API documentation; http://localhost:8222/swagger-ui.html

@ApiResponses is only for enriching the documentation.

Read about actuator. It is used by kubernates to orchestrate the rest points. 

http://localhost:8222/actuator

http://localhost:8222/actuator/shutdown

Devtools will not restart the applicaiton automatically in IntelliJ. You have to go to settings (or preferences in mac)/Build, Execution and Deployment/Compiler and check on Build Projects Automatically. Then go to settings (or preferences in mac)/Advance Settings and check on Allow auto-make to start even if developed application is currently running.

Git Cheat Sheet
…or create a new repository on the command line

echo "# BasicsOfSpringBoot" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/jeeves1618/BasicsOfSpringBoot.git
git push -u origin main

…or push an existing repository from the command line

git remote add origin https://github.com/jeeves1618/BasicsOfSpringBoot.git
git branch -M main
git push -u origin main

Spring Boot App - Ground Up
----------------------------

1. Create a project using the following dependencies. Spring Web, Spring Data JPA, H2 Database, Lombok, starter validation, open api2. If there are dependency issues, 
		mkdir ~/.m2
		cp /opt/homebrew/Cellar/maven/3.9.4/libexec/conf/settings.xml ~/.m2
		If the plug in issues persist, File > Invalidate Caches / Restart to reload intelliJ
3. Start with the controller
4. Proceed with Entity
5. If the port is in use
	find the process id (PID) using the port by issuing the command: sudo lsof -i :8081
	Then kill the process id using: sudo kill -9 PID
6. @CreatedDate and @LastModifiedDate needs @EnableJpaAuditing in the spring boot application.
7. @CreatedBy and @LastModifiedBy needs AuditorAware Implementation
8. @RestController will not work if some methods are going to be exposed as REST and others as MVC. In those scenarios, use @Controller for class and @ResponseBody for the REST methods.
9. What libraries to use? Load Balancer - Spring Cloud Load Balancer, Spring Cloud Eureka for Service Discovery, Resilience4j + Adoptive Concurrency Limiter for Faul Tolerance
10.If @Entity is autowired, it will fail. Because, Classes annotated with @Entity are JPA entities - they usually represent rows in a database. These are not Spring-managed beans, so you cannot inject them. You normally also don't need to inject these classes.
11. For setting up a spring cloud server, add the dependency Config Server from Spring Cloud Config, Actuator. Check from here https://spring.io/projects/spring-cloud whether the right spring cloud version is being used.
	@EnableConfigServer should be added to the @SpringBootApplication
	If you point to a .git uri or a local folder, your spring cloud application server is up.
	Now, for the spring cloud client, add the config client dependency, spring cloud version and dependency management in the POM file