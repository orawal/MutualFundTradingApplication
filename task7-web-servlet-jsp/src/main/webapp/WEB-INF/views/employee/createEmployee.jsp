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


                <%@ include file="../common/error.jspf"%>


                <form id="createTodoForm" action="/employee/newEmployee.do" method="post" class="form-horizontal">

                    <fieldset>

                        <legend>Create a new employee
                            <p class="alert-success">${requestScope.hint}</p>
                            <%--the hint for success--%>
                        </legend>
                        <div class="control-group">
                            <label class="control-label" for="userName"><span style="color:red">*</span> User Name:</label>
                            <div class="controls">
                                <input type="text" id="userName" name="userName" value="${requestScope.userName}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password"><span style="color:red">*</span> Password:</label>
                            <div class="controls">
                                <input type="password" id="password" name="password" required="required"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="firstName"><span style="color:red">*</span> First Name:</label>
                            <div class="controls">
                                <input type="text" id="firstName" name="firstName" required="required" value = "${requestScope.firstName}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="lastName"><span style="color:red">*</span> Last Name:</label>
                            <div class="controls">
                                <input type="text" id="lastName" name="lastName" required="required" value = "${requestScope.lastName}"/>
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
