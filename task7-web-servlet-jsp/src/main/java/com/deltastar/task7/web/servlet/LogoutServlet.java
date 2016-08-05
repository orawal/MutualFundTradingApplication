package com.deltastar.task7.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends BaseHttpServlet {

    @Override
    protected String performDoGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return IndexServlet.INDEX;
    }

    @Override
    protected String performDoPost(HttpServletRequest request, HttpServletResponse response) {
        return performDoGet(request, response);
    }

}