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


                <form id="createTodoForm" action="/employee/newFund.do" method="post" class="form-horizontal">


                    <fieldset>
                        <%@ include file="../common/error.jspf" %>
                        <%--the hint for error--%>

                        <legend>Create a new fund
                            <p class="alert-success">${requestScope.hint}</p>
                            <%--the hint for success--%>
                        </legend>

                        <div class="control-group">
                            <label class="control-label" for="fundName"><span style="color:red">*</span> Fund
                                Name:</label>
                            <div class="controls">
                                <input type="text" id="fundName" name="fundName" autofocus="autofocus"
                                       required="required" value="${requestScope.fundName}"/>
                            </div>
                            <%--To test the validation on server part, we should remove the required attributes in jsp first.--%>
                            <%--After the server validation is done, we should add back the requires attributes in jsp.--%>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="symbol"><span style="color:red">*</span> Ticker: </label>
                            <div class="controls">
                                <input type="text" id="symbol" name="symbol" placeholder="1 to 5 character long"
                                       required="required" value="${requestScope.symbol}"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="comment">Comment:</label>
                            <div class="controls">
                                <input type="text" id="comment" name="comment" value="${requestScope.comment}"/>
                            </div>
                        </div>


                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary"><i class="icon-ok icon-white"></i> Create
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
