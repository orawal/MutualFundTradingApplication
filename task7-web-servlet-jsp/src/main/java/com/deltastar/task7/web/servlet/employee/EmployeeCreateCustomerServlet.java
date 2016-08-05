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
import com.deltastar.task7.web.common.form.CreateCustomerForm;
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.text.MessageFormat;
import java.util.Set;

@WebServlet(name = "EmployeeCreateCustomerServlet", urlPatterns = {"/employee/newCustomer", "/employee/newCustomer.do"})
public class EmployeeCreateCustomerServlet extends BaseEmployeeServlet {

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.EMPLOYEE_CREATE_CUSTOMER;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        Customer customer = new Customer(userName, password, firstName,
                lastName, addressLine1, addressLine2, city, state, zipcode);

        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("addressLine1", addressLine1);
        request.setAttribute("addressLine2", addressLine2);
        request.setAttribute("city", city);
        request.setAttribute("state", state);
        request.setAttribute("zipcode", zipcode);

        if (checkEmployeeForm(customer)) {
            return Views.EMPLOYEE_CREATE_CUSTOMER;
        }

        try {
            getEmployeeService().createCustomer(customer);
            request.setAttribute(KEY_HINT, MessageFormat.format(getResourceBundle().getString("customer.create.success"), userName));
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
        return Views.EMPLOYEE_CREATE_CUSTOMER;
    }

    private boolean checkEmployeeForm(Customer customer) {
        CreateCustomerForm createCustomerForm = new CreateCustomerForm();
        createCustomerForm.setFirstName(customer.getFirstName());
        createCustomerForm.setLastName(customer.getLastName());
        createCustomerForm.setPassword(customer.getPassword());
        createCustomerForm.setUserName(customer.getUserName());
        validateCustomerUsername(createCustomerForm);
        return !isValid();
    }

    private void validateCustomerUsername(CreateCustomerForm createCustomerForm) {
        Set<ConstraintViolation<CreateCustomerForm>> constraintViolations = getValidator().validateProperty(createCustomerForm, "userName");
        if (!constraintViolations.isEmpty()) {
            getCustomErrorList().add(constraintViolations.iterator().next().getMessage());
        }
    }
}
