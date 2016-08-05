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
                <div class="page-header">
                    <h1>Buy fund</h1>
                </div>

                <%@ include file="../common/error.jspf" %>

                <form id="createTodoForm" action="/customer/buyFund.do" method="post" class="form-horizontal">

                    <fieldset>

                        <div class="control-group">
                            <div class="controls">
                                <input type="hidden" id="fundId" name="fundId" required="required"
                                       value="${requestScope.fund.id}"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="fundName">Fund name:</label>
                            <div class="controls">
                                <input type="text" id="fundName" name="fundName" required="required"
                                       disabled="disabled" value="${requestScope.fund.fundName}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="symbol">Symbol:</label>
                            <div class="controls">
                                <input type="text" id="symbol" name="symbol" required="required"
                                       disabled="disabled" value="${requestScope.fund.symbol}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="amount"><span style="color:red">*</span>
                                Amount($):</label>
                            <div class="controls">
                                <input type="text" id="amount" name="amount" value="${requestScope.amount}"
                                       required="required" autofocus="autofocus"/>
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
                <%@ include file="../common/hint.jspf" %>

            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
