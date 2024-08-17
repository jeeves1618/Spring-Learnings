Installed Apache Tomcat 9.0 for Spring
The default port is 8085 for Tomcat 9.0
Primary Functions of a Spring Container
1. Create and Manage Objects (Inversion of Control)
2. Inject Object's Dependencies (Dependency Injection)

Spring
------
1. A bean is POJO managed by Spring IOC container.
2. A bean can be created during start up in the @Configuration class inside the methods returning the object and annotated with @Bean. When you use @Bean, the method name will become the bean name
3. Wiring can be done manually by a method call or passing the dependency bean as method parameter.
4. @Autowired can be done on class variable, constructor and setter. And it can be used whether the bean is created through @Bean or Streotype annotations like @Component
5. If you use @Autowired with constructor, you can define the dependency as final.

Annotations
@Component will register the class and make the bean available for Spring Framework
@Qualifier, when there are multiple implementation or child classes, use this annotation to specify which implementation should be used for bean creation
@Autowired is used for injecting @Component classes. If you haven't annotated with @Component, that bean will not be visible for @Autowired
@Value is used to assign a value to a variable and will work only within classes that are annotated with @Component. Example @Value("${source.of.advice}") String sourceOfAdvice;
@Scope annotation defines the scope of the bean. By default it is singleton. 
@PostConstruct - Any method with this annotation gets invoked right after that constructor of that bean.
@PreDestroy - A method annotated with @PreDestroy runs only once, just before Spring removes our bean from the application context.

The above two annotations are equivalent to init-method  and destroy-method methods through XML configuration. 
@Bean - The @Bean annotation tells Spring that we are creating a bean component manually. Here is a real-time use case of using @Bean: You can use @Bean to make an e 
xisting non spring (i.e it doesn't have @Component annotation) third-party class available to your Spring framework application context.
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

Spring Vs Spring Boot.

Instead of Autowiring, we used applicationcontext.getbean
All the DB operations happened through sessioncontext


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

Dockerizing
-----------
1. Ensure that the packaging is set to <packaging>jar</packaging> in pom.xml
2. mvn clean install in the directory where pom.xml is stored. This will create a JAR in the name of artifact+version inside target directory. 
3. mvn spring-boot:run will bring up the application. <artifactId>spring-boot-maven-plugin</artifactId> plugin within pom.xml will ensure that the right jar is executed.
4. But, running using the maven will require mvnw to be included in the docker. So, we should use java -jar target/jarname.jar
5. Create the Dockerfile in the same folder as pom.xml
6. docker build . -t microservices/config:v1 from the same folder will create the docker impage. The "." after build implies that the Dockerfile is in the same location. -t is the tag to be provided. microservices is the docker user name.
7. Dockerdesktop should be up and running while you are trying this.
8. docker images from terminal will list the images. docker run can be used to create any number of instances.
9. By default, all docker instances will run in a private container that is not accessible. So do port mapping using -p 8081:8082 . 8081 is the port which we are asking docker to expose. 8082 is the port in which the application will run. This is the port provided in the server: port: property in the yaml file.
10.If we just say docker run -p 8071:8071 username/dockerreponame:tag, the terminal will be blocked. So, in order to run it in detatched mode, provide -d after run.
11.For docker compose, create the docker-compose.yml file and add environment variables to the dependent microservices
12.For liveness and health readiness probe, add actuator dependency and change the yml of the dependency server
13. REMEMBER: Running by clicking the play button in docker desktop will not work because the port mapping is not done.


Liveness & Readiness
1. http://localhost:8071/actuator/health will give the application status
2. http://localhost:8071/actuator/health/readiness will give the readiness status
3. http://localhost:8071/actuator/health/liveness will give the liveness status


Service Discovery
1. Netflix's tech stack - https://netflixtechblog.com/netflix-oss-and-spring-boot-coming-full-circle-4855947713a0
2. From spring initializer download a project with Eureka Server, Spring Cloud Config client and Spring Boot Actuator
3. Annotate the main class of the eurake server @EnableEurekaServer
4. http://localhost:8070/eureka/apps

Why do we need database in the first place?

    Managing large data: Databases efficiently handle large amounts of data.
    Retrieving accurate data (data consistency): Databases ensure data accuracy through constraints.
    Easy updation: Data can be easily updated using Data Manipulation Language (DML).
    Security: Databases provide data security, allowing only authorized access.
    Data integrity: Ensured through various constraints.
    Availability: Databases can be replicated across servers for high availability.
    Scalability: They support load distribution by partitioning data across multiple nodes.
    Efficiency in data retrieval: Designed for quick and efficient data access.
    Data recovery and backup: Offer mechanisms to protect against data loss.
	
	DNS can return an IP or it give another URI - Called DNS Redirect. Content providers can use DNS redirect to send a client to a specific CDN. 

In synchronous replication, the primary host is responsible for replicating the message in all the relevant queues on other hosts. After acknowledgment from secondary hosts, the primary host then notifies the client regarding the reception of messages. In this approach, messages remain consistent in all queues replicas; however, it costs extra delay in communication and causes partial to no availability while an election is in progress to promote a secondary as a primary.

In asynchronous replication, once the primary host receives the messages, it acknowledges the client, and in the next step, it starts replicating the message in other hosts. This approach comes with other problems such as replication lag and consistency issues.

Replication is relatively simple if the replicated data doesn’t require frequent changes. The main problem in replication arises when we have to maintain changes in the replicated data over time.
Here are the various mechanisms of data replication:
    Single leader or primary-secondary replication: In primary-secondary replication, data is replicated across multiple nodes. Ideal for read heavy applicatiions, not so for write heavy applications. If the primary fails, one of the secondarys can take over. Replication via this approach comes with inconsistency if we use asynchronous replication. 
    Multi-leader replication. Multiple writes can result in inconsistent data. We use quorum to arrive at a consensus. A quorum can be a strict quorum or a sloppy quorum. 
    Peer-to-peer or leaderless replication

Shradding is different from replication in the way that it doesn't have the copy of complete data. Data partitioning (or sharding) enables us to use multiple nodes where each node manages some part of the whole data.The partitioning must be balanced so that each partition receives about the same amount of data. If partitioning is unbalanced, the majority of queries will fall into a few partitions. Partitions that are heavily loaded will create a system bottleneck. The efficacy of partitioning will be harmed because a significant portion of data retrieval queries will be sent to the nodes that carry the highly congested partitions. Such partitions are known as hotspots. 

Shradding can happen within a single node or (usually) multiple nodes. It can be vertical shradding, where a table with columns A, B, C and D are split into two shradds; one with columns A and B and another with columns C and D. Or it can be horizontal shradding, where the different set of rows with different partition keys are split across different shradds.
There are two strategies available in horizontal shradding:

    Key-range based sharding - Based on partition key. Range based queries are possible. Some partitions tend to become hot spots.
    Hash based sharding - Based on mod of hash value.  Range based queries are not possible. Partitions tend to grow equally. The problem is when we change the mod from 5 to 6 for an additional node, the impacted records have to be moved to the mew partition and it will be expensive.
	
	We need to do rebalancing when some partitions grows out of size. 
	
	Consistent hashing is a technique used in computer systems to distribute keys (e.g., cache keys) uniformly across a cluster of nodes (e.g., cache servers). The goal is to minimize the number of keys that need to be moved when nodes are added or removed from the cluster, thus reducing the impact of these changes on the overall system.

If a partition is very small, it will result in too much overhead because we may have to make a large number of small-sized partitions, each costing us some overhead. If the partition is very large, rebalancing the nodes and recovering from node failures will be expensive. It’s very important to choose the right number of partitions.
We can shart with Fixed Partitions (down side is zeroing in on the optimal number of partitions),Elasti search and Riak are using this, dynamic partitioning (HBase and MongoDB, downside is it is difficult to apply dynamic rebalancing while serving the reads and writes.) or Partition proportionally to nodes (Cassandra, Ketama. In this approach, the number of partitions is proportionate to the number of nodes, which means every node has fixed partitions. In earlier approaches, the number of partitions was dependent on the size of the dataset. That isn’t the case here.)

Key Value Stores

Key-value stores and traditional relational databases differ primarily in their data model and access patterns. Key-value stores are optimized for fast retrieval by using a simple key, do not support complex query languages, and often prioritize availability and scalability over strict consistency. They handle unstructured data, making them flexible for various data types. These stores are particularly advantageous in scenarios requiring rapid data access, massive volumes of data, and situations where the data structure is flexible or evolving.

Why do we need to run key-value stores on multiple servers?
Hide Answer

A single-node-based hash table can fall short due to one or more of the following reasons:

    No matter how big a server we get, this server can’t meet data storage and query requirements.

    Failure of this one mega-server will result in service downtime for everyone.

So, key-value stores should use many servers to store and retrieve data.

In distributed key value store, the keys and values are held in the cache of local nodes. So, when we add or remove the node the data should also be moved. Consistent hashing is an effective way to manage the load over the set of nodes. Primary secondary replication will not work when we want the write availability to be high. Peer to peer replication should be done. But, it is very expensive to do that across all nodes. So, we stick to 3 or 5 node replication. We can use the consistent hashing to replicate across the nodes as well. While replicating, remember synchronous replication reduces availability. If there is inconsistency between the nodes, we can maintain causality (that is recent updates get priority). But there is no guarantee that clocks across nodes are in sync. So we use vector clock. This process of resolving conflicts is comparable to how it’s done in Git. If Git is able to merge multiple versions into one, merging is performed automatically. It’s up to the client (the developer) to resolve conflicts manually if automatic conflict resolution is not possible. Along the same lines, our system can try automatic conflict resolution and, if not possible, ask the application to provide a final resolved value.

While consistent hashing is a good choice, it may result in unequal distribution of data, and certain servers may get overloaded. We add variable number of virtual servers inside those servers depending on the machine’s capability.

Note: Physical clocks are not reliable. So we go for logical clocks (lamport or vector) when we need causality. Lamport clocks don’t allow us to infer causality at the global level.Vector clock does. A 3 node cluster will have vector clock key as E1(1,0,0). So when there are too many nodes, it will become a problem. Google’s TrueTime API in Spanner considers the uncertainity of time and provides a range instead of a time. Drawbacks of spanner: if the ranges of two events overlap, then we don't know which one happened first. Also spanner is expensive. 

r + w > n is an important constraint. n is the total number of nodes in which the replication occurs. w is the number of nodes in which we are going write synchronously. r is the number of nodes from which we are going read. If we write synchronously into 2 nodes, then we have to read from 2 nodes to ensure that the data is consistent. 

Merkle tree is used to validate the consistency across the nodes. Each branch of the Merkle tree can be verified independently without the need to download the complete tree or the entire dataset. The Merkle tree is a mechanism to implement anti-entropy, which means to keep all the data consistent. It reduces data transmission for synchronization and the number of discs accessed during the anti-entropy process. In merkel tree every node will have the hash value of the root node. If the hash values of the root node from two nodes are the same, then the nodes are consistent. The disadvantage is that when a node joins or departs the system, the tree’s hashes are recalculated because multiple key ranges are affected.

Cache:
Cache should have cache client in the application server. Cache client will talk to cache servers through a configuration service that ensures that all the clients see an updated and consistent view of the cache servers. The cache server can have shards (partition) and each shard can be replicated for HA. Within the Cache server the values will be stored in a linked list and the pointers to the linked list will be stored as values in a hash table. The cache server will cater to insert(), retrieve() APIs from client and delete() api from the DB when something gets deleted.

We can usually go for synchrounous replication of nodes when they reside within the same DC or the latency to replicate is very low.

Distributed Messaging Queue.
The DMQ has a client to accept requests from producers and consumers. After the client comes the load balancer. The LBs route the message to the FE servers where the validations, de-duplication and authentication happen. The FE deals with Metadata service and request dispatching to back end data store. The meta data services persists the data as well as have a cache.

In Kafka, a consumer reads the message, sets the visibility to No and turns on the visibility timer. Once it processes the message, it comes back to the queue and deletes the message. What happens when the visibility timeout of a specific message expires and the consumer is still busy processing the message? The message becomes visible, and another worker can receive the message, thereby duplicating the processing. To avoid such a situation, we ensure that the application sets a safe threshold for visibility timeout.

Difference between queue and a topic (or message queue and a pub sub system). Queue is point to point. One producer to one consumer. So, in message queue, we will have only one distributed message queue. A pub sub is one to many. 

How do you manage concurrency while putting into or getting out of a queue?
1. We can use a locking mechanism. When a process or thread requests a message, it should acquire a lock for placing or consuming messages from the queue.  It’s neither scalable nor performant.
2. Another solution is to serialize the requests using the system’s buffer at both ends of the queue so that the incoming messages are placed in an order, and consumer processes also receive messages in their arrival sequence. By serializing requests, we mean that the requests (either for putting data or extracting data), which come to the server would be queued by the OS, and a single application thread will put them in the queue

Bad Design #1
So, producer will post it into a distributed message queue and the messages will be replicated in to as many distributed message queue as the number of subscribers. But, if we have millions of subscribers for thousands of topics, defining and maintaining millions of queues is expensive.

Mediocre Design #2
In messaging queues, the message disappears after the reader consumes it. So, what if we add a counter for each message? The counter value decrements as a subscriber consumes the message. It does not delete the message until the counter becomes zero. Now, we don’t need to keep a separate queue for each reader. Okay, but can we gurantee that all subscribers will consumer the message all the time? If not, those message will remain in the queue for ever.


Kafka will have topic -> partition -> segments will have messages with offsets. The messages are often stored in the local storage of the brokers or BE clusters.


Points to Rememeber:
We replicate when we want to avoid SPOF. We shard when the data size exceeds one server. When we shrad data across servers, the application layer can maintain the partition/shrad info and route accordingly or the shrad info can be stored in each individual data servers that can route among themselves.
Semaphore is an operating system-level memory management technique that can be used to manage the allocation and use of memory resources. In a nutshell, it allows multiple threads or processes to share access to a limited number of resources, such as memory blocks or locks

https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages/listener-annotation.html

Rate Limiter:
A rate limiter is generally used as a defensive layer for services to avoid their excessive usage, whether intended or unintended. 
Hard throttling - stops. Soft throtlling - sends message up to certain variance. 
For better performance, a rate limiter should check the count and allow/disallow the client first then go an update the counter. But, will this stop DoS attack?

Algorithms for rate limiting

    Token bucket
    Leaking bucket
    Fixed window counter
    Sliding window log
    Sliding window counter

A Rate Limiter is a pattern put in place for a service to protect itself from too many calls. A resource-intensive service is always in danger of overloading if triggered multiple times, or it may end up calling other resource-intensive services, bringing the whole system down. If such a service is exposed directly to clients, it is susceptible to DDOS attacks as well. Another use case is that we may want to limit the user to call a request depending on the Pricing Plan they are subscribed to, e.g. a user can be restricted to call a service only ‘n’ times a day depending upon what plan they’ve chosen. All further calls are rejected.

A Circuit Breaker is a pattern put in place for a service to protect itself from calling too many unresponsive services. If a service is unresponsive, it makes sense to not overload it further by retrying it. If a service I am attempting to call repeatedly fails, the circuit ‘breaks’ and I return a default response for some time. After a wait duration only I attempt to call the failing service.

Blob Storage:
When we design a blob we consider the following.
1. Number of servers - Every server can handle predeterminate amount of requests per second. Total number of requests expected/RPS of server will give the number of servers needed.
2. What additional storage will be needed every day?
3. Estimate the bandwidth for incoming traffic. Totalbandwidth (GB/s) ​=Totalstorage_day​ (GB)​/24×60×60
4. Estimate the bandwidth for outgoing traffic. Totalbandwidth (GB/s) ​=Totalstorage_day​ (GB)​/24×60×60

Sharded Counters:
heavy hitters problem
Strict Consistency Model vs Relaxed Consistency Model

A stack can be implemented in array or linked list. Because both the push and pop are only from one end. Queues are implemented only in linked list. Because the pop happens at another end and it is expensive to move things around. 

We have three strict rules our tree must adhere to in order to be classified as a binary tree:

    Each node can have only zero, one, or two children.

    The tree can have only a single root node.

    There can be only one path to a node from the root.

If we add two more 

Spark cluster

Race Condition - A race condition is an undesirable situation that occurs when a device or system attempts to perform two or more operations at the same time

An idempotent task produces the same result, no matter how many times we execute it. 
 
 Tree - Just a hierarchial structure of nodes connected with edges. Can have any number of nodes.
 Binary Tree - Tree with 0, 1 or 2 node only. Any node should have only one path from the root.
 Binary Search Tree (BST) - First key becomes the node, lesser numbers are added to the left and greater numbers are added to the right. If not rebalanced, it becomes O(N). If perfectly rebalanced, search is O(H)
 Heap - Heap is binary tree where the nodes are added to leftmost leaf and swapped based on value. Max heap will have higher values at the top and min heap will have lower values at the top. Nodes are removed from the righ most.
 
 The PACELC theorem is an extension of the CAP theorem that states, in the event of network Partition, one should choose between Availability or Consistency; else, choose between Latency and Consistency.
 Dijkstra’s Algorithm: Given a weighted graph and a source vertex in the graph, find the shortest paths from the source to all the other vertices in the given graph. 
 Haversine formula: The haversine formula determines the great-circle distance between two points on a sphere given their longitudes and latitudes. 
 HTTP Vs WebSocket Vs Asset Service
 
 Example of Web Servers: Contains only Web Container

    Nginx
    Resin
	
Examples of Application Server: Contains a Web Container as well as an application container

    Weblogic
    JBoss
    Websphere

Apache Tomcat behaves like an application server because it can deliver highly dynamic content. It can also behave like a standalone web server, but it is actually a Java servlet container. As such, it does not have the features of a full application server and may not support some enterprise-level requirements.

base58-encode: Uses 1 to 9, 52 upper and lower case alphabets minus I, O and l

Lamport’s quip: “A distributed system is one in which the failure of a computer you didn’t even know existed can render your own computer unusable.”

Multi Threading - Process 1 runs and it got evicted out of CPU. Gets written as PCB (Process Control Block) in main memore to pave way for Process 2. This is called context switching. Every call to DB causes CPU eviction and context swtiching. Amdhal's law determines the acceleration in performance based on the percentage of parallization.

For listening - import org.apache.kafka.clients.consumer.ConsumerConfig; @EnableKafka Configuration and @KafkaListener(topics = "your_topic_name", groupId = "group_id").
For Publishing - org.springframework.kafka.core.KafkaTemplate has a send method that takes the topic name and message as parameters. It has to be implemented in @Service. Invoke this service from the controller. 
 
1 NF - Atomicity of cells are not maintained.
2 NF - An attribute in the table is related only to part of primary key. For instance, in a table with PO and Line, if there is a field dependent on PO alone, it violates 2 NF.
3 NF - An attribute in the table not at all related to the primary key that is present. For instance, department name in employee table. That is, where a non-key attribute depends on another non-key attribute.
BCNF - An attribute combination in the employee table, department id and name, has the determinant (dep id) not defined as a candidate key. Extension of 3 NF. Create the intermediate look up table between the primary key and key 
4 NF - Removes multi value dependency. Multivalued Dependency (MVD) is a form of data dependency where two or more attributes, other than the key attribute, are functionally independent on each other, but these attributes depends on the key .

DATABASES
----------

1. RDBMS
	1. Relational from RDBMS comes from Relational Algebra
	2. There are four different types of joines Inner Join (JOIN), Outer Join (FULL JOIN), Left Outer Join (LEFT JOIN) and Right Outer Join (RIGHT JOIN).
	3. An index is a special data structure built to avoid a full table scan when performing a query. 
	4. RDBM creates an index on the primary key—in particular a B-tree index—where the key is the primary key value and where the value points to a row on disk.
	5. Using the UNIQUE keyword is another way to force an index on a table column. 
	6. If you want VIEWs that do offer such gains, you should consider creating materialized views, which are different because they’re stored on disk in a “real” table
	7. Overindexing will kill the write queries. Under indexing will kill the read queries. 
	
3. COSMOS
	0. Databases -> Tables (called containers in Cosmos) -> Rows (Called items in Cosmos). In MySQL we define things (like store procs, triggers etc.) at Database level. In COSMOS we do that at container level. Containers can be table, document, graph, key value stores based on API.
	1. Database as service 99.999% uptime. No schema or index management. Globally distributed. Encrypted in rest and motion. 
	2. Supports JSON documents, Columnar, Keyvalue and Graph datamodels. Table API to support key value store, Cassandra to suppoort Wide-column, Gremlin supports the graph table and Document DB is supported by Core(SQL) and Mongo DB APIs. Core(SQL) is the only API that supports SQL like querying.	
	3. Azure Cosmos DB's support for consistency levels like strong, eventual, consistent prefix, session, and bounded-staleness.
	4. Azure Table Storage is a NoSQL database, meaning it does not enforce a strict schema. Data is stored as key-value pairs in a highly flexible format. Data not globally distributed like COSMOS DB Table API. Very low latency. ATS allows index only on Primary key. COSMOS table API allows indexing in All keys. No consistency options vs 5 options. Throughput is capped vs unlimited throughput. High vs low latency.
	5. Cosmos DB is price optimized for hot storage.
	6. Throughput is measured in terms of RUs. RU depends on CPU, Memory, IOPS. Query stats will give us RUs. We can request 400 to 100,000 RUs per second.
	7. Data is partitioned based on partition key. Logical partitions are created based on the key. These logical partitions map to physical partitions. A logical partition cannot be stored across multiple physical partitions.
	8. Queries spanning across partitions, called cross partition or fan out queries. Each doc can't exceed 2MB and 1 GB cannot exceed 20GB.
	9. Composite partition keys improves the scale. Autoindexing in Cosmos takes care of indexing all the fields using the path to root node in the tree /locations/0/country/name: "Denmark".
	10.Inverted indexing is a data structure commonly used in search engines, databases, and other information retrieval systems to enable fast full-text searches. It essentially maps content, like words or terms, to their locations within a document or a set of documents, allowing for efficient retrieval of data that matches specific search queries.
	11.Time to Live 
	12.Conflict resolution during multi master writes - Last Write Wins and Merge Procedure
	13. Five consistency levels
		a. Strong - Gets updated at the same time synchronously
		b. Bounded Staleness - Willing to read stale data for n seconds or up to a maximum lag of 10 operations. 
		c. Session - Within session, consistency is maintained.
		d. Consistent Prefix - Order of writes is guaranteed, but not immediete availability.
		e. Evantual Consistency 
		
		We can set a default consistency to the account and can override at request level with weaker consistency.
	14. CLI commands are prefixed with az cosmosdb 
	15. We can monitor using monitor services.

4. Azure SQL
	0. Azure SQL is RDBMS, with strong consistency, SQL access and vertical scalability. Cosmos DB is documented based with 5 different levels of consistency, API access and horizontally scalable.
	1. IaaS - We will be given an VM with SQL Server in it. Though the VM is fully managed, the instance of SQL server should be managed manually. Back up, DR, Patching is all our responsibility. Installed in a VM.
	2. DaaS - PaaS offering. Azure SQL will take of managing the database. Pay as you go, platform as a service and can be scaled easily. Choosing the right service tier will take care of that. Installed in a logical server.
	3. Azure SQL is available as Single Database, Elastic Pool (also single databases, but from shared resources) and Managed instances (Multiple databases, closest to IaaS).
	4. 
	
	Qs
	1. What is the partition key for our collections?
	2. How are the RU through puts defined in our collections? Autoscale or Manual?
	3. How do I query using presto?
	4. Do we have single master doing the writes or we have multi master writes?
	5. What does SOE users look at? Will our consistency unacceptable to some?
	
	PODs * Concurrency -> Number of Partitions.
	
	12 PODs for E.
	
Design Patterns
---------------

1. Observer Pattern - Defines a one to many dependency between objects so that when one object changes state, all of its depenendents are notified and updated accordingly. 
2. Decorrator Pattern - Decorates objects in a chain. Think of starbucks coffee. Every additional condiment is going to add additional cost.
3. Factory Pattern - We have a ProductFactory abstract class with sub classes for concrete products. The concrete product implements a product interface. The ProductFacture sub classes returns the object. For ex. VehicleFactory carFactory = new CarFactory();
4. Singleton Pattern - A singleton Class has a private constructor. So, it can't be instantiated. Call the get instance method to get the static instance of the class. This pattern is particularly useful in scenarios where having multiple instances of a class could lead to issues, such as inconsistent data, resource contention, or unexpected behavior. If we make the method synchronized it will be threadsafe.
5. Command Pattern - We need objects of different classes to execute things for us. So, create a Command interface with an execute Method. Classes will implement the Command interface. The objects will be created with the reference type as Command. This objects will be passed through controller to executed the command.

Hadoop - Big Data
------------------
As we can see, both the Map and Reduce phases can be run exclusively and hence can use independent nodes in the cluster to process the data. This approach of separation of tasks into smaller units called Map and Reduce has revolutionized general purpose distributed/parallel computing, which we now know as MapReduce.

Data: HDFS has NameNode and DataNode daemons running in master node and worker nodes respectively. Resource Management: YARN has ResourceManager and NodeManager daemons running in master node and worker nodes respectively. Data Consistency: Zookeeper ensures data consistency through the establishment of quorum. In a distributed system, there are multiple nodes or machines that need to communicate with each other and coordinate their actions. ZooKeeper provides a way to ensure that these nodes are aware of each other and can coordinate their actions. It does this by maintaining a hierarchical tree of data nodes called “Znodes“ The Hive Metastore curates information about the structured datasets (as opposed to unstructured binary data) that reside in Hadoop and organizes them into a logical hierarchy of databases, tables, and views.

  Another objective that YARN met was that it made MapReduce one of the techniques to process the data rather than being the only technology to process data on HDFS, as was the case in Hadoop 1.x systems. This paradigm shift opened the floodgates for the development of interesting applications around Hadoop and a new ecosystem other than the classical MapReduce processing system evolved. It didn't take much time after that for Apache Spark to break the hegemony of classical MapReduce and become arguably the most popular processing framework for parallel computing. YARN: https://learning.oreilly.com/library/view/apache-spark-2-x/9781787126497/29f3e055-60a2-4c83-8954-8ea1aa58eee0.xhtml
 
 Hadoop - Name Node and Data Node
 Spark - Driver and Executor
 YARN - Resource Manager and Node Manager
 
 In Spark, when the lambda expressions were sent to each partitions in the node. If there are movement of data between partitions, it is called 
 
 Why Spark?
 1. Performance - Disk I/O vs Memory
 2. Fault Tolerance - Hadoop launches a new JVM task after checking with RM for failed tasks. Spark uses RDD, a read only fault-tolerant parallel collection. RDD maintains the lineage graph so that whenever its partition gets lost it recovers the lost data by re-computing from the previous stage and thus making it more resilient.
 3. DAG - Chaining and creating a plan ensures that only the computations required by actions are executed.
 4. Spark is General purpose while Hadoop is an isolated distributed compute framework. 
 5. Hadoop only on YARN. Spark on Mesos, YARN and standalone resource manager.
 6. Hadoop - low level programming. Spark has APIs.

As Spark is written in a functional programming paradigm, one of the key concepts of functional programming is immutable objects. Resilient Distributed Dataset is also an immutable dataset.

As discussed in the beginning of this chapter, you control your Spark Application through a driver process called the SparkSession. The SparkSession instance is the way Spark executes user-defined manipulations across the cluster. There is a one-to-one correspondence between a SparkSession and a Spark Application.
Narrow transformations or pipelining will happen in memmory. But that cannot be said about the wide transformations (or shuffling). When we perform a shuffle, Spark writes the results to disk. 

Spark includes the ability to read and write from a large number of data sources. To read this data, we will use a DataFrameReader that is associated with our SparkSession.

 We can call explain on any DataFrame object to see the DataFrame’s lineage (or how Spark will execute this query)
 
 RDDs - No Compile and Runtime type safety; Dataframe - No compile time type safety, but provides run time type safety; Datasets - Provides both runtime and compile time type safety (available only in Java and Scala). 
 
 For reading a JSON file, you would typically use the DataFrame API and then convert it to an RDD:
 Dataset<Row> df = spark.read().json("path/to/your/file.json");
JavaRDD<Row> rdd = df.javaRDD(); Using the DataFrame API to read a JSON file and then converting it to an RDD is a common practice because the DataFrame API in Spark is optimized for reading and processing structured data formats like JSON, CSV, and Parquet.

Transformation: If an operation on an RDD gives you another RDD, then it is a transformation.  If an operation on an RDD gives you a result other than an RDD, it is called an action: for example, the sum of all values in an RDD

Flume Java and Apache Crunch were the predecessors.

Spark JDBC since 2014. While reading partition can be provided in the Spark JDBC. 

$SPARK_HOME/bin/spark-shell - Will open spark in stand alone mode. 


Performance considerations with Spark.

1. Think about transformations and actions. If you perform frequent actions, there may be memory/disk spill.
2. Look at the DAG execution plan to see any partition skews. Add a Scanner to keep the console waiting. The boxes in the DAG will take you to the code line.
	- Look at the distributions. 
	- Each task is going to run on each partitions. So, if any task is running long, it is because of there is a data skew towards those partitions. It is called key skews. You can salt the key by attaching a random key. 
3. Narrow transformation within a partition is good. Wide transformation (for example Join) or shuffle is bad. How to avoid shuffles? First filter and reduce the records and then do the shuffle operations. A stage is a series of transformations where shuffle is not needed. So, where there are multiple stages in the DAG, there usually is a shuffle.
4. Avoid Group By Key - Group By will over crowd the partitions. For instance, employees RDD group by geneder will result in two partitions. If you have to count the number of employess by geneder, don't do group by first. But, do a redecue by key, that gives something like (Man, 1), (Woman, 2) etc, Add them and then shuffle.
5. Caching and Partition

An RDD can be stored in mememory and disk by rdd.persist(memory_and_disk).

Spark SQL is for strucutred data. It is not only for databases. 

Broadcast the smaller tables to many nodes.

Standard deviation, correlation characteristics. Netflix - I rate the flims. Netflix finds correlation 

Standard scaler to cull out the tail events.
One-hot encoding transforms categorical variables into a binary vector format. Like R, B, G col into (1,0,0),(0,1,0),(0,0,1). pandas.get_dummy()
Label encoder. Transforms categorical or descriptive data with high cardinality. Scikit-learn library was used

SelecKtBest is used for feature selection. SelectKBest from Scikit 
Random forrest classification algorithm.

Big Query:
---------
	We usually extract data from files on Google Cloud Storage. Transform and load it into BigQuery’s native storage.(ETL). We can also do EL when the data doesn't require transformation. We can do ELT when the schema of the raw data is in flux
	BigQuery’s storage system is built on the idea that when you’re dealing with structured storage, the appropriate abstraction is the table, not the file.
	Because BigQuery separates compute and storage, it is possible to run BigQuery SQL queries against CSV (or JSON or Avro) files that are stored as-is on Google Cloud Storage; this capability is called federated querying.
	The primary way you interact with BigQuery is via SQL, and because BigQuery is an SQL engine, you can use a wide variety of Business Intelligence (BI) tools such as Tableau, Looker, and Google Data Studio to create impactful analyses, visualizations, and reports on data held in BigQuery. 
	When you have “big data,” Gray said, “you want to move the computation to the data, rather than move the data to the computation.” 
	Protocol Buffers (often abbreviated as Protobuf) is a language-neutral, platform-neutral, extensible mechanism for serializing structured data. Developed by Google, Protocol Buffers are designed to be smaller, faster, and simpler than XML or JSON, making them ideal for communication between services, storing data, or defining APIs.
	The most advanced SQL engine was called Dremel. Dremel is used to query files that sit on Colossus, Google’s file store for storing data.
	BigQuery added a storage system that provided a table abstraction. 
	In many data warehouses, compute and storage reside together on the same physical hardware.  This colocation means that in order to add more storage, you might need to add more compute power as well. BigQuery separates compute from storage.
	BigQuery resources are denominated in terms of “slots,” which are, roughly speaking, about half of a CPU core. BigQuery uses slots as an abstraction to indicate how many physical compute resources are available. 
	Queries running too slow? Just add more slots. More people want to create reports? Add more slots. Want to cut back on your expenses? Decrease your slots.
	BigQuery differs from other cloud data warehouses in that queries are served primarily from spinning disks in a distributed filesystem. Most competitor systems need to cache data within compute nodes to get good performance.
	BigQuery, on the other hand, relies on two systems unique to Google, the Colossus (next gen cluster level) File System and Jupiter networking (in-house datacenter network, providing 1 Petabit/sec bandwidth), to ensure that data can be queried quickly no matter where it physically resides in the compute cluster.
	Federated queries allow BigQuery to query data held in Google Cloud Storage, Cloud SQL (a relational database), Bigtable (a NoSQL database), Spanner (a distributed database), or Google Drive (which offers spreadsheets). 
	

Distributed Systems
-------------------
Loosely speaking, a distributed system is composed of nodes that cooperate to achieve some task by exchanging messages over communication links. A node can generically refer to a physical machine (e.g., a phone) or a software process (e.g., a browser).

Commmunication Models or links:
	The fair-loss link model assumes that messages may be lost and duplicated - Think about IP.
	The reliable link model assumes that a message is delivered exactly once, without loss or duplication. - Think of TCP
	The authenticated reliable link model makes the same assumptions as the reliable link, but additionally assumes that the receiver can authenticate the message’s sender. - Think of TLS

Node Models	
	Arbitrary Fault Model or Byzantine Model assumes that a node can deviate from its algorithm in arbitrary ways, leading to crashes or unexpected behavior due to bugs or malicious activity.
	Crash-recovery model assumes that a node doesn’t deviate from its algorithm, but can crash and restart at any time, losing its in-memory state.
	Crash-stop model assumes that a node doesn’t deviate from its algorithm, but if it crashes it never comes back online.
	
Timing Models
	Synchronous
	Asynchronous
	Partially Synchronous 

Ping vs Heartbeat
	A ping is a periodic request that a process sends to another to check whether it’s still available.
	A heartbeat is a message that a process periodically sends to another to inform it that it’s still up and running.
	
Clocks
	The rate at which a clock runs is also called clock drift. In contrast, the difference between two clocks at a specific point in time is referred to as clock skew.
	Because quartz clocks drift, they need to be synced periodically with machines that have access to higher accuracy clocks, like atomic ones. Atomic clocks1 measure time based on quantummechanical properties of atoms and are significantly more expensive than quartz clocks and are accurate to 1 second in 3 million years.
	The Network Time Protocol (NTP) is used to synchronize clocks.
	A monotonic clock measures the number of seconds elapsed since an arbitrary point, like when the node started up, and can only move forward in time. A monotonic clock is useful to measure how much time elapsed between two timestamps on the same node, but timestamps of different nodes can’t be compared with each other.
	A logical clock4 measures the passing of time in terms of logical operations, not wall-clock time. The simplest possible logical clock is a counter, which is incremented before an operation is executed.
	Lamport clock does order the events across processes which happened before the events started interacting. It is simply a counter that gets added on events and passed to the next process. Vector clocks has as many dimensions as the number of processes [n1,n2,...np]. Each dimension is incremented and merged when there is an interprocess communication (IPC)
	
Leader Follower 
	Raft leader election.
	
Consistency Models
	The simplest form of linearizability is to ask the clients to read and write from the same node. That will take time, because the node has to confirm everytime, whether it is the leader.
	Sequential consistency is when the one client is attched to one node. In this case, if the node happens to be a follower node, the updates are going to take time. But, it will be in a sequential order. The difference between linearizability and sequantial consistency is the realtime guarantee.
	Eventual Consistency is when we allow the clients to query any follower. The only guarantee the client has is that eventually, all followers will converge to the final state if the writes to the system stop. This consistency model is called eventual consistency.

Isolation Models - https://www.postgresql.org/docs/current/transaction-iso.html

	We should understand the challenges before getting into 
	Read Uncommitted - Prevents dirty writes
	Read Committed - Prevents dirty reads
	Repeatable Read - Prevents fuzzy reads. A careful review of the literature shows that Snapshot Isolation (SI) is very similar to Repeatable Read (RR), but they aren't exactly the same. They are both stronger than Read Committed but weaker than Serializable. The best summary of their difference is that SI allows write skew but blocks phantoms, whereas RR blocks write skew but allows phantoms.
	Serializability - Prevents phantom reads
	
Atomicity
	Achieved through 2PC. 2PC has two phases, Prepare phase where a the updates are sent to all processes and their confirmation is sought and commit phase where the processes are asked to commit (because everyone is good.)
	The assumption is that the coordinator and the participants are available and that the duration of the transaction is short-lived. Because of its blocking nature, 2PC is generally combined with a blocking concurrency control mechanism, like 2PL, to provide isolation.
	
	
Serializability is achieved through concurrency controls.
	Serializability can be achieved either with a pessimistic or an optimistic concurrency control mechanism.
	Pessimistic concurrency control uses locks to block other transactions from accessing a data item.
		The most popular pessimistic protocol is two-phase locking6 (2PL). It has write locks (exclusive) and read locks (shared). The locks happen in two phases expanding (locks are acquired) and shrinking (locks are released).
	The optimistic approach to concurrency control doesn’t block, as it checks for conflicts only at the very end of a transaction.
		If a conflict is detected, the transaction is aborted or restarted from the beginning. Generally, optimistic concurrency control is implemented with multi-version concurrency control7 (MVCC).
		With MVCC, the data store keeps multiple versions of a data item.
		Optimistic concurrency makes sense when you have read-heavy workloads that only occasionally perform writes, as reads don’t need to take any locks. 
		For write-heavy loads, a pessimistic protocol is more efficient as it avoids retrying the same transactions repeatedly.
		
		
State machine replication
-------------------------
State machine replication is a technique used in distributed systems to ensure that a group of machines (often called replicas) maintain a consistent state despite failures or network partitions. The idea is that each replica implements a state machine, which is an abstract machine that can be in one of a finite number of states and transitions between these states based on a sequence of inputs or events. By ensuring that all replicas process the same sequence of inputs in the same order, state machine replication guarantees that all replicas will arrive at the same state, even if some replicas fail or are temporarily disconnected.

A saga is a distributed transaction composed of a set of local transactions 𝑇1, 𝑇2, ..., 𝑇𝑛, where 𝑇𝑖 has a corresponding compensating local transaction 𝐶𝑖 used to undo its changes. A saga can be implemented as workflow. AWS Step Functions20 or Azure Durable Functions21 make it easy to create fully-managed workflows. Saga ensures atomicity. It can also ensure isolation  thorugh semantic locks. The idea is that any data the Saga modifies is marked with a dirty flag. This flag is only cleared at the end of the transaction when it completes. Another transaction trying to access a dirty record can either fail and roll back its changes, or block until the dirty flag is cleared. The latter approach can introduce deadlocks, though, which requires a strategy to mitigate them.

Scalability
-----------
There are three dimensions of scalabilty; functional decomposition, partition and replication.


A special type of replication is cache. 

As you scale out, any failure that can happen will eventually happen. Hardware failures, software crashes, memory leaks — you name it. The more components you have, the more failures you will experience. Resliency is all about how you mitigate those failures by implementing self-healing mechanisms.

Common causes of failure are 
	1. Single point of failure
	2. Unreliable Network 
	3. Slow processes due to memory leak etc.

Use exponential back off algorithm before retrying. 

Circuit Breaker States: Closed (allows requests), open (stops requests), partial open (checks for the availability of the downstream and decides accordingly). Since circuit breaker is an example of downstream resliency.

When the server detects that it’s overloaded, it can reject incoming requests by failing fast and returning a 503 (Service Unavailable) status code in the response. This technique is also referred to as load shedding. Depending on how the rejection is implemented, the server might still have to pay the price of opening a TLS connection and read the request just to finally reject it. Hence, load shedding can only help so much, and if the load keeps increasing, eventually, the cost of rejecting requests takes over, and the service starts to degrade.

Load Leveling can be done by introducing a messaging queue between the client and server to spread out the work load.

Rate-limiting, or throttling, is a mechanism that rejects a request when a specific quota is exceeded. When a service rate-limits a request, it needs to return a response with a particular error code so that the sender knows that it failed because a quota has been breached. For services with HTTP APIs, the most common way to do that is by returning a response with status code 429 (Too Many Requests). Rate-limiting is also used to enforce pricing tiers. You would think that rate-limiting also offers strong protection against a denial-of-service3 (DDoS) attack, but it only partially protects a service from it. Nothing forbids throttled clients from continuing to hammer a service after getting 429s. And no, ratelimited requests aren’t free either — for example, to rate-limit a request by API key, the service has to pay the price to open a TLS connection, and to the very least download part of the request to read the key. Although rate-limiting doesn’t fully protect against DDoS attacks, it does help reduce their impact.

Although rate-limiting has some similarities to load shedding, they are different concepts. Load shedding rejects traffic based on the local state of a process, like the number of requests concurrently processed by it; rate-limiting instead sheds traffic based on the global state of the system, like the total number of requests concurrently processed for a specific API key across all service instances.

The goal of the bulkhead pattern is to isolate a fault in one part of a service from taking the entire service down with it.

CI/CD
-----
Continuous integration ensures that code changes are merged into the main branch after an automated build and test suites have run. Once a code change has been merged, it should be automatically published and deployed to a production-like environment, where a battery of integration and end-to-end tests run to ensure that the service doesn’t break any dependencies or use cases.

A memory leak occurs when programmers create a memory in a heap and forget to delete it. That is you do malloc in C++ and exit the process without a dealloc.