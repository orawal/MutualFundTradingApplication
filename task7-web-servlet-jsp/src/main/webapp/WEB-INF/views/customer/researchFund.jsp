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
                <%@ include file="../common/error.jspf" %>

                <c:choose>
                    <c:when test="${empty requestScope.fundList}">
                        <div class="alert alert-info">
                            <div align="center">There is no fund list}.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="page-header">
                            <h1>Chart for last price of fund</h1>
                        </div>

                        <div>
                            <canvas id="canvas"></canvas>
                        </div>

                    </c:otherwise>
                </c:choose>


            </div>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>


<script>
    window.onload = function () {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myBar = new Chart(ctx).Bar(${requestScope.barChartData}, {
            responsive: true
        });
    }

</script>