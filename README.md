# Opera Booking Service
In this project used N-tier architecture with DB layer, DAO layer, Service layer, Controllers layer and View layer.
Project was developed according to SOLID principles with authorization and authentication.

UML diagram that describes the relationship between the entities.
 ![img_1.png](https://user-images.githubusercontent.com/75170409/109185591-d3f95800-7798-11eb-984e-2f0e7a06671e.png)
One user can have multiple roles.

No role:
Registration

Authorization
View a list of available Performances
View the list of Stages
Find session by date

User:
View a list of available Performances
View the list of Stages
View order list
Find session by date
Add sessions to shopping cart
Make an order
logout

Admin:
View / add Performance
View / add Stage
Add Performance session
Find session by date
Find user by email
logout

Technologies used
backend: Java, Spring Core/MVC/Security, Hibernate, Jackson, Tomcat
database: MySQL
tools: lombok, log4j

To start the project you need:

1.[Download and install the JDK](https://www.oracle.com/java/technologies/javase-downloads.html,)

2.[Download and install servlet container](https://tomcat.apache.org/download-90.cgi,) (for example Apache Tomcat)

3.[Download and install MySQL Server](https://dev.mysql.com/downloads/)

Setup new connection with

user: "your username"
password: "your password"
url: jdbc:mysql://"your host name":"your port"/"your name db"?useUnicode=true&serverTimezone=UTC

Run a project
