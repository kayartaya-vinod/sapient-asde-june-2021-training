# SAPIENT ASDE - JUNE, 2021

### TOC for day 9:

-   HTML response using JSP
-   MVC for building a web application using servlets/jsp

![Concepts](./concepts.dio.png 'Concepts')

## Assignment

#### Part 1: Create a class called DaoFactory for loose coupling

![](./dao.png 'DaoFactory for loose coupling')

Now that your Maven Jar project is ready, run the `mvn clean install` command to install the current project artifacts in your local Maven repository. To verify visit the `<HOME_DIRECTORY>/.m2/repository` and check if the folder structure `com/sapient/...` exists with the generated .jar file.

#### Part 2: Create a new Maven project using the artifact `maven-architype-webapp`

-   Add the necessary dependencies
    -   lombok, slf4j, mysql, servlet-api, jsp-api, jstl api/impl
    -   The newly installed artifact consisting of the entity and DAO interfaces/classes
-   The application should have following stories:
    -   Should list the available products (name, description)
    -   Should show the details of a single selected product (all details including category and pictures)
    -   Should add a new product
    -   Should add new picture path to existing product
    -   Should delete a picture (path) of an existing product
    -   Should all editing of product details
    -   Should allow search of products by range of price
-   The application should have a nice homepage with navigational options for all the stories listed above
