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
import com.deltastar.task7.web.common.form.ChangePasswordForm;
import com.deltastar.task7.web.common.util.CfsUtils;
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Delta Star Team
 */

@WebServlet(name = "CustomerUpdatePasswordServlet", urlPatterns = {"/customer/changePassword", "/customer/changePassword.do"})
public class CustomerUpdatePasswordServlet extends BaseCustomerServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        return Views.CUSTOMER_CHANGE_PASSWORD;
    }


    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmationPassword = request.getParameter("confirmationPassword");


        ChangePasswordForm changePasswordForm = new ChangePasswordForm();
        changePasswordForm.setCurrentPassword(currentPassword);
        changePasswordForm.setNewPassword(newPassword);
        changePasswordForm.setConfirmationPassword(confirmationPassword);

        String nextPage = Views.CUSTOMER_CHANGE_PASSWORD;

        validatePasswords(request, changePasswordForm);

        if (isInvalid(request)) {
            return nextPage;
        }

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute(CfsUtils.SESSION_CUSTOMER);

        if (!confirmationPassword.equals(newPassword)) {
            request.setAttribute("errorConfirmationPassword", getResourceBundle().getString("account.password.confirmation.error"));
            addGlobalChangePasswordErrorAttribute(request);
            return nextPage;
        }

        if (!customer.checkPassword(currentPassword)) {
            request.setAttribute("errorCurrentPassword", getResourceBundle().getString("account.password.error"));
            addGlobalChangePasswordErrorAttribute(request);
            return nextPage;
        }

        customer.setPassword(newPassword);
        getCustomerService().updatePassword(customer.getId(), newPassword);
        try {
            session.setAttribute(CfsUtils.SESSION_CUSTOMER, getCustomerService().getCustomerById(customer.getId()));
            request.setAttribute("updatePasswordSuccessMessage", getResourceBundle().getString("account.password.update.success"));
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
        return Views.CUSTOMER_CHANGE_PASSWORD;

    }

    private void validatePasswords(HttpServletRequest request, ChangePasswordForm changePasswordForm) {
        validateCurrentPassword(request, changePasswordForm);
        validateNewPassword(request, changePasswordForm);
        validateConfirmationPassword(request, changePasswordForm);
    }

    private void validateConfirmationPassword(HttpServletRequest request, ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations
                = getValidator().validateProperty(changePasswordForm, "confirmationPassword");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorConfirmationPassword", constraintViolations.iterator().next().getMessage());
            addGlobalChangePasswordErrorAttribute(request);
        }
    }

    private void validateNewPassword(HttpServletRequest request, ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations
                = getValidator().validateProperty(changePasswordForm, "newPassword");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorNewPassword", constraintViolations.iterator().next().getMessage());
            addGlobalChangePasswordErrorAttribute(request);
        }
    }

    private void validateCurrentPassword(HttpServletRequest request, ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations
                = getValidator().validateProperty(changePasswordForm, "currentPassword");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorCurrentPassword", constraintViolations.iterator().next().getMessage());
            addGlobalChangePasswordErrorAttribute(request);
        }
    }

    private void addGlobalChangePasswordErrorAttribute(HttpServletRequest request) {
        request.setAttribute("error", getResourceBundle().getString("account.password.error.global"));
    }

    private boolean isInvalid(HttpServletRequest request) {
        return request.getAttribute("error") != null;
    }

}
