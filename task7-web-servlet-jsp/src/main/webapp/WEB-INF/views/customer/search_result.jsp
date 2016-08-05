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
            <div class="well">


                <%@ include file="search.jspf" %>
                <%@ include file="../common/error.jspf" %>


                <c:choose>
                    <c:when test="${empty requestScope.fundList}">
                        <div class="alert alert-info">
                            <div align="center">No fund name matched for key word ${requestScope.keyWords}.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>

                        <div class="page-header">
                            <h2>Fund List for key word : ${requestScope.keyWords}</h2>
                        </div>

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
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
