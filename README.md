# Credit Card Java Application

Credit Card Application is a java application which allows user to add the Credit Card details and view them as a list. It is a RESTful API.

**Tech Stack :**

Java - 8,
Springboot  - 2.7.0,
Maven 4.0.0,
Apache Tomcat/9.0.63
JPA,Hibernate,
H2 database (In memory),
Junit , Mockito,
Spring Security ,
Spring Validations,
JWT

**Endpoints :**
Save Credit Card - http://localhost:8081/creditcard/save
Get All Credit Cards  http://localhost:8081/creditcard/getAll

**Validations:**
1. Credit card length should be 19 or less
2. Credit card value cannot be empty
3. Credit card must be valid as per Luhn algorithm
4. Credit card will accept only Numeric
5. Expiry Date must be Future Date or Present Date

**Exceptions**
Exceptions are handled globally using ControllerAdvice

**Security**
1. For fetching and saving the data user must have valid credentials (username & password).
2. Credit cards are never saved in plain text . The values are encrpted while saving to the database and decrypted on fetching from DB.
3. User passwords are also encrypted and saved in DB.

**Assumptions**
1. Users are registered with Credit card Application
2. User Table is pre populated with the user data on application startup

**Getting Started**
1. Clone the repository
   - git clone <link to clone>
2. Import as Maven project in IDE (Eclipse/Intellij)
3. Let dependencies get resolved
4. Run application as Java Application. Run the client (Main) class.  Rest End points are exposed and can be accessed
5. Run Junit from src/test/java 

**Application Flow**

![image](https://user-images.githubusercontent.com/94593339/170864543-99a31b1b-d8ae-4562-90ec-4a614fccc491.png)

**ER Diagram**
![image](https://user-images.githubusercontent.com/94593339/170874229-a3e8a87b-d838-4969-a515-1cc40be8bccc.png)

**Testing Screenshots:**

**Login Page**
![image](https://user-images.githubusercontent.com/94593339/170874328-338aa4c8-92e7-49aa-8356-e04dc1c63616.png)

**Home Page**
![image](https://user-images.githubusercontent.com/94593339/170874341-84e0c9f1-24ac-4fc9-ab57-591c9de3fb1c.png)

**Add Credit Card**
![image](https://user-images.githubusercontent.com/94593339/170874356-59e1ad6c-2c12-40ad-b627-0148a4007b9c.png)

![image](https://user-images.githubusercontent.com/94593339/170874370-b59b0bfb-cb16-4bf2-8545-1d1e149b49f1.png)

**Get All Credit Card**
![image](https://user-images.githubusercontent.com/94593339/170874379-d1872934-70c6-4ec1-9b50-0ed8301ab753.png)

**Testing via postman**
![image](https://user-images.githubusercontent.com/94593339/170874406-0c10c264-d5d2-438f-91d0-cb858283470b.png)

![image](https://user-images.githubusercontent.com/94593339/170879721-462ff1cd-cbe0-4f06-90d4-2c0e8598fe1f.png)


 **Not a Luhn 10 Credit card number**
![image](https://user-images.githubusercontent.com/94593339/170879768-e833c1bd-b437-4c43-b0b0-64a2f01b8b53.png)



**Junit Testing**

![image](https://user-images.githubusercontent.com/94593339/170879873-33dd5567-0d94-4823-b18e-6cf597a997b0.png)


**Service Testing**
![image](https://user-images.githubusercontent.com/94593339/170882853-5dabef18-9985-49a4-8958-149adb5604c8.png)



**Controller Testing**
![image](https://user-images.githubusercontent.com/94593339/170883090-5eebab8e-19e7-4fba-8250-eaaf9971fabf.png)


Note** : UI screenshots are from springboot client application (dummy consumer service ) which is consuming this credit Card Application using Rest Templates.
Refer  for Client app - creditAppUi folder . 









