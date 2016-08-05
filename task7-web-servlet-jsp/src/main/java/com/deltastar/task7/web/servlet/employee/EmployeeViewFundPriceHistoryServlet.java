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

import com.deltastar.task7.core.repository.domain.Fund;
import com.deltastar.task7.core.repository.domain.FundPriceHistoryView;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.util.Views;
import com.deltastart.task7.core.constants.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Servlet that controls action on the user's home page.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeViewFundPriceHistoryServlet", urlPatterns = "/employee/fundPriceHistory")
public class EmployeeViewFundPriceHistoryServlet extends BaseEmployeeServlet {

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        String fundId = request.getParameter("fundId");
        Fund fund = null;
        try {
            fund = getEmployeeService().getFundById(fundId);
        } catch (CfsException e) {
            e.printStackTrace();//// TODO: 1/24/16
            getCustomErrorList().add(e.getMessage());
            return Views.EMPLOYEE_FUND_PRICE_HISTORY_BY_FUND;
        }
        request.setAttribute("fundName", fund.getFundName());
        List<FundPriceHistoryView> fundPriceHistoryViewList = null;
        try {
            fundPriceHistoryViewList = getEmployeeService().getFundPriceHistoryViewList(fundId);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
            return Views.EMPLOYEE_FUND_PRICE_HISTORY_BY_FUND;
        }
        if (!Util.isEmptyList(fundPriceHistoryViewList)) {
            request.setAttribute("fundPriceHistoryViewList", fundPriceHistoryViewList);
            request.setAttribute("totalCountFundPriceHistoryView", fundPriceHistoryViewList.size());
        }
        request.setAttribute("homeTabStyle", "active");
        return Views.EMPLOYEE_FUND_PRICE_HISTORY_BY_FUND;
    }
}
