package com.sapient.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8080/java-webapp/hello
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.setContentType("text/html"); // setting MIME type

        // PrintWriter out = resp.getWriter();
        // out.println("<h1>Hello from Servlet</h1>");
        // out.println("<hr />");
        // out.println("<p>Created by Vinod</p>");

        // out.close();
        RequestDispatcher rd = req.getRequestDispatcher("/hello.jsp");
        req.setAttribute("name", "Vinod");
        rd.forward(req, resp);
    }

}
