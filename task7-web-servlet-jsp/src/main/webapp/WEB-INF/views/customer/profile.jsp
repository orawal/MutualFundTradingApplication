<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>
<style type="text/css">
    table {
        width: 100%;
    }

    tr {
        display: table-row;
        vertical-align: inherit;
        border-color: inherit;
    }

    td {
        padding: 8px;
        line-height: 20px;
        text-align: left;
        vertical-align: top;
        border-top: 1px solid #ddd;
    }
</style>
<div class="container">
    <div class="row">
        <div class="span3">
            <%@ include file="../common/sidebar_customer.jspf" %>
        </div>
        <div class="span9">

            <div class="well">
                <div class="page-header">
                    <h1>My Profile</h1>
                </div>

                <%@ include file="../common/error.jspf" %>

                <table class="table-bordered table-striped">
                    <tbody>
                    <tr>
                        <td>
                            Id
                        </td>
                        <td>
                            ${sessionScope.customer.id}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            User Name
                        </td>
                        <td>
                            ${sessionScope.customer.userName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            First Name
                        </td>
                        <td>
                            ${sessionScope.customer.firstName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Last Name
                        </td>
                        <td>
                            ${sessionScope.customer.lastName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Available cash
                        </td>
                        <td>
                            ${sessionScope.customer.cashForDisplay}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Cash to be deposited
                        </td>
                        <td>
                            ${sessionScope.customer.cashToBeDepositedForDisplay}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Cash to be be checked
                        </td>
                        <td>
                            ${sessionScope.customer.cashToBeCheckedForDisplay}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Address
                        </td>
                        <td>
                            ${sessionScope.customer.addressLine1},&nbsp${sessionScope.customer.addressLine2},<br>
                            ${sessionScope.customer.city},&nbsp${sessionScope.customer.state},&nbsp${sessionScope.customer.zipcode}
                        </td>
                    </tr>
                    </tbody>
                </table>

                </div>


            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>
