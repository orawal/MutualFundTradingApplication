<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>
<%--content--%>

<div class="container">

    <div class="row">
        <div class="span6 offset3">
            <div class="page-header">
                <h1>Sign in as employee</h1>
            </div>

            <%@ include file="../common/error.jspf" %>

            <form class="well form-horizontal" method="post" action="/employeeLogin.do">
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="userName">User name:</label>
                        <div class="controls">
                            <input id="userName" name="userName" type="text" class="input-medium"
                                   placeholder="Your user name" required="required"/>
                            <p class="help-block alert-error"><c:out value="${requestScope.errorUserName}"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password:</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" class="input-medium"
                                   placeholder="Min 6 characters" required="required"/>
                            <p class="help-block alert-error"><c:out value="${requestScope.errorPassword}"/></p>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary"><i class="icon-lock icon-white"></i> Sign in
                        </button>
                    </div>


                </fieldset>
            </form>

        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
