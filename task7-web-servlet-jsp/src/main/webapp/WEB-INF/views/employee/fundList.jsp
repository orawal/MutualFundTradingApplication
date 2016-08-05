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

            <div class="well">
                <div class="page-header">
                    <h1>Fund List</h1>
                </div>
                <%@ include file="../common/error.jspf" %>
                <%@ include file="../common/hint.jspf" %>


                <form id="transitionDay" action="/employee/executeTransitionDay.do" method="post"
                      class="form-horizontal">


                    <div class="form-actions">
                        Execution day:
                        <input type="text" id="executionDay" name="executionDay" required="required"
                               placeholder="MM/DD/YYYY"
                               value="${requestScope.executionDay}"/>
                        <button type="submit" class="btn btn-primary left"><i class="icon-ok icon-white"></i>
                            Execute
                        </button>
                    </div>

                    <table class="table table-bordered table-striped">

                        <thead>
                        <tr>
                            <th>Price History</th>
                            <th>Fund name</th>
                            <th>Symbol</th>
                            <th>Price</th>
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
                                <td>${currentFund.symbol}</td>

                                <td>
                                    <input type="hidden" id="price_id_${currentFund.id}" name="fundId"
                                           value="${currentFund.id}"/>
                                    <input type="text" id="price_${currentFund.id}" name="price"/>
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

                </form>


                <c:if test="${empty requestScope.fundList}">
                    <div class="alert alert-info">
                        <div align="center">There is no available fund.</div>
                        <div align="center"><a class="btn btn-mini btn-primary" href="/employee/newFund"><i
                                class="icon-plus icon-white"></i>Add</a></div>
                    </div>
                </c:if>

            </div>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
