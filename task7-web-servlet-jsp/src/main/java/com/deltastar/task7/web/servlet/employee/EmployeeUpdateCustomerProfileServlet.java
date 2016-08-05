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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * Servlet that controls todo update.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeUpdateCustomerProfileServlet", urlPatterns = {"/employee/updateCustomerProfile.do"})
public class EmployeeUpdateCustomerProfileServlet extends BaseEmployeeServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.EMPLOYEE_UPDATE_CUSTOMER;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        String customerId = request.getParameter("customerId");
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");


        Customer customer = new Customer();
        customer.setUserName(userName);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipcode(zipcode);

        request.setAttribute("customer", customer);
        request.setAttribute("customerId", customerId);
        request.setAttribute("userName", userName);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("addressLine1", addressLine1);
        request.setAttribute("addressLine2", addressLine2);
        request.setAttribute("city", city);
        request.setAttribute("state", state);
        request.setAttribute("zipcode", zipcode);

        try {
            getEmployeeService().updateCustomerProfile(customerId, userName, firstName, lastName, addressLine1, addressLine2, city, state, zipcode);
            request.setAttribute(KEY_HINT, MessageFormat.format(getResourceBundle().getString("customer.profile.update.success"), ""));
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }

//        request.setAttribute("updateProfileSuccessMessage", resourceBundle.getString("account.profile.update.success"));
// FIXME: 1/13/16 should just refresh the current page instead of going back to customer list page.
//        return EmployeeHomeServlet.EMPLOYEE_HOME;
        return Views.EMPLOYEE_UPDATE_CUSTOMER;
    }


}
