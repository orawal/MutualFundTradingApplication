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

                <form id="createTodoForm" action="/employee/depositCheck.do?customerId=${requestScope.customerId}"
                      method="post" class="form-horizontal">

                    <fieldset>


                        <legend>Deposit check for customer ${requestScope.customerName}
                            <p class="alert-success">${requestScope.hint}</p>
                            <%--the hint for success--%>
                        </legend>
                        <%@ include file="../common/error.jspf" %>


                        <div class="control-group">
                            <label class="control-label" for="amount"><span style="color:red">*</span> Amount:</label>
                            <div class="controls">
                                <input type="text" id="amount" name="amount"
                                       placeholder="between 0.01 to 1,000,000,000"/>
                            </div>
                        </div>


                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary"><i class="icon-ok icon-white"></i>Deposit
                            </button>
                            <button type="button" class="btn" onclick="location.href='home'"><i class="icon-remove"></i>
                                Cancel
                            </button>
                        </div>

                    </fieldset>

                </form>

            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
