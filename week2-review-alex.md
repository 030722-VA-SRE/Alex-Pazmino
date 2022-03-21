# SQL

### Relations

* Explain what SQL is. What are some SQL databases?

	Structured Query Language is a standard language for relational database management systems(RDBMS) SQL is uses statements that perform tasks such as create, read, update, or delete data from a database.

	SQL databases:

	MySQL, MariaDB, Oracle, PostgreSQL, MSSQL


* How is data structured in a SQL database?

Structured with tables consisting of rows and columns.
	
	Five Factors:
		Data Type: information you want to store.

		Use Case: How the information will be used.

		Location: where the data will be stored

		Efficiency: The best way for you to organize it for ez access

		Storage: how you can optimize storage reservation

* What is an ERD? How is it useful?

	An entity-relationship diagram (ERD) is crucial to creating a good database design. It is used as a high-level logical data model, which is useful in developing a conceptual design for databases.

* What are the different multiplicity relationships? How would you create these relations?

	One to one: related to a single instance of another entity.
	One to many: person to group
	Many to many: Course and Student
	Many to one: group to person


* What kind of relationship would exist between Students and Classes? Students and Textbooks?

	Students and Classes: Many to many
	Students and Textbooks: One to Many

 
* Explain the concept of referential integrity

	Referential integrity refers to the relationship between tables. Because each table in a database must have a primary key.

When a primary key from one table appears in another table, it is called a foreign key .

* What is a cascade delete?

	A foreign key with cascade delete means that if a record in the parent table is deleted, then the records in the child table will automatically be deleted.
    

* List the integrity constraints


	entity integrity, 
	referential integrity,
	key constraint,
	domain integrity.
    

* Define the word "schema"


	A schema is a list of logical structures of data. A database user owns the schema, which has the same name as the database manager. In SQL, a schema is an individual entity (container of objects) distinct from the user who constructs the object.
    

* What is a candidate key? What about a surrogate key?


	A candidate key is a specific type of field in a relational database that can identify each unique record independently of any other data.


	A surrogate key in a database is a unique identifier for either an entity in the modeled world or an object in the database.

 
### Sublanguages & Queries
    
* What are the 5 sublanguages of SQL? List some commands for each


	Data Definition Language: create, alter, drop, truncate


	Data Manipulation Language: insert, update, delete


	Data Control Language: grant, grant revoke
	

	Transaction Control Language: commit, rollback, savepoint
	

	Data Query Language: select

    
* What is the difference between DELETE, DROP, and TRUNCATE commands?


	Delete: remove one or more row from a table


	Drop: delete both the structure and record stored in the table


	Truncate: delete all rows from the table and free space containing the
	table

* What are some SQL clauses you can use with SELECT statements?
   
	WHERE,
 	GROUP BY, 
	HAVING, 
	ORDER BY 	

* What is the difference between joins and set operators?


	JOIN in SQL is used to combine data from many tables based on a matched condition between them. The data combined using JOIN statement results into new columns.


	UNION in SQL is used to combine the result-set of two or more SELECT statements. The data combined using UNION statement is into results into new distinct rows.

 
* What are the types of joins? Explain the differences.
 
•	(INNER) JOIN: Returns records that have matching values in both tables

•	LEFT (OUTER) JOIN: Returns all records from the left table, and the matched records from the right table

•	RIGHT (OUTER) JOIN: Returns all records from the right table, and the matched records from the left table

•	FULL (OUTER) JOIN: Returns all records when there is a match in either left or right table


* Explain the difference between UNION, UNION ALL, and INTERSECT

•	UNION: combines results from both tables.

•	UNION ALL: combines two or more result sets into a single set, including all duplicate rows.

•	INTERSECT: takes the rows from both the result sets which are common in both.

•	EXCEPT: takes the rows from the first result data but does not in the second result set.


### Transactions

* What are the properties a transaction must follow?
 
•	Atomicity: each statement in a transaction (to read, write, update or delete data) is treated as a single unit. Either the entire statement is executed, or none of it is executed.

•	Consistency: ensures that transactions only make changes to tables in predefined, predictable ways.

•	Isolation: ensures that the concurrent transactions don’t interfere with or affect one another.

•	Durability: ensures that changes to your data made by successfully executed transactions will be saved, even in the event of system failure.
 
* Explain the different isolation levels. What read phenomena do each prevent?

Read Uncommitted: Read Uncommitted is the lowest isolation level. In this level, one transaction may read not yet commit changes made by other transactions, thereby allowing dirty reads. At this level, transactions are not isolated from each other.

Read Committed: This isolation level guarantees that any data read is committed at the moment it is read. The transaction holds a read or write lock on the current row, and thus prevents other transactions from reading, updating, or deleting it. It does not allow dirty read.

Repeatable Read: This is the most restrictive isolation level. The transaction holds read locks on all rows it references and writes locks on referenced rows for update and delete actions. Since other transactions cannot read, update or delete these rows, it avoids non-repeatable read.

Serializable: This is the highest isolation level. A serializable execution is guaranteed to be serializable. Serializable execution is defined to be an execution of operations in which concurrently executing transactions appears to be serially executing. It prevents all phenomena

### Practicals

Given the following table 'employees'...

| id | firstName | lastName | salary | dept |
| --- | -------- | -------- | ------ | ---- |
| 1  | Michael   | Scott    | 65     | Sales|
| 2  | Dwight    | Schrute  | 75     | Sales|
| 3  | Toby      | Flenderson| 80    | HR  |
| 4  | Jim       | Halpert  | 90     | Sales|
| 5  | Oscar     | Martinez | 90     | Accounting |
| 6  | Angela    | Martin   | 75     | Accounting |
| 7  | Kevin     | Malone   | 70     | Accounting |
| 8  | Holly     | Flax     | 60     | HR |
| 9  | Creed     | Branton  | 70     | Quality Assurance |

* Write a query to find all data in the table

Select * from employees;

* Write a query to find employees with a salary over 75

Select * from employees where salary > 75;

* Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\

Select * from employees where firstName like ‘%e%’ or lastName like ‘S%’;

* Write a query to find the first name of all employees who do not work in accounting

Select firstName from employees where dept != ‘Accounting’;

* Write a query to find the average salary of all employees whose last names begin with 'M'

Select AVG(salary) where lastName like ‘M%’;

* Write a query to find the highest paid salesperson

Select * from employees where dept = ‘Sales’ and salary = (select MAX(salary) from employees) limit 1;

Select MAX(salary) from employees where dept = ‘Sales’;

* Write a query to combine the resultsets of any two previous queries

Select * from employees where salary > 75;
UNION
Select firstName from employees where dept != ‘Accounting’;

* Remove all members of accounting from the database

Delete from employees where dept = ‘Accounting’;

* Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship

| dept_id | name |
| ------- | ---  |
| 1       | Sales |
| 2       | HR   |
| 3       | Accounting |
| 4       | Customer Service |

* Write a query to find the salary of the lowest paid salesperson (HINT: use a join)

Select MIN(salary) from employees
Join department on employees.dept_id = department.dept_id
Where name = ‘Sales’
Order by salary asc
Limit 1;

* Write a query to find the average salary of each department

select AVG(salary) from employees
join department on employees.dept_id = department.dept_id
group by dept_id;

 
* Write a query to find all possible combinations of employees and departments. How many records do you expect?

select * from employees
cross join department;

36 records should be expected.

* Change the name of department 4 to 'Quality Assurance'

Update department
Set name = ‘Quality Assurance’
where dept_id = 4;

* Remove both tables

Drop table if exists employees;
Drop table if exists department;

# JDBC
1.	What is JDBC?

Java™ database connectivity (JDBC) is the JavaSoft specification of a standard application programming interface (API) that allows Java programs to access database management systems. The JDBC API consists of a set of interfaces and classes written in the Java programming language.


Using these standard interfaces and classes, programmers can write applications that connect to databases, send queries written in structured query language (SQL), and process the results.


Since JDBC is a standard specification, one Java program that uses the JDBC API can connect to any database management system (DBMS), as long as a driver exists for that particular DBMS.

2.	What are the core interfaces / classes in JDBC?

o	Driver interface
o	Connection interface
o	Statement interface
o	PreparedStatement interface
o	CallableStatement interface
o	ResultSet interface
o	ResultSetMetaData interface
o	DatabaseMetaData interface
o	RowSet interface

o	DriverManager class
o	Blob class
o	Clob class
o	Types class

3.	What is a stored procedure and how would you call it in Java?

    Stored procedure are user-generated functions or procedures that, once created in the database, can be called by the client applications, such as Java application.
    
    in Java:
        create a database connection
        create a String to store the SQL query
        create a Statement or PreparedStatement object
        set the input parameters if necessary
        call using execute() method



4.	What is the difference between Statement and PreparedStatement?

Statement will be used for executing static SQL statements and it can't accept input parameters. 

PreparedStatement will be used for executing SQL statements many times dynamically. It will accept input parameters.


5.	Steps to executing an SQL query using JDBC?

    1.	Establishing a connection.

    2.	Create a statement.

    3.	Execute the query.

    4.	Process the ResultSet object.

    5.	Close the connection.

 
# AWS
### Cloud / AWS Overview


* How would you describe AWS? What is "the cloud" or "cloud computing" and why is it so popular now?

	Amazon Web Services (AWS) is a secure cloud services platform, offering compute power, database storage, content delivery and other functionality.

	AWS allows you to do the following things: Running web and application servers in the cloud to host dynamic websites.


* Define Infrastructure, Platform, and Software as a Service

•	IaaS, or infrastructure as a service, is on-demand access to cloud-hosted physical and virtual servers, storage and networking - the backend IT infrastructure for running applications and workloads in the cloud. 

•	PaaS, or platform as a service, is on-demand access to a complete, ready-to-use, cloud-hosted platform for developing, running, maintaining and managing applications.

•	SaaS, or software as a service, is on-demand access to ready-to-use, cloud-hosted application software.

 
* What's the difference between a Region and an Availability Zone (AZ)?

Each Region is a separate geographic area.

Availability Zones are multiple, isolated locations within each Region.
 

* How are you charged for using AWS services? Does it vary by service?

AWS pricing is similar to how you pay for utilities like water and electricity. 

You only pay for the services you consume, and once you stop using them, there are no additional costs or termination fees.
 
 
* Different ways to interact with AWS services?
	CLI
	APIs
	SDKs
	AWS Console

### EC2

* What are the configuration options for EC2?

	Choosing an Amazon Machine Image
	Choosing and configuring an instance type
	Adding storage
	Configuring security groups

* What are the different EC2 instance sizes/types?

•	t2.nano, t2.micro, t2.small, t2.medium have up to 3.3 GHz Intel Scalable Processor.
•	t2.large, t2.xlarge, and t2.2xlarge have up to 3.0 GHz Intel Scalable Processor.

 
* Once you create an EC2, how to connect to it?

	connect to your instance using SSH

	- ssh -i [file.pem] ec2-user@[address]
	

* What are Security Groups? When defining a rule for a security group, what 3 things do you need to specify?

A security group is like a virtual firewall that controls traffic that is allowed to access and place resources in an instance.
	You need to specify:
	Ip address
	Protocols
	Port numbers
	

* What's the difference between scalability, elasticity, and resiliency?
The Elasticity refers to the ability of a cloud to automatically expand or compressed the infrastructural resources on a sudden-up and down in the requirement so that the workload can be managed efficiently.

Cloud scalability is used to handle the growing workload where good performance is also needed to work efficiently with software or applications.

Cloud resiliency is the process of foreseeing possible disruptions to technology service at a business.
 


* Ways of paying for EC2?

•	On-Demand Instances let you pay for compute or database capacity by the hour or second (minimum of 60 seconds) depending on which instances you run with no long-term commitments or upfront payments.

•	Savings Plans are a flexible pricing model that offer low prices on Amazon EC2, AWS Lambda and AWS Fargate usage, in exchange for a commitment to a consistent amount of usage (measured in $/hour) for a one- or three-year term.

•	Spot Instances are an Amazon EC2 pricing mechanism that let you request spare computing capacity with no upfront commitment and at discounted hourly rate (up to 90% off the on-demand price).

•	Reservations provide you with the ability to receive a greater discount, up to 75 percent, by paying for capacity ahead of time.

### RDS

* What's an RDS?

Amazon RDS facilitates the deployment and maintenance of relational databases in the cloud. 

A cloud administrator uses Amazon RDS to set up, operate, manage and scale a relational instance of a cloud database.

Amazon RDS is not itself a database; it is a service used to manage relational databases.

* Which vendors are supported?

Amazon RDS supports Amazon Aurora, MySQL, MariaDB, Oracle, SQL Server, and PostgreSQL database engines.

 
# UNIX/LINUX

* What are the differences between a Thread and Process and a Service?

A service is a program or application in Linux that runs or expects to run in the background.

A process is a heavyweight instance of a running program.

A thread is a lightweight process that can be managed independently by a scheduler.


* Write a basic bash script 
    * ie: installing java/maven/git to an ec2
    * if you're feeling ambitious, a script that would delete a file if it exists

#!/bin/bash

    If [-f ${filename}]; then
        rm {filename}
        echo “file removed”
    else
        echo ”file not found”
    fi
