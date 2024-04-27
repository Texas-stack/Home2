package org.example.Servlets;

import com.google.gson.Gson;
import org.example.DTO.ProductDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        Gson gson = new Gson();
        ProductDTO requestDTO = gson.fromJson(jsonRequest.toString(), ProductDTO.class);

        ProductDTO responseDTO = new ProductDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setName("Processed " + requestDTO.getName());
        responseDTO.setPrice(requestDTO.getPrice() * 2);

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String productIdString = request.getParameter("id");

        if (productIdString == null || productIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        long productId;
        try {
            productId = Long.parseLong(productIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ProductDTO responseDTO = new ProductDTO(productId, "TestProduct", 100);

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

}
