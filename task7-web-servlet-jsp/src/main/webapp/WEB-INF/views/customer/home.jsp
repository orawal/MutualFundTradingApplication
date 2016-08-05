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
                    <h1>Fund List</h1>
                </div>
                <%@ include file="../common/error.jspf" %>

                <table class="table table-bordered table-striped">

                    <thead>
                    <tr>
                        <th>Fund name</th>
                        <th>Symbol</th>
                        <th>Last price</th>
                        <th>Price history</th>
                        <th>Buy</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.fundList}" var="currentFund">
                        <tr>
                            <td>${currentFund.fundName}</td>
                            <td>${currentFund.symbol}</td>
                            <td>${currentFund.lastPriceForDisplay}</td>
                            <td>
                                <a class="btn btn-mini btn-primary"
                                   href="/customer/fundPriceHistory?fundId=${currentFund.id}"><i
                                        class="icon-list icon-white"></i>View price chart</a>
                            </td>

                            <td>
                                <a class="btn btn-mini btn-primary"
                                   href="/customer/buyFund?fundId=${currentFund.id}"><i
                                        class="icon-plus-sign icon-white"></i>Buy</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
                            <div align="center">Total = <span
                                    class="badge badge-inverse">${requestScope.totalCountFund}</span></div>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <c:if test="${empty requestScope.fundList}">
                    <div class="alert alert-info">
                        <div align="center">There is no available fund.</div>
                    </div>
                </c:if>

            </div>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
