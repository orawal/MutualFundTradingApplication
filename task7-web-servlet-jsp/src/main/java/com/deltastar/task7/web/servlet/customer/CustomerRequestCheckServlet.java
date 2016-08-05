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

package com.deltastar.task7.web.servlet.customer;

import com.deltastar.task7.core.repository.domain.Customer;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.common.util.CfsUtils;
import com.deltastar.task7.web.util.Views;
import com.deltastart.task7.core.constants.CCConstants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet that controls todo creation.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "CustomerRequestCheckServlet", urlPatterns = {"/customer/requestCheck", "/customer/requestCheck.do"})
public class CustomerRequestCheckServlet extends BaseCustomerServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.CUSTOMER_REQUEST_CHECK;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        Customer customer = (Customer) request.getSession().getAttribute(CfsUtils.SESSION_CUSTOMER);
        String amountInString = request.getParameter("amount");
        try {
            getCustomerService().requestCheck(customer, amountInString);
            request.setAttribute(KEY_HINT, CCConstants.HINT_SUCCESS);
        } catch (CfsException e) {
            e.printStackTrace();
            getCustomErrorList().add(e.getMessage());
        }
        return Views.CUSTOMER_REQUEST_CHECK;

    }

}
