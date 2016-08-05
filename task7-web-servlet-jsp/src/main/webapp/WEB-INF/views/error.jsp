<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf" %>
<%--content--%>

<div class="container">
    <div class="row">
        <div align="center">
            <p>
                <c:if test="${sessionScope.employee != null}">
                    <a class="btn btn-primary btn-large" href="/employee/home"> Go to my Home page </a>
                </c:if>
                <c:if test="${sessionScope.customer != null}">
                    <a class="btn btn-primary btn-large" href="/customer/home"> Go to my Home page </a>
                </c:if>

                <c:if test="${sessionScope.customer == null && sessionScope.employee == null}">
                    <a class="btn btn-danger" href="javascript:history.go(-1);">Back</a>
                </c:if>
            </p>
        </div>

        <div class="alert alert-block alert-error fade in">
            <h2 class="alert-heading">An error occurred, please find details below:</h2>
            <br/>
            <c:forEach items="${requestScope.unexpectedErrorList}" var="error">
                <p> ${error} </p>
            </c:forEach>


        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="common/footer.jspf" %>
