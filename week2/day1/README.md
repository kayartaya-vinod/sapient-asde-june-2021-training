# SAPIENT ASDE - JUNE, 2021

### TOC for day 6:

-   Data model
-   Types: Conceptual/logical/physical
-   ER modeling - Entity/Attribute/Relationship
-   ER diagrams/normalization
-   RDBMS using MySQL/Postgres
-   DDL/DML/Queries
-   Basic examples of JDBC (connect/insert/select)
-   Problems with JDBC and why we need ORM
-   What is JPA?
-   Setting up environment
-   Entity classes
-   Configuration
-   CRUD operations
-   Queries

### Create a new docker container to run MySQL server

`docker run -d --name=mysql8server -p 3306:3306 -v mysql8volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=Welcome#123 mysql:latest`

### Open a MySQL CLI

`docker exec -it mysql8server mysql -uroot -pWelcome#123`

### Get sandbox code from here:

`git clone https://github.com/kayartaya-vinod/ps-asde-day-06.git`

![](./concepts1.dio.png 'Concepts')
