# Employee-Reimbursement Application

This is my Project1 app which allows Employees to make and access their Reimbursement claims.
Managers are able to approve or deny pending claims as well as view old claims.

Running the application requires a MySQL DB connection with tables that conform to the
models used. The frontend currently runs on http://localhost:7000 by default.

Features:
  - Login Functionality
  - Account Creation
  - Claim Creation
  - Claim Approval/Denial by Admin Accounts
  - Review of previous Claims

Roles
  - Creation of Database 
  - Implementation of Backend Functionality
  - Implementation of Frontend User Interface
  - Integration of the previous features

Responsibiities
  - Creation of Database Tables for Accounts and Claims
  - Establishment and management of the DB connection using JDBC
  - Creation of REST endpoints with the help of Javalin
  - Logging of relevant actions using slf4j

  - User Interface features
    - Login and Registration forms
    - New Claim form available to logged in Users
    - List of previous claims by an Employee
    - Pending Claim table where Admin can approve or deny claims
    - List of all past claims available to Admin

Technology
  - Java
  - Javalin
  - HTML, CSS
  - JDBC, MySQL
  - JUnit
  - slf4j
  - Selenium
