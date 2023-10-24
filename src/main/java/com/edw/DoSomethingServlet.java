package com.edw;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <pre>
 *     com.edw.DoSomethingServlet
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 24 Okt 2023 14:32
 */
@WebServlet(name = "DoSomethingServlet", urlPatterns = "/do-something")
public class DoSomethingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Do Something Okay </h1>");
        out.close();
    }

}
