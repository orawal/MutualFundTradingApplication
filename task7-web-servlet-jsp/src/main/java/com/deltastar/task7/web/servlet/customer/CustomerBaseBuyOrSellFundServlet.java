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
import com.deltastar.task7.core.repository.domain.Fund;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.common.form.BuyOrSellFundForm;
import com.deltastar.task7.web.common.util.CfsUtils;
import com.deltastar.task7.web.util.Views;
import com.deltastart.task7.core.constants.CCConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.util.Set;


public abstract class CustomerBaseBuyOrSellFundServlet extends BaseCustomerServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        String fundId = request.getParameter("fundId");
        Fund fund;
        try {
            fund = getCustomerService().getFundById(fundId);
            request.setAttribute("fund", fund);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
        return getFundView();
    }


    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute(CfsUtils.SESSION_CUSTOMER);

        String fundId = request.getParameter("fundId");
        String amountInString = request.getParameter("amount");
        request.setAttribute("amount", amountInString);

        Fund fund;
        try {
            fund = getCustomerService().getFundById(fundId);
            request.setAttribute("fund", fund);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }

        BuyOrSellFundForm buyOrSellFundForm = new BuyOrSellFundForm();
        buyOrSellFundForm.setAmount(amountInString);
        validateAmount(buyOrSellFundForm);

        if (!isValid()) {
            //save the amount input.
            return getFundView();
        }


        try {
            if (isBuyFund()) {
                getCustomerService().buyFund(customer, fundId, amountInString);
            } else {
                getCustomerService().sellFund(customer, fundId, amountInString);
            }
            request.setAttribute(KEY_HINT, CCConstants.HINT_SUCCESS);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }
        return getFundView();
    }

    private String getFundView() {
        return isBuyFund() ? Views.CUSTOMER_BUY_FUND : Views.CUSTOMER_SELL_FUND;
    }

    protected abstract boolean isBuyFund();


    private void validateAmount(BuyOrSellFundForm buyOrSellFundForm) {
        Set<ConstraintViolation<BuyOrSellFundForm>> constraintViolations = getValidator().validateProperty(buyOrSellFundForm, "amount");
        if (!constraintViolations.isEmpty()) {
            getCustomErrorList().add(constraintViolations.iterator().next().getMessage());
        }
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.valueOf(buyOrSellFundForm.getAmount());
        } catch (Exception e) {
            getCustomErrorList().add(CCConstants.INVALID_NUMBER);
        }
    }

}
