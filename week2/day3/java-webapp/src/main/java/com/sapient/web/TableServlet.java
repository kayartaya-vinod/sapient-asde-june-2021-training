package com.sapient.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // read inputs from the request
        String input = req.getParameter("num");
        if (input == null) {
            out.println("<p>Hey, please suply a number</p>");
        } else {
            try {
                int num = Integer.parseInt(input);
                for (int i = 1; i <= 10; i++) {
                    out.printf("<p>%d X %d = %d</p>%n", num, i, num * i);
                }
            } catch (Exception e) {
                out.println("<p>Sorry, table can be printed only for numbers!</p>");
            }
        }

        out.close();
    }

}
