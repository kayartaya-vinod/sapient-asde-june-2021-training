package com.sapient.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/customers")
public class AddCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        try (PrintWriter out = resp.getWriter(); BufferedReader in = req.getReader();) {
            String line;
            out.println("This is what I got from you:");
            while ((line = in.readLine()) != null) {
                out.println(line);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

}
