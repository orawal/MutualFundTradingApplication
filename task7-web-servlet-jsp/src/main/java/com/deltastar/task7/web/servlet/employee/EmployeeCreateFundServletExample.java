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
import com.deltastar.task7.web.common.form.CreateFundForm;
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.text.MessageFormat;
import java.util.Set;


/**
 * Servlet that create a new fund. This is employee action, so it extend from BaseEmployeeServlet,
 * which provides some common fields and functions to use. It will simplify writing same code.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeCreateFundServletExample", urlPatterns = {"/employee/newFund", "/employee/newFund.do"})
public class EmployeeCreateFundServletExample extends BaseEmployeeServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.EMPLOYEE_CREATE_FUND_EXAMPLE;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        //get the parameter sent from Web client.
        String fundName = request.getParameter("fundName");
        String symbol = request.getParameter("symbol");
        String comment = request.getParameter("comment");

        //Set the parameter into attributes again to keep whatever user has inputted in Client.
        //If there is any error in the current transaction, we should be able to save whatever the user has input.
        //So there is necessary.
        //And to make sure this works, there should be some extra code done in jsp,
        //which is to reference the attribute here.
        request.setAttribute("fundName", fundName);
        request.setAttribute("symbol", symbol);
        request.setAttribute("comment", comment);


        //Check the basic form first.
        if (checkForm(fundName, symbol)) {
            return Views.EMPLOYEE_CREATE_FUND_EXAMPLE;
        }


        try {
            //Call the service to create fund. Currently since every parameter from Client is passed as String.
            //So the parameter passed to service are all strings.
            getEmployeeService().createFundExample(fundName, symbol, comment);
            //After the success of creating fund, there should be a hint telling user.
            request.setAttribute(KEY_HINT, MessageFormat.format(getResourceBundle().getString("success.create.fund"), fundName));
        } catch (CfsException e) {
            //If there is any error such as invalid parameter, duplicated fund name or so, it will throw exceptions,
            //which will should be caught there and add the error message into our error list,
            //which will eventually be presented to users by including error jspf.
            getCustomErrorList().add(e.getMessage());
        }
        return Views.EMPLOYEE_CREATE_FUND_EXAMPLE;

    }

    /**
     * check form
     *
     * @return true if the parameter is invalid.
     */
    private boolean checkForm(String fundName, String symbol) {
        CreateFundForm createFundForm = new CreateFundForm();
        createFundForm.setSymbol(symbol);
        createFundForm.setFundName(fundName);
        validateFundName(createFundForm);
        validateSymbol(createFundForm);
        return !isValid();
    }


    /**
     * validate the fund name
     */
    private void validateFundName(CreateFundForm createFundForm) {
        Set<ConstraintViolation<CreateFundForm>> constraintViolations = getValidator().validateProperty(createFundForm, "fundName");
        if (!constraintViolations.isEmpty()) {
            getCustomErrorList().add(constraintViolations.iterator().next().getMessage());
        }
    }

    /**
     * validate the fund name
     */
    private void validateSymbol(CreateFundForm createFundForm) {
        Set<ConstraintViolation<CreateFundForm>> constraintViolations = getValidator().validateProperty(createFundForm, "symbol");
        if (!constraintViolations.isEmpty()) {
            getCustomErrorList().add(constraintViolations.iterator().next().getMessage());
        }
    }


}
