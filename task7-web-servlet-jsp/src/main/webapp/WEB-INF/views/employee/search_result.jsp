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
            <div class="well">


                <%@ include file="search.jspf" %>
                <%@ include file="../common/error.jspf" %>

                <c:if test="${empty requestScope.customerList && empty requestScope.fundList}">
                    <div class="alert alert-info">
                        <div align="center">No result matched for key word ${requestScope.keyWords}.
                        </div>
                    </div>
                </c:if>


                <c:choose>
                    <c:when test="${empty requestScope.customerList}">
                    </c:when>
                    <c:otherwise>

                        <div class="page-header">
                            <h2>Customer List for key word : ${requestScope.keyWords}</h2>
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
                    </c:otherwise>
                </c:choose>


                <c:choose>
                    <c:when test="${empty requestScope.fundList}">
                    </c:when>
                    <c:otherwise>

                        <div class="page-header">
                            <h2>Fund List for key word : ${requestScope.keyWords}</h2>
                        </div>

                        <table class="table table-bordered table-striped">

                            <thead>
                            <tr>
                                <th>View history</th>
                                <th>Fund name</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${requestScope.fundList}" var="currentFund">
                                <tr>
                                    <td>
                                        <a class="btn btn-mini btn-primary"
                                           href="/employee/fundPriceHistory?fundId=${currentFund.id}"><i
                                                class="icon-list icon-white"></i>View history</a>
                                    </td>

                                    <td>${currentFund.fundName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>

                            <tfoot>
                            <tr>
                                <td colspan="2">
                                    <div align="center">Total = <span
                                            class="badge badge-inverse">${requestScope.totalCountFund}</span></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
