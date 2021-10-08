# ASDE Training

## Spring framework

Last updated on 27th June 2021

### TOC for day 11:

-   Over view of Spring framework
-   Inversion of control
-   Dependency injection
-   Loose coupling
-   Spring bean lifecycle
-   Singleton/prototype beans
-   Lazy Singletons
-   Wiring - manual/auto
-   components - different types
-   component scanning

![Concepts](concepts.dio.png 'Concepts')

### Assignment

-   Create the classes and interfaces as shown in the diagram below:
    -   App, ProductDao, JpaProductDao, MongodbProductDao, AppConfig
-   In the AppConfig, define beans for EntityManager and MongoClient
-   In the AppConfig, scan for components to auto instantiate DAO implementation classes
-   In the DAO implementation classes, `autowire` the dependencies
-   In the App.java's main method,
    -   create the Spring container
    -   get the `jpaProductDao` bean and call all the functions to see if they work
    -   do the same for `mongodbProductDao` bean

![Assignment](assignment.png 'Assignment')
