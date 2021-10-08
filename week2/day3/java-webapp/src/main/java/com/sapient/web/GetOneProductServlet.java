package com.sapient.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.dao.DaoFactory;
import com.sapient.dao.ProductDao;
import com.sapient.entity.Product;

// servlet configuration is in WEB-INF/web.xml
public class GetOneProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Read any inputs from the request
        String input = req.getParameter("id");
        if (input == null) {
            // redirect the user to "/product-list" page
            redirect(resp); // adds a response header Location: ./product-list
            return;
        }
        try {
            Integer id = new Integer(input);
            // 2. Get a suitable service/dao object and call appropriate methods
            ProductDao dao = DaoFactory.getProductDao();
            Product p = dao.getById(id);
            // 3. Store the model data in a place which is accessible to JSP
            req.setAttribute("prd", p);
            // 4. Redirect the request to the JSP for presenting the model data
            req.getRequestDispatcher("/product-details.jsp").forward(req, resp);
        } catch (Exception e) {
            redirect(resp); // adds a response header Location: ./product-list
            return;
        }

    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("./product-list");
    }

}
