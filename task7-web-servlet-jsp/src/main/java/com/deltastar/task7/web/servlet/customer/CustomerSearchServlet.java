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

import com.deltastar.task7.core.repository.domain.Fund;
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

@WebServlet(name = "CustomerSearchServlet", urlPatterns = CustomerSearchServlet.CUSOMTER_SEARCH)
public class CustomerSearchServlet extends BaseCustomerServlet {

    public static final String CUSOMTER_SEARCH = "/customer/search.do";

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        String keywords = request.getParameter("keyWords");
        request.setAttribute("keyWords", keywords);
        try {
            List<Fund> fundList = getCustomerService().search(keywords);
            request.setAttribute("fundList", fundList);
            request.setAttribute("totalCountFund", fundList != null ? fundList.size() : 0);
        } catch (CfsException e) {
            getCustomErrorList().add(e.getMessage());
        }

        return Views.CUSTOMER_SEARCH_RESULT;
    }


}
