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

                <c:choose>
                    <c:when test="${empty requestScope.fundPriceHistoryViewList}">
                        <div class="alert alert-info">
                            <div align="center">There is no fund price history
                                for ${requestScope.fundName}.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="page-header">
                            <h1>${requestScope.fundName}'s price history list</h1>
                        </div>

                        <table class="table table-bordered table-striped">

                            <thead>
                            <tr>
                                <th>Fund name</th>
                                <th>Price</th>
                                <th>Price date</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${requestScope.fundPriceHistoryViewList}" var="currentFundPriceHistoryView">
                                <tr>
                                    <td>${currentFundPriceHistoryView.fundName}</td>
                                    <td>${currentFundPriceHistoryView.priceForDisplay}</td>
                                    <td>${currentFundPriceHistoryView.priceDate}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="3">
                                    <div align="center">Total = <span
                                            class="badge badge-inverse">${requestScope.totalCountFundPriceHistoryView}</span>
                                    </div>
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
