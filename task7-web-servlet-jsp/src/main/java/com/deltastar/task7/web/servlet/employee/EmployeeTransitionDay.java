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
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastar.task7.web.util.Views;
import com.deltastart.task7.core.constants.CCConstants;
import com.deltastart.task7.core.constants.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Delta Star Team
 */

@WebServlet(name = "EmployeeTransitionDay", urlPatterns = {"/employee/fundList", "/employee/executeTransitionDay.do"})
public class EmployeeTransitionDay extends BaseEmployeeServlet {


    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        String executionDay = Util.getCurrentDay();
        request.setAttribute("executionDay", executionDay);
        List<Fund> fundList = getEmployeeService().getFundList();
        request.setAttribute("fundList", fundList);
        int totalCount = fundList.size();
        request.setAttribute("totalCountFund", totalCount);
        return Views.EMPLOYEE_TRANSITION_DAY;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {

        String[] prices = request.getParameterValues("price");
        String[] fundIds = request.getParameterValues("fundId");
        String executionDay = request.getParameter("executionDay");
        if (Util.isEmpty(executionDay)) {
            executionDay = Util.getCurrentDay();
        }
        request.setAttribute("executionDay", executionDay);
        List<Fund> fundList = getEmployeeService().getFundList();
        request.setAttribute("fundList", fundList);
        int totalCount = fundList.size();
        request.setAttribute("totalCountFund", totalCount);
        try {

            Util.formatTimeStamp(executionDay);
            if (prices != null && prices.length > 0 && fundIds != null && fundIds.length > 0 && hasValidPrice(prices)) {
                getEmployeeService().executeTransitionDay(prices, fundIds, executionDay);
                request.setAttribute(KEY_HINT, CCConstants.HINT_SUCCESS);
            } else {
                getCustomErrorList().add("No validate price for funds.");

            }
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }


        return Views.EMPLOYEE_TRANSITION_DAY;
    }


    private boolean hasValidPrice(String[] prices) {
        for (String price : prices) {
            if (!Util.isEmpty(price)) {
                return true;
            }
        }
        return false;
    }


}
