package com.sapient.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.dao.DaoFactory;
import com.sapient.dao.ProductDao;
import com.sapient.entity.Product;

@WebServlet(urlPatterns = { "/product-list", "/products" })
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // A web controller typicall has the following responsibilities:
        // 1. Read any inputs from the request (query/matrix/path parameters, headers,
        // cookies, payload)
        // 2. Get a suitable service/dao object and call appropriate methods
        // 3. Store the model data in a place which is accessible to JSP (scopes:
        // request, session, application)
        // 4. Redirect the request to the JSP for presenting the model data

        ProductDao dao = DaoFactory.getProductDao();
        try {
            List<Product> list = dao.getAllProducts();
            req.setAttribute("products", list);
            req.setAttribute("title", "List of products");
            RequestDispatcher rd = req.getRequestDispatcher("/show-products.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e); // exception funnelling
        }
    }

}
