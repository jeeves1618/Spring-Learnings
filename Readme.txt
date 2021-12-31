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

Project Structure of WebApp is available here: https://maven.apache.org/plugins/maven-war-plugin/usage.html
web.xml is the deployment descriptor for servlet based java web applications. 

Hibernate is a framework to persist Java objects. Hibernate provides Object to Relational mapping or ORM. Hibernate is a wrapper around JDBC. 	

1. Section 12 ending has details on WAR file deployment 
2. Refer video 122 for Controller level request mapping

To Do
1. For each and <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
2. org.springframework.context.support.ResourceBundleMessageSource - ResourceBundle [resources/messages] not found issue should be looked at. 
3. Section 17 - Creating Custom Validation Rules 
4. Find out how to pass literal values during constructor injection using component scan.