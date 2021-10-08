# SAPIENT ASDE - JUNE, 2021

### TOC for day 7:

-   Overview of NOSQL and Mongodb
-   Setup a local server
-   Mongodb Atlas
-   Basic operations on collections
-   Using Mongodb drivers in Java
-   Examples of Save+Retrieve docs from Java

![](./jpa-mongodb-demo/concepts.dio.png 'Concepts')

## Assignment

#### Tables from the ER diagram created in day 6 assignment

-   Create a database schema diagram for the following tables
    -   customers (customer_id, name, street, city, state, pincode, country, email, phone, password)
    -   category (category_id, name, description)
    -   products (product_id, name, description, price, category_id)
    -   pictures (picture_id, picture_path, product_id)
    -   orders (order_id, customer_id, order_date, coupon_code, status)
    -   orders_products (order_id, product_id, discount, quantity)
    -   reviews (review_id, customer_id, product_id, review_date, review, rating)
-   Create an SQL script file called `dbscript.sql` for creating the tables on MySQL database
-   Create the tables in your MySQL database by running the following command from MySQL client CLI
    -   `source dbscript.sql`
