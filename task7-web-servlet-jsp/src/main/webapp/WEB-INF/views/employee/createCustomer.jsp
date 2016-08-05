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


                <form id="createTodoForm" action="/employee/newCustomer.do" method="post" class="form-horizontal">

                    <fieldset>

                        <legend>Create a new customer
                            <p class="alert-success">${requestScope.hint}</p>
                            <%--the hint for success--%>
                        </legend>
                        <%@ include file="../common/error.jspf" %>

                        <div class="control-group">
                            <label class="control-label" for="userName"><span style="color:red">*</span> User
                                Name:</label>
                            <div class="controls">
                                <input type="text" id="userName" name="userName" value="${requestScope.userName}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password"><span style="color:red">*</span>
                                Password:</label>
                            <div class="controls">
                                <input type="password" id="password" name="password" required="required"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="firstName"><span style="color:red">*</span> First
                                Name:</label>
                            <div class="controls">
                                <input type="text" id="firstName" name="firstName" required="required"
                                       value="${requestScope.firstName}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="lastName"><span style="color:red">*</span> Last
                                Name:</label>
                            <div class="controls">
                                <input type="text" id="lastName" name="lastName" required="required"
                                       value="${requestScope.lastName}"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="addressLine1"><span style="color:red">*</span> Address
                                Line 1:</label>
                            <div class="controls">
                                <input type="text" id="addressLine1" name="addressLine1"
                                       value="${requestScope.addressLine1}"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="addressLine2">Address Line 2:</label>
                            <div class="controls">
                                <input type="text" id="addressLine2" name="addressLine2"
                                       value="${requestScope.addressLine2}"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="city"><span style="color:red">*</span> City:</label>
                            <div class="controls">
                                <input type="text" id="city" name="city" value="${requestScope.city}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="state"><span style="color:red">*</span> State:</label>
                            <div class="controls">
                                <select id="state" name="state">
                                    <option value="Alabama">Alabama</option>
                                    <option value="Alaska">Alaska</option>
                                    <option value="Arizona">Arizona</option>
                                    <option value="Arkansas">Arkansas</option>
                                    <option value="California">California</option>
                                    <option value="Colorado">Colorado</option>
                                    <option value="Connecticut">Connecticut</option>
                                    <option value="Delaware">Delaware</option>
                                    <option value="District Of Columbia">District Of Columbia</option>
                                    <option value="Florida">Florida</option>
                                    <option value="Georgia">Georgia</option>
                                    <option value="Hawaii">Hawaii</option>
                                    <option value="Idaho">Idaho</option>
                                    <option value="Illinois">Illinois</option>
                                    <option value="Indiana">Indiana</option>
                                    <option value="Iowa">Iowa</option>
                                    <option value="Kansas">Kansas</option>
                                    <option value="Kentucky">Kentucky</option>
                                    <option value="Louisiana">Louisiana</option>
                                    <option value="Maine">Maine</option>
                                    <option value="Maryland">Maryland</option>
                                    <option value="Massachusetts">Massachusetts</option>
                                    <option value="Michigan">Michigan</option>
                                    <option value="Minnesota">Minnesota</option>
                                    <option value="Mississippi">Mississippi</option>
                                    <option value="Missouri">Missouri</option>
                                    <option value="Montana">Montana</option>
                                    <option value="Nebraska">Nebraska</option>
                                    <option value="Nevada">Nevada</option>
                                    <option value="New Hampshire">New Hampshire</option>
                                    <option value="New Jersey">New Jersey</option>
                                    <option value="New Mexico">New Mexico</option>
                                    <option value="New York">New York</option>
                                    <option value="North Carolina">North Carolina</option>
                                    <option value="North Dakota">North Dakota</option>
                                    <option value="Ohio">Ohio</option>
                                    <option value="Oklahoma">Oklahoma</option>
                                    <option value="Oregon">Oregon</option>
                                    <option value="Pennsylvania">Pennsylvania</option>
                                    <option value="Rhode Island">Rhode Island</option>
                                    <option value="South Carolina">South Carolina</option>
                                    <option value="South Dakota">South Dakota</option>
                                    <option value="Tennessee">Tennessee</option>
                                    <option value="Texas">Texas</option>
                                    <option value="Utah">Utah</option>
                                    <option value="Vermont">Vermont</option>
                                    <option value="Virginia">Virginia</option>
                                    <option value="Washington">Washington</option>
                                    <option value="West Virgina">West Virginia</option>
                                    <option value="Wisconsin">Wisconsin</option>
                                    <option value="Wyoming">Wyoming</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="zipcode"><span style="color:red">*</span> Zipcode:</label>
                            <div class="controls">
                                <input type="text" id="zipcode" name="zipcode" value="${requestScope.zipcode}"/>
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
