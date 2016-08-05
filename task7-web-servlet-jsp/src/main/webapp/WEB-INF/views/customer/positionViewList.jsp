<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>

<div class="container">
    <div class="row">
        <div class="span3">
            <%@ include file="../common/sidebar_customer.jspf" %>
        </div>
        <div class="span9">
            <%@ include file="search.jspf" %>

            <div class="well">
                <div class="page-header">
                    <h1>Position List</h1>
                </div>
                <%@ include file="../common/error.jspf" %>


                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <ul class="nav">
                            <li class="${requestScope.inPossession}">
                                <a href="/customer/positionListInPossession">In possession</a>
                            </li>
                            <li class="${requestScope.toBeSold}">
                                <a href="/customer/positionListToBeSold">To be sold</a>
                            </li>
                            <li class="${requestScope.toBeBought}">
                                <a href="/customer/positionListToBeBought">To be bought</a>
                            </li>
                        </ul>
                    </div>
                </nav>


                <table class="table table-bordered table-striped">

                    <thead>
                    <tr>
                        <th>Fund Name</th>
                        <th>Shares</th>
                        <th>Last price</th>
                        <th>Estimated value</th>
                        <th>Status</th>

                        <c:if test="${requestScope.inPossession != null}">
                            <th>Action</th>
                        </c:if>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.positionViewList}" var="currentPosition">
                        <tr>
                            <td>${currentPosition.fundName}</td>
                            <td>${currentPosition.sharesForDisplay}</td>
                            <td>${currentPosition.lastPriceForDisplay}</td>
                            <td>${currentPosition.value}</td>
                            <td>${currentPosition.displayStatus}</td>

                            <c:if test="${requestScope.inPossession != null}">
                                <td>
                                    <a class="btn btn-mini btn-primary"
                                       href="/customer/sellFund?fundId=${currentPosition.fundId}"><i
                                            class="icon-minus-sign icon-white"></i>Sell</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="6">
                            <div align="center">Total = <span
                                    class="badge badge-inverse">${requestScope.totalCountPositionView}</span></div>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <c:if test="${empty requestScope.positionListView}">
                    <div class="alert alert-info">
                        <div align="center">There is no position that you have bought.</div>
                    </div>
                </c:if>

            </div>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
