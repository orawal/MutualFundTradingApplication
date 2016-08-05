<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="common/header.jspf" %>

<div class="container">
    <div class="row well">
        <div class="span10 offset1">
            <div class="row">

                <div class="span5">
                    <h3>This is a demo for task 7 from Team 9 Delta Star ebiz 2015.</h3>
                    <c:if test="${sessionScope.employee == null && sessionScope.customer == null}">
                        <p>
                            <a class="btn btn-primary btn-large" href="employeeLogin"> Sign in as an employee</a> <br><br>
                            <a class="btn btn-primary btn-large" href="customerLogin"> Sign in as a customer </a>
                        </p>
                    </c:if>

                    <c:if test="${sessionScope.employee != null || sessionScope.customer != null}">
                        <%--in home page servlet, it will decide which home page will go.--%>
                        <p>
                            <c:if test="${sessionScope.employee != null}">
                                <a class="btn btn-primary btn-large" href="employee/home"> Go to my Home page </a>
                            </c:if>
                            <c:if test="${sessionScope.customer != null}">
                                <a class="btn btn-primary btn-large" href="customer/home"> Go to my Home page </a>
                            </c:if>

                        </p>


                    </c:if>

                </div>

            </div>

        </div>

    </div>
</div>

<%@ include file="common/footer.jspf" %>
