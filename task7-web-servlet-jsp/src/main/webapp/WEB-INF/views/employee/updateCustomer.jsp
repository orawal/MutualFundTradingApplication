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
                    <h1>Update customer information</h1>
                </div>

                <%@ include file="../common/error.jspf" %>

                <div class="row">
                    <div class="span8">
                        <form action="/employee/updateCustomerPassword.do" method="post" class="form-horizontal">

                            <fieldset>

                                <legend>
                                    <p class="alert-success">${requestScope.hint}</p>
                                    <%--the hint for success--%>
                                </legend>

                                <div class="control-group">
                                    <label class="control-label" for="password">New password:</label>
                                    <div class="controls">
                                        <input type="password" id="password" name="password"/>
                                    </div>
                                </div>


                                <input type="hidden" name="customerId" value="${requestScope.customerId}"/>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary"><i
                                            class="icon-refresh icon-white"></i> Update
                                    </button>
                                    <button type="button" class="btn" onclick="location.href='home'"><i class="icon-remove"></i>
                                        Cancel
                                    </button>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                </div>


                <div class="row">
                    <div class="span8">
                        <form action="/employee/updateCustomerProfile.do" method="post" class="form-horizontal">

                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="id">Customer Id:</label>
                                    <div class="controls">
                                        <input type="text" id="id" name="id" value="${requestScope.customer.id}"
                                               disabled="disabled"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="userName">User name:</label>
                                    <div class="controls">
                                        <input type="text" id="userName" name="userName"
                                               required disabled value="${requestScope.customer.userName}"/>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="firstName">First name:</label>
                                    <div class="controls">
                                        <input type="text" id="firstName" name="firstName"
                                               required value="${requestScope.customer.firstName}"/>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="lastName">Last name:</label>
                                    <div class="controls">
                                        <input type="text" id="lastName" name="lastName"
                                               required value="${requestScope.customer.lastName}"/>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="addressLine1">Address Line 1:</label>
                                    <div class="controls">
                                        <input type="text" id="addressLine1" name="addressLine1"
                                               required value="${requestScope.customer.addressLine1}"/>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="addressLine2">Address line 2:</label>
                                    <div class="controls">
                                        <input type="text" id="addressLine2" name="addressLine2"
                                               value="${requestScope.customer.addressLine2}"/>
                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="city">City:</label>
                                    <div class="controls">
                                        <input type="text" id="city" name="city" required value="${requestScope.customer.city}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="state">State:</label>
                                    <div class="controls">
                                        <input type="text" id="state" name="state"
                                               required value="${requestScope.customer.state}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="zipcode">Zipcode:</label>
                                    <div class="controls">
                                        <input type="text" id="zipcode" name="zipcode"
                                               required value="${requestScope.customer.zipcode}"/>
                                    </div>
                                </div>


                                <input type="hidden" name="customerId" value="${requestScope.customerId}"/>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary"><i
                                            class="icon-refresh icon-white"></i> Update
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
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
