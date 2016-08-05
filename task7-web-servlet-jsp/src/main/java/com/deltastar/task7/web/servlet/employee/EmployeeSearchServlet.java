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
import com.deltastar.task7.web.util.Views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Servlet that controls action on the user's home page.
 * <p>
 * Delta Star Team
 */

@WebServlet(name = "EmployeeSearchServlet", urlPatterns = EmployeeSearchServlet.EMPLOYEE_SEARCH)
public class EmployeeSearchServlet extends BaseEmployeeServlet {

    public static final String EMPLOYEE_SEARCH = "/employee/search.do";

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        String keywords = request.getParameter("keyWords");
        request.setAttribute("keyWords", keywords);
        List<List<?>> matchedResult = null;
        try {
            matchedResult = getEmployeeService().search(keywords);
            List<?> customerList = matchedResult.get(0);
            List<?> fundList = matchedResult.get(1);
            request.setAttribute("customerList", customerList);
            request.setAttribute("fundList", fundList);

            request.setAttribute("totalCountCustomer", customerList != null ? customerList.size() : 0);
            request.setAttribute("totalCountFund", fundList != null ? fundList.size() : 0);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }

        return Views.EMPLOYEE_SEARCH_RESULT;
    }


}
