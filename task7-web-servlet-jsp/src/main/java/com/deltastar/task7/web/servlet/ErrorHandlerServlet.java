package com.deltastar.task7.web.servlet;// Import required java libraries

import com.deltastar.task7.web.util.Views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandlerServlet extends BaseHttpServlet {

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {

        List<String> errorList = new ArrayList<>();

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        errorList.add("Error Code : " + statusCode);
        errorList.add("Servlet Name : " + (servletName != null ? servletName : "Unknown"));
        errorList.add("Request Uri : " + (requestUri != null ? requestUri : "Unknown"));

        errorList.add("Exception Message : " + (throwable != null ? getStackTrace(throwable) : "Unknown"));
        request.setAttribute("unexpectedErrorList", errorList);
        return Views.ERROR_PAGE;
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        return performDoGet(request, response);
    }
}