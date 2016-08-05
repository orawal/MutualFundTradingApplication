## Task7 Carnegie Financial System from Team 9 Delta Star

FAQ:

1, we have three type of account. Super employee, employee, customer.
Super employee could create both employee and customer.
Employee could only create customer.
The super employee account info:
user name:jeff
password:teamnine

With the super employee account, you could basically create whatever account that you want.


2, we use Servlet, Spring & Hibernate.
We did not use GenericDao as we would like to learn something new through this task.

3, our code has three module:
task7-core module, which contains the domain(java bean), repository(DAO) & and service(Action).
task7-web-common, which contains form validation and common util.
task7-web-servlet-jsp, which contains servlet(controller)

4, we use filter to handle authorization of employee and customer,
which makes our code more clean.

5,all our business logic is handled in service layer.

6, we have defined the error message into properties to make it configurable.

7, we have our customized exception to help data validation.

8, we use a lot of Mysql Views to minimize the data storage.

9, we have used Chart JS to demonstrate chart for fund price history.

For more details, please refer to

https://docs.google.com/presentation/d/1plytB4m9Iew-QjfyKYbGjBs7uIOLt2auF5wS0BSI0wo/edit?usp=sharing




