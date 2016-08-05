<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>

<div class="container">
    <div class="row">
        <div class="span3">
            <%@ include file="../common/sidebar_employee.jspf" %>
        </div>


        <div class="span9">

            <%@ include file="search.jspf" %>
            <%@ include file="../common/error.jspf" %>

            <div class="well">
                <div class="page-header">
                    <h1>Customer List</h1>
                </div>


                <table class="table table-bordered table-striped">

                    <thead>
                    <tr>
                        <th>User name</th>
                        <th>Profile</th>
                        <th>Deposit</th>
                        <th>Transition History</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.customerList}" var="currentCustomer">
                        <tr>
                            <td>${currentCustomer.userName}</td>
                            <td>
                                <a class="btn btn-mini btn-primary"
                                   href="/employee/updateCustomer?customerId=${currentCustomer.id}"><i
                                        class="icon-edit icon-white"></i>Edit profile</a>
                            </td>
                            <td>
                                <a class="btn btn-mini btn-primary"
                                   href="/employee/depositCheck?customerId=${currentCustomer.id}"><i
                                        class="icon-edit icon-white"></i>Deposit money</a>
                            </td>

                            <td>
                                <a class="btn btn-mini btn-primary"
                                   href="/employee/viewCustomerTransitionHistory?customerId=${currentCustomer.id}"><i
                                        class="icon-list icon-white"></i>View Transition</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
                            <div align="center">Total = <span
                                    class="badge badge-inverse">${requestScope.totalCountCustomer}</span></div>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <c:if test="${empty requestScope.customerList}">
                    <div class="alert alert-info">
                        <div align="center">Your customer list is empty, please clicking add customer button on the left
                            navigation bar!
                        </div>
                    </div>
                </c:if>

            </div>

            <c:if test="${sessionScope.employee != null && sessionScope.employee.type == 1}">

                <div class="well">
                    <div class="page-header">
                        <h1>Employee List</h1>
                    </div>

                    <table class="table table-bordered table-striped">

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>User name</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${requestScope.employeeList}" var="currentEmployee">
                            <tr>
                                <td>${currentEmployee.id}</td>
                                <td>${currentEmployee.userName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan="2">
                                <div align="center">Total = <span
                                        class="badge badge-inverse">${requestScope.totalCountEmployee}</span></div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>

                    <c:if test="${empty requestScope.employeeList}">
                        <div class="alert alert-info">
                            <div align="center">Your employee list is empty, please clicking add employee button on the
                                left navigation bar!
                            </div>
                        </div>
                    </c:if>

                </div>
            </c:if>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
