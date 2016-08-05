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
                <div class="page-header">
                    <h1>Change Password</h1>
                </div>

                <%@ include file="../common/error.jspf" %>

                <div class="row">
                    <div class="span8">
                        <form action="/employee/changePassword.do" method="post" class="form-horizontal">

                            <fieldset>

                                <legend>Update my password <p
                                        class="alert-success">${requestScope.updatePasswordSuccessMessage}</p></legend>

                                <div class="control-group">
                                    <label class="control-label" for="currentPassword">Current password:</label>

                                    <div class="controls">
                                        <input type="password" id="currentPassword" name="currentPassword"
                                               class="input-medium" placeholder="min 6 characters" required="required"/>
                                        <p class="help-block alert-error"><c:out
                                                value="${requestScope.errorCurrentPassword}"/></p>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="newPassword">New password:</label>

                                    <div class="controls">
                                        <input type="password" id="newPassword" name="newPassword" class="input-medium"
                                               placeholder="min 6 characters" required="required"/>
                                        <p class="help-block alert-error"><c:out
                                                value="${requestScope.errorNewPassword}"/></p>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="confirmationPassword">Confirmation
                                        password:</label>

                                    <div class="controls">
                                        <input type="password" id="confirmationPassword" name="confirmationPassword"
                                               class="input-medium" placeholder="min 6 characters" required="required"/>
                                        <p class="help-block alert-error"><c:out
                                                value="${requestScope.errorConfirmationPassword}"/></p>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary"><i
                                            class="icon-refresh icon-white"></i> Update my password
                                    </button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
