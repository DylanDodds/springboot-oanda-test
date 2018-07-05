# Project Notes
**Setting up SpringBoot IntelliJ Idea Community**
    - Install IntelliJ Idea, Spring & Lombok Plugin
    - Create a spring initializr from http://start.spring.io/
    - Use web as dependencies
    - Download package
    - Open extracted Package in intellij

**Add oAnda v20 package to maven project, pom.xml**
```xml
<dependency>
  <groupId>com.oanda.v20</groupId>
  <artifactId>v20</artifactId>
  <version>3.0.24</version>
</dependency>
```

**Add Lombok package to maven project**
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.16</version>
    <scope>provided</scope>
</dependency>
```

**Add SLF4J package to maven project**
```xml
<dependency>
  	<groupId>org.slf4j</groupId>
  	<artifactId>slf4j-jdk14</artifactId>
  	<version>1.8.0-beta2</version>
</dependency>
```

**Create a service and its interface (@Service, @SpringBootApplication(scanBasePackages={“”})**
	- Create directory service & service/impl
	- Create new Java Interface IOAndaService in service/
	- Create new Java class OAndaService in service/impl
	- Make OAndaService implement IOandaService
    - (Public methods for OAndaService should be exposed via IOandaService)

**Create oanda config and application.yml**
    - Create directory <project root>/config
    - Create file named “application.yml” in <project root>/config
    - Add parameter to config oanda/api-token, oanda/account-number, oanda/url, oanda/username
    - Create directory <src root>/config
    - Create Java class OAndaConfig in <project root>/config
    - Give the class annotations:
    ```java
        @Data
        @Configuration
        @ConfigurationProperties(prefix="oanda")
    ```
    Give the class...
    ```java
        private String apiToken;
        private String accountNumber;
        private String username
        private String url
    ```


**Create an OAndaManager Component (@Component)**
    - Give the component an @Autowired constructor and give it the parameters:
	    - OAndaConfig oAndaConfig
    - Create a private Context (Class from OAnda Library)
    - Create an @PostConstruct annotation and define your context
	```java
        ctx = new ContextBuilder(oAndaConfig.getUrl())
       .setToken(oAndaConfig.getApiToken())
       .setApplication("OAndaManager")
       .build();
    ```

**Create an OAndaBotStartup**
	- Give StartupClass a @Component Annotation
	- Give StartupClass an @Autowired Constructor taking in our OandaService
**Make Startup Class implement ApplicationListener<ApplicationReadyEvent>**
	- Add @Override method void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)
	- This method will contain the main functionality of out application


**Edit <MainApplication>.java**
    - Add scanBasePackages parameter to SpringBootApplication
    - Set value of scanBasePackages to {“package to be scanned”, “other package to be scanned”}
    - Scanned packages should include: Services, Configs, Components, etc.



## Getting account ID
	- Create Postman GET Request to https://api-fxpractice.oanda.com/v3/accounts
	- Under the authorization tab, select Bearer Token Authorization and paste your API Token Key
	- Submit the GET Request and the endpoint will resolve a list of accounts you current own
