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
import com.deltastar.task7.web.common.form.LoginForm;
import com.deltastar.task7.web.common.util.CfsUtils;
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.util.Set;


@WebServlet(name = "CustomerLoginServlet", urlPatterns = {"/customerLogin", "/customerLogin.do"})
public class CustomerLoginServlet extends BaseCustomerServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginTabStyleCustomer", "active");
        return Views.CUSTOMER_LOGIN;
    }

    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginTabStyleCustomer", "active");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        LoginForm loginForm = new LoginForm();
        loginForm.setUserName(userName);
        loginForm.setPassword(password);

        String nextPage = Views.CUSTOMER_LOGIN;

        validateCredentials(request, loginForm);

        if (isInvalid(request)) {
            return nextPage;
        }

        if (isInvalidCombination(userName, password)) {
            request.setAttribute("error", getResourceBundle().getString("login.error.global.invalid"));
        } else {
            HttpSession session = request.getSession(true);//create session
            Customer customer = getCustomerService().getCustomerByUserName(userName);
            session.setAttribute(CfsUtils.SESSION_CUSTOMER, customer);
            nextPage = CustomerHomeServlet.CUSTOMER_HOME;
        }

        return nextPage;
    }


    private void validateCredentials(HttpServletRequest request, LoginForm loginForm) {
        validateEmail(request, loginForm);
        validatePassword(request, loginForm);
    }

    private boolean isInvalidCombination(String email, String password) {
        return !getCustomerService().login(email, password);
    }

    private void validatePassword(HttpServletRequest request, LoginForm loginForm) {
        Set<ConstraintViolation<LoginForm>> constraintViolations = getValidator().validateProperty(loginForm, "password");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorPassword", constraintViolations.iterator().next().getMessage());
            addGlobalLoginErrorAttribute(request);
        }
    }

    private void validateEmail(HttpServletRequest request, LoginForm loginForm) {
        Set<ConstraintViolation<LoginForm>> constraintViolations = getValidator().validateProperty(loginForm, "userName");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorUserName", constraintViolations.iterator().next().getMessage());
            addGlobalLoginErrorAttribute(request);
        }
    }

    private void addGlobalLoginErrorAttribute(HttpServletRequest request) {
        request.setAttribute("error", getResourceBundle().getString("login.error.global"));
    }

    private boolean isInvalid(HttpServletRequest request) {
        return request.getAttribute("error") != null;
    }

}
