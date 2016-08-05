/*
 * The MIT License
 *
 *   Copyright (c) 2015, Delta Star Team
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package com.deltastar.task7.web.servlet.employee;

import com.deltastar.task7.core.repository.domain.Customer;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.util.Views;
import com.deltastart.task7.core.constants.CCConstants;
import com.deltastart.task7.core.constants.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet that controls todo creation.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeDepositCheckForCustomerServlet", urlPatterns = {"/employee/depositCheck", "/employee/depositCheck.do"})
public class EmployeeDepositCheckForCustomerServlet extends BaseEmployeeServlet {


    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        preSetValue(request);
        return Views.EMPLOYEE_DEPOSIT_CHECK;
    }

    private void preSetValue(HttpServletRequest request) {
        String customerIdAsString = request.getParameter("customerId");
        request.setAttribute("customerId", customerIdAsString);
        try {
            request.setAttribute("customerName", Util.getDisplayName(getEmployeeService().getCustomerById(customerIdAsString)));
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
    }


    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        preSetValue(request);
        String customerId = request.getParameter("customerId");
        String amountInString = request.getParameter("amount");
        request.setAttribute("amount", amountInString);
        Customer customer;
        try {
            customer = getEmployeeService().getCustomerById(customerId);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
            return Views.EMPLOYEE_DEPOSIT_CHECK;

        }

        try {
            getEmployeeService().depositCheck(customerId, amountInString);
            request.setAttribute(KEY_HINT, CCConstants.HINT_SUCCESS);

        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
        request.setAttribute("success", "succeed in depositing $" + amountInString + " for "
                + Util.getDisplayName(customer));
        return Views.EMPLOYEE_DEPOSIT_CHECK;

    }

}
