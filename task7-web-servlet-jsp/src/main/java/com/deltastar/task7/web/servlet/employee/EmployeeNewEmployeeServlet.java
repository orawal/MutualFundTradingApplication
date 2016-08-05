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

import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.common.form.CreateEmployeeForm;
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.text.MessageFormat;
import java.util.Set;


/**
 * Servlet that creates a new employee
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeNewEmployeeServlet", urlPatterns = {"/employee/newEmployee", "/employee/newEmployee.do"})
public class EmployeeNewEmployeeServlet extends BaseEmployeeServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.EMPLOYEE_CREATE_EMPLOYEE;
    }

    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);

        if (checkEmployeeForm(userName, password, firstName, lastName)) {
            return Views.EMPLOYEE_CREATE_EMPLOYEE;
        }


        try {
            getEmployeeService().createEmployee(userName, password, firstName, lastName);
            request.setAttribute(KEY_HINT, MessageFormat.format(getResourceBundle().getString("employee.create.success"), userName));
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }

        return  Views.EMPLOYEE_CREATE_EMPLOYEE;

    }

    private boolean checkEmployeeForm(String userName, String password, String firstName, String lastName) {
        CreateEmployeeForm createEmployeeForm = new CreateEmployeeForm();
        createEmployeeForm.setFirstName(firstName);
        createEmployeeForm.setLastName(lastName);
        createEmployeeForm.setPassword(password);
        createEmployeeForm.setUserName(userName);
        validateEmployeeUsername(createEmployeeForm);
        return !isValid();
    }

    private void validateEmployeeUsername(CreateEmployeeForm createEmployeeForm) {
        Set<ConstraintViolation<CreateEmployeeForm>> constraintViolations = getValidator().validateProperty(createEmployeeForm, "userName");
        if (!constraintViolations.isEmpty()) {
            getCustomErrorList().add(constraintViolations.iterator().next().getMessage());
        }
    }

}
