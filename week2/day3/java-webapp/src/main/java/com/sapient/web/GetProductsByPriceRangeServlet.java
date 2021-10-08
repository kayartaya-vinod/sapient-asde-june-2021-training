package com.sapient.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.dao.DaoFactory;
import com.sapient.dao.ProductDao;
import com.sapient.entity.Product;

@WebServlet("/get-products-by-price-range")
public class GetProductsByPriceRangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. Read any inputs from the request (query parameters )
            Double min = new Double(req.getParameter("min"));
            Double max = new Double(req.getParameter("max"));
            // 2. Get a suitable service/dao object and call appropriate methods
            ProductDao dao = DaoFactory.getProductDao();
            List<Product> list = dao.getProductsByPriceRange(min, max);
            // 3. Store the model data in a place which is accessible to JSP (request)
            req.setAttribute("products", list);
            req.setAttribute("title", String.format("List of products with the range of Rs.%.1f Rs.%.1f", min, max));
            // 4. Redirect the request to the JSP for presenting the model data
            req.getRequestDispatcher("/show-products.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("./");
        }

    }

}
