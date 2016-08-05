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

                <%@ include file="../common/error.jspf" %>
                <form id="createTodoForm" action="/customer/requestCheck.do" method="post" class="form-horizontal">

                    <fieldset>


                        <legend>Request check
                            <p class="alert-success">${requestScope.hint}</p>
                            <%--the hint for success--%>
                        </legend>


                        <div class="control-group">
                            <label class="control-label" for="amount">Amount:</label>
                            <div class="controls">
                                <input type="text" id="amount" name="amount" required="required"/>
                            </div>
                        </div>


                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary"><i class="icon-ok icon-white"></i> Create
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
